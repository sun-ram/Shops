var _ = require("underscore")._;
var uuid = require('node-uuid');
var db = require("../../libs/db/index.js");
module.exports = function(router) {
    router.get("/merchant", function(req, res) {
        db.merchant.getMerchant(function(data) {
            res.json(data);
        });
    });
};