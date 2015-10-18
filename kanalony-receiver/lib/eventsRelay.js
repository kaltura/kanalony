'use strict';

var fs            = require('fs'),
    url           = require('url'),
    path          = require('path'),
    logger        = require('./logger')(module),
    config        = require('./configurationUtil'),
    timeUtil      = require('./timeUֹtil'),
    KafkaProducer = require('./kafkaProducer');

var eventsRelay = function(zkConnectionString, topic){
    this.producer = new KafkaProducer(zkConnectionString);
    this.topic = topic;
    this.fallbackPath = config.getOrElse('kanalony.receiver.fallback.path','/tmp/fallback');
};

eventsRelay.prototype.isRequestValid = function(req) {
    // TODO - validate the request params to make sure this is a stats request
    return true;
};

eventsRelay.prototype.produceEvent = function(req) {
    var payload = this.buildPayload(req);
    var that = this;
    this.producer.send(payload, function(err){
        if(err) {
            var filePath = path.join(that.fallbackPath, 'erroneous-events_' + timeUtil.currentDateTimeAsMinuteString() + '.log');
            fs.appendFile(filePath, JSON.stringify(payload), function(err){
                if(err) {
                    logger.error("Unable to write event to fallback storage  " + filePath + ', ' + err + '\n' + JSON.stringify(payload));
                }
            });
        }
    });
};

eventsRelay.prototype.buildPayload = function(req) {
    var payload = [{ topic: this.topic, messages: this.buildMessages(req) }];
    logger.debug("payload:", payload);
    return payload;
};

eventsRelay.prototype.buildMessages = function(req) {
    var params = {};
    var remoteIp = "";
    try {
        params = url.parse(req.url, true).query;
        remoteIp = req.connection.remoteAddress;
    }
    catch(err){}
    return [JSON.stringify({ eventTime: timeUtil.currentDateTimeAsISOString(), userAgent: req.headers["user-agent"], remoteIp: remoteIp, referrer: req.headers["referer"],  params: params })];
};

module.exports = eventsRelay;
