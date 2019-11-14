const cluters = require('child_process');

const num = require('os').cpus().length;
const fork = cluters.fork('./fork');

fork.send("父进程"+num);

fork.on('message',function (data) {
    console.log(data)
});
fork.on('exit',()=>{

    console.log("子进程在1秒后关闭")
});
