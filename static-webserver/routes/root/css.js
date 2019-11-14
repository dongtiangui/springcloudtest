import * as fs from "fs";

const express = require('express');
const router = express.Router();
const mime = require('mime');
/* GET users listing. */
router.get('/', function(req, res, next) {
  const readStream = fs.createReadStream(pathName);
  res.setHeader('Content-Type',mime.lookup(res));
  readStream.pipe(res)
});

module.exports = router;
