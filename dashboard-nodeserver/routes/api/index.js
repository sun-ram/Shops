var express = require('express');
var router = express.Router();

require("./salesOrder.js")(router);
require("./salesOrderLine.js")(router);
require("./merchant.js")(router);
require("./store.js")(router);
require("./address.js")(router);
require("./users.js")(router);

module.exports = router;