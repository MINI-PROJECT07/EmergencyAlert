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
    body(
        "emergencyNumbers",
        "Emergency numbers must be an array of length 2"
    ).isArray(),
    body("bloodGroup", "Enter blood group").isLength({ min: 1, max: 3 }),
    body("disease", "Enter disease").isLength({ min: 1, max: 50 }),
    body("otherInfo", "Enter other info").isLength({ min: 1, max: 50 }),
];

const loginUserValidator = [
    body("email", "Invalid email").isEmail(),
    body("password", "Password must be at least 8 characters long").isLength({
        min: 8,
    }),
]

module.exports = { createUserValidator,loginUserValidator };
