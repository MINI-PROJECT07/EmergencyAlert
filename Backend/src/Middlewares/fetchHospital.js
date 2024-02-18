const jwt = require("jsonwebtoken");

const JWT_SECRET = process.env.JWT_SECRET;

const fetchHospital = (req, res, next) => {
    const token = req.header("authToken");
    
    if (!token) {
        res.status(401).send({ error: "Please try using valid token" });
    }
    try {
        const data = jwt.verify(token, JWT_SECRET);
        req.hospital = data.hospital;
        next();
    } catch (error) {
        res.status(401).send({ error: "Please try using valid token" });
    }
}

module.exports = fetchHospital;     