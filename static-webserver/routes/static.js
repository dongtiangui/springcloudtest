/**
 * 统一路由入口
 * @type {createApplication}
 */
const express = require('express');
const router = express.Router();

const javascript = require('./root/javascript');

const css = require('./root/css');

const img = require('./root/image');

router.use("/js",javascript);

router.use("/css",css);

router.use("/image",img);


module.exports = router;

