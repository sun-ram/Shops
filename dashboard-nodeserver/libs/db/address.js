var _ = require("underscore")._;
module.exports = {
    getAddress: function(callback) {
        var data = {
            "error": 1,
            "Books": ""
        };
        GLOBAL.connection.query("SELECT * from address", function(err, success, fields) {
          
            if (success.length != 0) {
                data["error"] = 0;
                data["Books"] = success;
                callback(data);

            } else {
                data["Books"] = 'No books Found..';
                callback(data);
            }
        });
    }
};