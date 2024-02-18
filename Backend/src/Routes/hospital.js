const express = require("express");
const router = express.Router();

const { createHospital, loginHospital, getHospitalInfo ,getHospitals,getHospitalNearest} = require("../Controllers/hospitalControllers");
const { createHospitalValidator, loginHospitalValidator, getHospitalNearestValidator } = require("../Validators/hospitalValidators");
const fetchHospital = require("../Middlewares/fetchHospital");

router.post("/createHospital", createHospitalValidator, createHospital);
router.post("/loginHospital", loginHospitalValidator, loginHospital);
router.post("/getHospitalInfo",fetchHospital,getHospitalInfo);
router.get("/getHospitals",getHospitals)
router.post("/getHospitalNearest",getHospitalNearestValidator,getHospitalNearest);


module.exports = router;