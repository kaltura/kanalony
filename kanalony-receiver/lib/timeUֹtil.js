'use strict';

module.exports = {
    currentDateTimeAsUTCString: function() {
        return new Date().toUTCString();
    },
    currentDateTimeAsISOString: function() {
        return new Date().toISOString();
    }
};
