'use strict';

var winston = require('winston');
var config = require('./configurationUtil');

winston.emitErrs = true;
var path = require('path');
var mkdirp = require('mkdirp');

var logPath = config.getOrElse('kanalony.receiver.log_file_path','kanalony-receiver.log');
var logFullPath = path.resolve('.', logPath);

mkdirp.sync(path.dirname(logFullPath));

var getLabel = function(callingModule) {
    var parts = callingModule.filename.split('/');
    return parts[parts.length - 2] + '/' + parts.pop();
};

var logger = function (callingModule) {
    var loggerTimestamp = function() {
        return new Date().toUTCString();
    };
    var loggerFormatter = function(options) {
        return '['+ options.level.toUpperCase() +'] '+ options.timestamp() +': ' + options.label + ' - ' + (undefined !== options.message ? options.message : '') +
            (options.meta && Object.keys(options.meta).length ? '\n\t'+ JSON.stringify(options.meta) : '' );
    };
    return new winston.Logger({
        transports: [
            new (winston.transports.File)({
                name: 'info-file',
                filename: logPath,
                level: config.get('kanalony.receiver.log_level.file') || config.getOrElse('kanalony.receiver.log_level','info'),
                json: false,
                colorize: false,
                handleExceptions: false,
                humanReadableUnhandledException: true,
                label: getLabel(callingModule),
                timestamp: loggerTimestamp,
                formatter: loggerFormatter
            }),
            new winston.transports.Console({
                level: config.get('kanalony.receiver.log_level.console') || config.getOrElse('kanalony.receiver.log_level','info'),
                handleExceptions: false,
                json: false,
                colorize: true,
                label: getLabel(callingModule),
                humanReadableUnhandledException: true,
                timestamp: loggerTimestamp,
                formatter: loggerFormatter
            })
        ],
        exitOnError: false
    });
};
module.exports = logger;