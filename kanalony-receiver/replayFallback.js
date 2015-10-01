'use strict';

var fs         = require('fs'),
    path       = require('path'),
    JSONStream = require('JSONStream'),
    kanalony   = require('./kanalony'),
    timeUtil   = kanalony.TimeUtil,
    config     = kanalony.ConfigurationUtil,
    logger     = kanalony.Logger(module),
    argv       = require('minimist')(process.argv.slice(2));

/**
 * A node script to replay events that were fallbacked because they were unable to be published to Kafka (for some reason).
 * The script tries to replay the events from a file of the current minute, by default.
 * If a --time argument is provided (YYYYmmddHHMM or ISO format) it will try to replay a file with a pattern of that time
 *
 * Usage Examples:
 * ---------------
 *
 *  $ node replayFallback.js
 *     # equivalent to:
 *  $ node replayFallback.js --time now
 *  $ node replayFallback.js --time 'Thu, 01 Oct 2015 14:46:50 GMT'
 *  $ node replayFallback.js --time 201509011746
 */
var replayFallback = function(zkConnectionString){
    var that = this;
    this.fallbackPath = config.getOrElse('kanalony.receiver.fallback.path','/tmp/fallback');
    this.errorPath = config.getOrElse('kanalony.receiver.error.path','/tmp/errors');
    this.producer = new kanalony.KafkaProducer(zkConnectionString, function(){
        that.replayByArgs(argv);

    });
};

replayFallback.prototype.replayByArgs = function(args) {
    if ("time" in args) {
        if(args.time == 'now') {
            this.replayCurrentMinute();
        }
        else if (args.time.toString().length == 12 && /^\d+$/.test(args.time)){ // follows the format YYYYmmddHHMM
            this.replayByMinuteString(args.time)
        }
        else {
            this.replayByMinuteString(timeUtil.dateTimeAsMinuteString(new Date(Date.parse(args.time)))); // Try parse the date (ISO format should be provided)
        }
    }
    else {
        this.replayCurrentMinute();
    }
};

replayFallback.prototype.replayCurrentMinute = function() {
    this.replayFile(path.join(this.fallbackPath, 'erroneous-events_' + timeUtil.currentDateTimeAsMinuteString() + '.log'));
};

replayFallback.prototype.replayByMinuteString = function(minuteString) {
    if (isNaN(minuteString)) {
        logger.error('Invalid time provided.');
        process.exit(1);
    }
    this.replayFile(path.join(this.fallbackPath, 'erroneous-events_' + minuteString + '.log'));

};

replayFallback.prototype.replayFile = function(filePath) {
    var that = this;
    var parser = JSONStream.parse([])
        .on('data', function(data) {
            that.producer.send(data, function(err){
                if(err) {
                    var filePath = path.join(that.errorPath, 'erroneous-events_' + timeUtil.currentDateTimeAsMinuteString() + '.log');
                    fs.appendFile(filePath, JSON.stringify(data));
                }
            });
        })
        .on('end', function(){
            logger.info('Replaying file [' + filePath + '] finished successfully.');
            process.exit();
        });
    logger.info('Replaying file [' + filePath + '] started...');
    fs.createReadStream(filePath, {encoding: 'utf8'})
        .on('error', function(err){
            if(err.code != 'ENOENT'){ // Errors other than 'file not found'
                logger.error('Error reading file [' + filePath + '], ' + err);
                process.exit(1);
            }
            else {
                logger.info('Fallback file [' + filePath + '] was not found (which is a good thing!)');
                process.exit();
            }
        }).pipe(parser);
};

new replayFallback(config.getOrElse('kanalony.zookeeper.connection_string','127.0.0.1:2181/'));



