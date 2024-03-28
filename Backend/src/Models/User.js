const mongoose = require("mongoose");
const { Schema } = mongoose;

const Contact = {
    name: {
        type: String,
        required: true,
        default: "",
    },
    phoneNo: {
        type: String,
        required: true,
        default: "",
    },
};

const UserSchema = new Schema({
    name: {
        type: String,
        required: true,
    },
    email: {
        type: String,
        required: true,
    },
    password: {
        type: String,
        required: true,
    },
    phoneNo: {
        type: String,
        required: true,
    },
    emergencyNumbers: {
        type: [Contact],
        default:[],
    },
    bloodGroup: {
        type: String,
        default: "",
    },
    age:{
        type:Number,
        default:18
    },
    gender:{
        type:String,
        default:""
    },
    disease: {
        type: [String],
        default: [],
    },
    otherInfo: {
        type: String,
        default: "",
    },
    date: {
        type: Date,
        required: true,
        default: Date.now,
    },
});

const User = mongoose.model("user", UserSchema);
module.exports = { User };
// (16.847188, 74.596146)
