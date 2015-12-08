var _ = require("underscore")._;
var uuid = require('node-uuid');
var db = require("../../libs/db/index.js");
module.exports = function(router) {
    router.get("/address", function(req, res) {
        db.store.getStore(function(data) {
            res.json(data);
        });
    });
};