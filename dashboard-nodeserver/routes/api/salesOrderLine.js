var _ = require("underscore")._;
var uuid = require('node-uuid');
var db = require("../../libs/db/index.js");
module.exports = function(router) {
    router.get("/salesOrderLine", function(req, res) {
        db.salesOrderLine.getSalesOrderLine(function(data) {
            res.json(data);
        });
    });
};