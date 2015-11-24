var _ = require("underscore")._;
var uuid = require('node-uuid');
var db = require("../../libs/db/index.js");
module.exports = function(router) {
    router.get("/salesOrder", function(req, res) {
        db.salesOrder.getSalesOrder(function(data) {
            res.json(data);
        });
    });
};