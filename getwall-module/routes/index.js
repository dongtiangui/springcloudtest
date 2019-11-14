const express = require('express');
const router = express.Router();

/* GET home page. */
router.post('/', function(req, res, next) {

  console.log(req.body);
  res.writeHead(200,{
    "content-type":"text/plain"
  });
  res.write("hello nodejs");
  res.end();
});

module.exports = router;
