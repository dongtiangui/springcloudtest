import axios from 'axios'

axios.defaults.timeout = 50000

//设置请求拦截器
// axios.interceptors.request.use(
//   config=>{
//       config.data = JSON.stringify(config.data); // 避免造成数据错乱
//       config.headers = {
//         'content-Type': 'application/x-www-form-urlencoded'
//       };
//       return config;
//   },
//   error => {
//       return Promise.reject(error);
//   }
// )
// 设置响应拦截器

// 封装 http请求 restful风格 post put delete patch

// Promise node 异步编程的一个特性

export function get (url,params = {}) {
    return new Promise((resolve,reject)=>{
        axios.get(url,{
          params:params // 请求参数
        }).then(
        //  指定当前实例状态发生变化的时候回调函数
          (response)=>{
              // resolve (value) 传值给 then 函数
              resolve(response.data)
            }
          //  catch reject 处理异步编程请求异常
          ).catch((error)=>{
            reject(error)
        })

    })
}

// 封装post请求

export function post (url,data={}) {
    return new Promise((resolve,reject)=>{
          axios.post(url,data)
            .then(response=>{
              resolve(response.data);
          })
            .catch(error=>{
              reject(error)
          })
    })
}

export function patch (url,data={}) {
  return new Promise((resolve,reject)=>{
    axios.post(url,data)
      .then(response=>{
        resolve(response.data);
      })
      .catch(error=>{
        reject(error)
      })
  })
}


export function put (url,data={}) {
  return new Promise((resolve,reject)=>{
    axios.post(url,data)
      .then(response=>{
        resolve(response.data);
      })
      .catch(error=>{
        reject(error)
      })
  })
}




