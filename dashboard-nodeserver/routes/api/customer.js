var _ = require("underscore")._;
var uuid = require('node-uuid');
var db = require("../../libs/db/index.js");
module.exports = function(router) {
    router.get("/customer", function(req, res) {
        db.customer.getCustomer(function(data) {
            res.json(data);
        });
    });
};