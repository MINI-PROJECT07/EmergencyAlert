const mongoose = require("mongoose");

const connectDB = async (MONGO_URI) => {
  try {
    await mongoose.connect(MONGO_URI)
    console.log("MongoDB connection SUCCESS");
  } catch (error) {
    console.error("MongoDB connection FAIL");
  }
};

module.exports = connectDB;
