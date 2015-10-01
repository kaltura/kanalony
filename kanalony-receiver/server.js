'use strict';

var http      = require('http'),
    kanalony  = require('./kanalony'),
    timeUtil  = kanalony.TimeUtil,
    config    = kanalony.ConfigurationUtil,
    logger    = kanalony.Logger(module),
    relay     = new kanalony.EventsRelay(config.getOrElse('kanalony.zookeeper.connection_string','127.0.0.1:2181/'),
                                         config.getOrElse('kanalony.receiver.kafka_topic','player-events')),
    host      = config.getOrElse('kanalony.receiver.server_host','0.0.0.0'),
    port      = config.getOrElse('kanalony.receiver.server_port','5555');

http.createServer(function (req, res) {
    if (relay.isRequestValid(req)){
        writeResponse(res, 200, timeUtil.currentDateTimeAsUTCString()); // Client doesn't need to wait for response from Kafka
        relay.produceEvent(req);
    }
    else {
        writeResponse(res, 400, 'Bad request');
    }

}).listen(port, host);

function writeResponse(res, code, body) {
    res.writeHead(code, {'Content-Type': 'text/plain'});
    res.end(body);
}

logger.info('Server is running at http://' + host + ':' + port + '/');

