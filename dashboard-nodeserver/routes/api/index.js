var express = require('express');
var router = express.Router();

require("./salesOrder.js")(router);

module.exports = router;