'use strict';

module.exports = {

    /**
     * Returns Date and time as UTC String, for example: Wed, 30 Sep 2015 08:08:01 GMT
     * @returns {string}
     */
    currentDateTimeAsUTCString: function() {
        return new Date().toUTCString();
    },

    /**
     * Returns Date and time as ISO String, for example: 2015-09-30T08:08:58.208Z
     * @returns {string}
     */
    currentDateTimeAsISOString: function() {
        return new Date().toISOString();
    },

    /**
     * Returns Date and time rounded by minutes
     * @returns {number}
     */
    currentDateTimeAsMinuteString: function() {
        var d = new Date();
        var minutesDateTime = d.getFullYear()*100000000 + d.getMonth()*1000000 + d.getDate()*10000 + d.getHours()*100 + d.getMinutes();
        return minutesDateTime.toString();
    }
};
