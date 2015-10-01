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
     * Returns current date and time rounded by minutes
     * @returns {number}
     */
    currentDateTimeAsMinuteString: function() {
        return this.dateTimeAsMinuteString(new Date());
    },

    /**
     * Returns date and time rounded by minutes according to the provided value
     * @param d
     * @returns {string}
     */
    dateTimeAsMinuteString: function(d) {
        if (isNaN(d)) { return d; }
        var minutesDateTime = d.getFullYear()*100000000 + d.getMonth()*1000000 + d.getDate()*10000 + d.getHours()*100 + d.getMinutes();
        return minutesDateTime.toString();
    }
};
