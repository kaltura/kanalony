'use strict';

var kafka    = require('kafka-node'),
    logger   = require('./logger')(module);

var kafkaProducer = function(zkConnectionString, onReady){
    this.zkConnectionString = zkConnectionString;
    this.onReady = onReady || function(){};
    this.connect();
};

kafkaProducer.prototype.connect = function() {
    var that = this;
    this.client   = new kafka.Client(this.zkConnectionString);
    this.producer = new kafka.Producer(this.client, {requireAcks: 1, ackTimeoutMs: 50});
    this.producer.on('ready', function () {
        logger.info("Producer is ready!");
        that.onReady();
    });

    this.producer.on('error', function (err) {
        logger.warn("Producer Error:", err);
    });
};

kafkaProducer.prototype.send = function(payload, cb) {
    this.producer.send(payload, cb);
};

module.exports = kafkaProducer;

