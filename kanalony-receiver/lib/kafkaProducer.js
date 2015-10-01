'use strict';

var kafka    = require('kafka-node'),
    logger   = require('./logger')(module);

var kafkaProducer = function(zkConnectionString){
    this.zkConnectionString = zkConnectionString;
    this.connect();
};

kafkaProducer.prototype.connect = function() {
    this.client   = new kafka.Client(this.zkConnectionString);
    this.producer = new kafka.Producer(this.client);
    this.producer.on('ready', function () {
        logger.info("Producer is ready!");
    });

    this.producer.on('error', function (err) {
        logger.warn("Producer Error:", err);
    });
};

kafkaProducer.prototype.send = function(payload, cb) {
    this.producer.send(payload, cb);
}

module.exports = kafkaProducer;

