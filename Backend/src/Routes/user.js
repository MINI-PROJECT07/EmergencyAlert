const express = require("express");
const router = express.Router();
const { createUser,loginUser,getUserInfo} = require("../Controllers/userControllers");
const {createUserValidator,loginUserValidator} = require("../Validators/userValidators");
const {fetchUser} = require("../Middlewares/fetchUser")

router.post("/createUser",createUserValidator,createUser);
router.post("/login",loginUserValidator,loginUser);
router.post("/getUserInfo",fetchUser,getUserInfo);

module.exports = router;