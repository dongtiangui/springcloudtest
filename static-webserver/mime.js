const path = require('path');

const mimeTypes = {
    "css": "text/css",
    "gif": "image/gif",
    "html": "text/html",
    "ico": "image/x-icon",
    "jpeg": "image/jpeg",
    "js": "text/javascript"
};

const lookup = (pathname)=>{
    //返回文件路径的扩展名
    let suffix = path.extname(pathname);
    suffix = suffix.split(".").pop();
    return mimeTypes[suffix]||mimeTypes["txt"]
};

module.exports = {
  lookup
};
