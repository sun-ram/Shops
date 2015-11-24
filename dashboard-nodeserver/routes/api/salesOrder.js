var _ = require("underscore")._;
var uuid = require('node-uuid');
var db = require("../../libs/db/index.js");
module.exports = function(router) {
    router.get("/list", function(req, res) {
        db.salesOrder.list(function(data) {
            res.json(data);
        });
    });
};