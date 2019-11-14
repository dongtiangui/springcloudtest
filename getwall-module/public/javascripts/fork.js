
const socket = require('socket.io');
// process 全局变量 用于子进程
process.on('message',(data)=>{
    console.log(data);
    process.send('子进程');
    setTimeout(()=>{
        process.exit(1);
    },1000);
});
