const { body } = require("express-validator");

const createUserValidator = [
    body("name", "Name must be at least 5 characters long").isLength({
        min: 5,
        max: 50,
    }),
    body("email", "Invalid email").isEmail(),
    body("password", "Password must be at least 8 characters long").isLength({
        min: 8,
    }),
    body("phoneNo", "Invalid phone number").isMobilePhone(),
];

const loginUserValidator = [
    body("email", "Invalid email").isEmail(),
    body("password", "Password must be at least 8 characters long").isLength({
        min: 8,
    }),
]

module.exports = { createUserValidator,loginUserValidator };
