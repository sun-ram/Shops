var _ = require("underscore")._;
module.exports = {
    list: function(callback) {
        var data = {
            "error": 1,
            "Books": ""
        };
        GLOBAL.connection.query("SELECT * from store", function(err, success, fields) {
          
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