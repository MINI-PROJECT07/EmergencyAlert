const { validationResult } = require("express-validator");
const { User } = require("../Models/User.js");
const bcrypt = require("bcryptjs");
const jwt = require("jsonwebtoken");

const JWT_SECRET = process.env.JWT_SECRET;

const createUser = async (req, res) => {
    try {
        let success = false;

        const errors = validationResult(req);
        if (!errors.isEmpty()) {
            console.log(errors);
            return res.status(400).json({ authToken:"", success: success });
        }

        const {
            name,
            email,
            password,
            phoneNo
        } = req.body;

        const oldUser = await User.findOne({ email: email });

        if (oldUser) {
            return res
                .status(400)
                .json({ success: success, authToken:""});
        }

        const salt = await bcrypt.genSalt(10);
        const hashedPassword = await bcrypt.hash(password, salt);

        const newUser = new User({
            name: name,
            email: email,
            password: hashedPassword,
            phoneNo: phoneNo,
        });

        await newUser.save();

        const data = {
            user: {
                id: newUser.id,
            },
        };

        const authToken = jwt.sign(data, JWT_SECRET);
        success = true;
        res.status(200).json({ success: success, authToken: authToken });
    } catch (error) {
        res.status(500).json({ success: false, error: error });
        console.error("Create User Error", error);
    }
};

const loginUser = async (req, res) => {
    try {
        let success = false;

        const errors = validationResult(req);
        if (!errors.isEmpty()) {
            console.log(errors);
            return res.status(400).json({ authToken:"", success });
        }

        const { email, password } = req.body;

        let user = await User.findOne({ email });
        if (!user) {
            console.log("no user with this email");
            return res
                .status(400)
                .json({ authToken:"", success });
        }

        const passwordcompare = await bcrypt.compare(password, user.password);
        if (!passwordcompare) {
            console.log("Try to login with correct credentials");
            return res.status(400).json({
                error: "Try to login with correct credentials",
                success,
            });
        }

        const data = {
            user: {
                id: user.id,
            },
        };
        const authToken = jwt.sign(data, JWT_SECRET);
        success = true;
        res.json({ authToken: authToken, success: success });
    } catch (error) {
        let success = false;
        console.error(error);
        res.status(500).send({
            error: "Some internal server error ocurred",
            success: success,
        });
    }
};

module.exports = { createUser, loginUser };
