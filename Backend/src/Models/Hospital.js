const mongoose = require("mongoose");
const { Schema } = mongoose;

const HospitalScehma = new Schema({
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
    address: {
        type: String,
        required: true,
    },
    city: {
        type: String,
        required: true,
    },
    state: {
        type: String,
        required: true,
    },
    pincode: {
        type: String,
        required: true,
    },
    latitude: {
        type:Schema.Types.Decimal128,
        required: true,
    },
    longitude: {
        type:Schema.Types.Decimal128,
        required: true,
    },
    capacity:{
        type:Number,
        default:0,
        required:true,
    },
    isActive:{
        type:Boolean,
        default:true,
        required:true,
    },
    remCapacity:{
        type:Number,
        default:0,
        required:true,
    },
    info:{
        type:String,
        default:"",
        required:true,
    },
    date: {
        type: Date,
        required: true,
        default: Date.now,
    },
})

const Hospital = mongoose.model("hospital", HospitalScehma);
module.exports = { Hospital };