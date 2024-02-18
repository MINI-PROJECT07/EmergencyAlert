const {body} = require('express-validator');

const createHospitalValidator = [
    body("name", "Name must be at least 5 characters long").isLength({
        min: 5,
        max: 50,
    }),
    body("email", "Invalid email").isEmail(),
    body("password", "Password must be at least 8 characters long").isLength({
        min: 8,
    }),
    body("phoneNo", "Invalid phone number").isMobilePhone(),
    body("address", "Address must be at least 5 characters long").isLength({
        min: 5,
        max: 100,
    }),
    body("city", "City must be at least 3 characters long").isLength({
        min: 3,
        max: 50,
    }),
    body("state", "State must be at least 3 characters long").isLength({
        min: 3,
        max: 50,
    }),
    body("pincode", "Invalid pincode").isLength({min: 6, max: 6}).isNumeric(),
    body("latitude", "Invalid latitude").isDecimal(),
    body("longitude", "Invalid longitude").isDecimal(),
    body("info", "Info must be at least 5 characters long").isLength({
        min: 5,
        max: 100,
    }),
]

const loginHospitalValidator = [
    body("email", "Invalid email").isEmail(),
    body("password", "Password must be at least 8 characters long").isLength({
        min: 8,
    }),
]

const getHospitalNearestValidator = [
    body("latitude", "Invalid latitude").exists().isDecimal(),
    body("longitude", "Invalid longitude").exists().isDecimal(),
]

module.exports = { createHospitalValidator, loginHospitalValidator ,getHospitalNearestValidator};