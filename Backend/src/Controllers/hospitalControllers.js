const { validationResult } = require("express-validator");
const { Hospital } = require("../Models/Hospital.js");
const bcrypt = require("bcryptjs");
const jwt = require("jsonwebtoken");
const { getDistance } = require("../util/getDistance.js")
const JWT_SECRET = process.env.JWT_SECRET;

const createHospital = async (req, res) => {
    try {
        let success = false;

        const errors = validationResult(req);
        if (!errors.isEmpty()) {
            console.log(errors);
            return res.status(400).json({ authToken: "", success: success });
        }

        const {
            name,
            email,
            password,
            phoneNo,
            address,
            city,
            state,
            pincode,
            latitude,
            longitude,
            capacity,
            remCapacity,
            isActive,
            info,
        } = req.body;

        const oldHospital = await Hospital.findOne({ email: email });

        if (oldHospital) {
            return res.status(400).json({ success: success, authToken: "" });
        }

        const salt = await bcrypt.genSalt(10);
        const hashedPassword = await bcrypt.hash(password, salt);

        const newHospital = new Hospital({
            name: name,
            email: email,
            password: hashedPassword,
            phoneNo: phoneNo,
            address: address,
            city: city,
            state: state,
            pincode: pincode,
            latitude: latitude,
            longitude: longitude,
            capacity: capacity,
            remCapacity: remCapacity,
            isActive: isActive,
            info: info,
        });

        await newHospital.save();

        const data = {
            hospital: {
                id: newHospital.id,
            },
        };

        const authToken = jwt.sign(data, JWT_SECRET);
        success = true;
        res.status(200).json({ success: success, authToken: authToken });
    } catch (error) {
        res.status(500).json({ success: false, error: error });
        console.error("Create Hospital Error", error);
    }
};

const loginHospital = async (req, res) => {
    try {
        let success = false;

        const errors = validationResult(req);
        if (!errors.isEmpty()) {
            console.log(errors);
            return res.status(400).json({ authToken: "", success: success });
        }

        const { email, password } = req.body;

        const hospital = await Hospital.findOne({ email: email });

        if (!hospital) {
            return res.status(400).json({ authToken: "", success: success });
        }

        const passwordCompare = await bcrypt.compare(
            password,
            hospital.password
        );

        if (!passwordCompare) {
            return res.status(400).json({ authToken: "", success: success });
        }

        const data = {
            hospital: {
                id: hospital.id,
            },
        };

        const authToken = jwt.sign(data, JWT_SECRET);
        success = true;
        res.status(200).json({ success: success, authToken: authToken });
    } catch (error) {
        res.status(500).json({ success: false, error: error });
        console.error("Login Hospital Error", error);
    }
};

const getHospitalInfo = async (req, res) => {
    try {
        const hospital = await Hospital.findById(req.hospital.id).select(
            "-password"
        );
        res.json(hospital);
    } catch (error) {
        console.error("Get Hospital Info Error", error);
        res.status(500).json({ success: false, error: error });
    }
};

const getHospitals = async (req, res) => {
    try {
        const hospitals = await Hospital.find().select("-password");
        res.json(hospitals);
    } catch (error) {
        console.error("Get Hospitals Error", error);
        res.status(500).json({ success: false, error: error });
    }
};

const getHospitalNearest = async (req, res) => {
    try {
        let success = false;

        const errors = validationResult(req);
        if (!errors.isEmpty()) {
            console.log(errors);
            return res.status(400).json({ success: success });
        }
        const { latitude, longitude } = req.body;
        const hospitals = await Hospital.find({
            latitude: { $gte: latitude - 0.45, $lte: latitude + 0.45 },
            longitude: { $gte: longitude - 0.45, $lte: longitude + 0.45 },
        }).select("-password");

        hospitals.sort((a, b) => {
            const aDistance = getDistance(
                a.latitude,
                a.longitude,
                latitude,
                longitude
            );
            const bDistance = getDistance(
                b.latitude,
                b.longitude,
                latitude,
                longitude
            );
            return aDistance - bDistance;
        });

        for (let i = 0; i < hospitals.length; i++) {
            hospitals[i] = {
                ...hospitals[i]._doc,
                distance: getDistance(
                    hospitals[i].latitude,
                    hospitals[i].longitude,
                    latitude,
                    longitude
                ),
            };
        }

        res.status(200).json(hospitals);
    } catch (error) {
        console.error("Get Hospital Nearest Error", error);
        res.status(500).json({ success: false, error: error });
    }
};

const updateHospital = async (req, res) => {
    try {
        const hospital = await Hospital.findById(req.hospital.id);
        if(!hospital){
            res.status(400).send({error:"No hospital found"})
        }
        const {
            name,phoneNo,address,city,state,pincode,latitude,longitude,capacity,isActive,remCapacity,info
        } = req.body

        await Hospital.findByIdAndUpdate(req.hospital.id,{
            name:name,phoneNo:phoneNo,address:address,city:city,state:state,pincode:pincode,latitude:latitude,longitude:longitude,capacity:capacity,isActive:isActive,remCapacity:remCapacity,info:info
        })
        res.status(200).send({
            message: "Hospital information updated successfully",
            user: hospital,
        });
    } catch (error) {
        console.error("Update Hospital Error", error);
        res.status(500).json({ success: false, error: error });
    }
};

module.exports = {
    createHospital,
    loginHospital,
    getHospitalInfo,
    getHospitals,
    getHospitalNearest,
    updateHospital,
};
