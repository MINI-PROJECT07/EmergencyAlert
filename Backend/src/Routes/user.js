const express = require("express");
const router = express.Router();
const { createUser,loginUser } = require("../Controllers/userControllers");
const {createUserValidator,loginUserValidator} = require("../Validators/userValidators");

router.post("/createUser",createUserValidator,createUser);
router.post("/login",loginUserValidator,loginUser);

module.exports = router;