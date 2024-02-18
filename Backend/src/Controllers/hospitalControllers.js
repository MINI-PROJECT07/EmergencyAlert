const {validationResult} = require('express-validator');
const {Hospital} = require('../Models/Hospital.js');
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');

const JWT_SECRET = process.env.JWT_SECRET;

const createHospital = async (req, res) => {
    try {
        let success = false;

        const errors = validationResult(req);
        if (!errors.isEmpty()) {
            console.log(errors);
            return res.status(400).json({authToken: '', success: success});
        }

        const {
            name,
            email,
            password,
            phoneNo
        } = req.body;

        const oldHospital = await Hospital.findOne({email: email});

        if (oldHospital) {
            return res
                .status(400)
                .json({success: success, authToken: ''});
        }

        const salt = await bcrypt.genSalt(10);
        const hashedPassword = await bcrypt.hash(password, salt);

        const newHospital = new Hospital({
            name: name,
            email: email,
            password: hashedPassword,
            phoneNo: phoneNo,
        });

        await newHospital.save();

        const data = {
            hospital: {
                id: newHospital.id,
            },
        };

        const authToken = jwt.sign(data, JWT_SECRET);
        success = true;
        res.status(200).json({success: success, authToken: authToken});
    } catch (error) {
        res.status(500).json({success: false, error: error});
        console.error('Create Hospital Error', error);
    }
}

const loginHospital = async (req, res) => {
    try {
        let success = false;

        const errors = validationResult(req);
        if (!errors.isEmpty()) {
            console.log(errors);
            return res.status(400).json({authToken: '', success: success});
        }

        const {email, password} = req.body;

        const hospital = await Hospital.findOne({email: email});

        if (!hospital) {
            return res.status(400).json({authToken: '', success: success});
        }

        const passwordCompare = await bcrypt.compare(password, hospital.password);

        if (!passwordCompare) {
            return res.status(400).json({authToken: '', success: success});
        }

        const data = {
            hospital: {
                id: hospital.id,
            },
        };

        const authToken = jwt.sign(data, JWT_SECRET);
        success = true;
        res.status(200).json({success: success, authToken: authToken});
    } catch (error) {
        res.status(500).json({success: false, error: error});
        console.error('Login Hospital Error', error);
    }
}


const getHospitalInfo = async (req, res) => {
    try {
        const hospital = await Hospital.findById(req.hospital.id).select('-password');
        res.json(hospital);
    } catch (error) {
        console.error('Get Hospital Info Error', error);
        res.status(500).json({success: false, error: error});
    }
}

const getHospitals = async (req, res) => {
    try {
        const hospitals = await Hospital.find().select('-password');
        res.json(hospitals);
    } catch (error) {
        console.error('Get Hospitals Error', error);
        res.status(500).json({success: false, error: error});
    }
}

module.exports = {createHospital, loginHospital, getHospitalInfo,getHospitals};