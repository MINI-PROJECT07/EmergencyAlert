const express = require("express");
const router = express.Router();


router.use("/user",require("./user"));
router.use("/hospital",require("./hospital"));

module.exports = router