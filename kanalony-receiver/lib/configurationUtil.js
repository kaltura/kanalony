'use strict';

module.exports = {
    /**
     * Returns a configuration value by provided key or provided defaultValue if not exists
     * @param configKey
     * @param defaultValue
     * @returns {*|null}
     */
    getOrElse: function(configKey, defaultValue) {
        return this.get(configKey) || defaultValue;
    },

    /**
     * Returns a configuration value by provided key or null if not exists
     * @param configKey
     * @returns {null}
     */
    get: function(configKey){
        // TODO - read configuration from file using an environment variable
        return null;
    }
};
