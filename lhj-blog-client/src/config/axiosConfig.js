
import axios from 'axios' //引入axios 


const apiURL = 'http://127.0.0.1:9090/river';
//const apiURL = '/api/river';

// 创建axios实例
/* const service = axios.create({
    baseURL: process.env.BASE_API, // node环境的不同，对应不同的baseURL
    timeout: 5000, // 请求的超时时间
    //设置默认请求头，使post请求发送的是formdata格式数据// axios的header默认的Content-Type好像是'application/json;charset=UTF-8',我的项目都是用json格式传输，如果需要更改的话，可以用这种方式修改
    // headers: {  
      // "Content-Type": "application/x-www-form-urlencoded"
    // },
    withCredentials: true // 允许携带cookie
  }) */

// axios默认配置
axios.defaults.timeout = 10000;   // 超时时间
axios.defaults.baseURL = apiURL;  // 默认地址
axios.defaults.withCredentials = true ;// 允许携带cookie


/* axios.defaults.transformRequest = (data)=>{

} */

// 添加请求拦截器
axios.interceptors.request.use(
    function (config) {
        // 在发送请求之前做些什么
        config.headers['Content-Type'] = 'application/json;charset=UTF-8';


        return config;
     }, function (error) {
        // 对请求错误做些什么
        return Promise.reject(error);
     }
);

// 添加响应拦截器
axios.interceptors.response.use(
    function (response) {
         // 对响应数据做点什么
        return response;
     }, 
    function (error) {
        // 对响应错误做点什么
        return Promise.reject(error);
    }
);


export default axios