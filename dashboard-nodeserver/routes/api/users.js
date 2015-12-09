var _ = require("underscore")._;
var uuid = require('node-uuid');
var db = require("../../libs/db/index.js");
module.exports = function(router) {
    router.get("/users", function(req, res) {
        db.users.getUsers(function(data) {
            res.json(data);
        });
    });
};