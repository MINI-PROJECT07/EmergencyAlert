const express = require("express");
const cors = require("cors");
const bodyParser = require("body-parser");
const app = express();
const connectDB = require("./db");

require("dotenv").config();

const port = process.env.PORT || 5000;
const MONGO_URI = process.env.MONGO_URI;

app.use(cors());
app.use(bodyParser.json());

app.use("/api", require("./src/Routes/index"))

app.get("/", (req, res) => {
  res.send("Hello World");
});

app.get("/demo",(req,res)=>{
  res.send({message:"It's working .",id:1})
})

const startServer = async () => {
    await connectDB(MONGO_URI);
    app.listen(port, () => {
        console.log(`Server is running on port ${port}`);
    });
    
}

startServer();