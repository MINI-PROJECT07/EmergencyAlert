const mongoose = require("mongoose");
const { Schema } = mongoose;


const Contact = {
    name:{
        type:String,
        required:true
    },
    phoneNo:{
        type:String,
        required:true
    },
}

const UserSchema = new Schema({
  name: {
    type: String,
    required: true,
  },
  email: {
    type: String,
    required: true,
  },
  password:{
    type:String,
    required:true,
  },
  phoneNo: {
    type: String,
    required: true,
  },
  emergencyNumbers:{
    type:[Contact],
    required:true,
  },
  bloodGroup:{
    type:String,
    required:true,
  },
  disease:{
    type:String,
    required:true,
  },
  otherInfo:{
    type:String,
    required:true,
  },
  date:{
    type:Date,
    required:true,
    default:Date.now
  }
});

const User = mongoose.model("user", UserSchema);
module.exports = {User};