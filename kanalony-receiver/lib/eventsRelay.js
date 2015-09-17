'use strict';

var kafka    = require('kafka-node'),
    url      = require('url'),
    logger   = require('./logger')(module),
    timeUtil = require('./timeUֹtil');

var eventsRelay = function(zkConnectionString, topic){
    this.zkConnectionString = zkConnectionString;
    this.topic    = topic;
    this.connect();
};

eventsRelay.prototype.connect = function() {
    this.client   = new kafka.Client(this.zkConnectionString);
    this.producer = new kafka.Producer(this.client);
    this.producer.on('ready', function () {
        logger.info("Producer is ready!");
    });

    this.producer.on('error', function (err) {
        logger.error("Producer Error:", err);
    });
};

eventsRelay.prototype.isRequestValid = function(req) {
    // TODO - validate the request params to make sure this is a stats request
    return true;
};

eventsRelay.prototype.produceEvent = function(req, cb) {
    this.producer.send(this.buildPayload(req), cb);
};

eventsRelay.prototype.buildPayload = function(req) {
    var payload = [{ topic: this.topic, messages: this.buildMessages(req) }];
    logger.debug("payload:", payload);
    return payload;
};

eventsRelay.prototype.buildMessages = function(req) {
    var params = {};
    try {
        params = url.parse(req.url, true).query;
    }
    catch(err){}
    return [JSON.stringify({ eventTime: timeUtil.currentDateTimeAsISOString(), userAgent: req.headers["user-agent"], referrer: req.headers["referer"],  params: params })];
};

module.exports = eventsRelay;
