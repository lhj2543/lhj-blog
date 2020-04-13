/**
 *  业务配置
 * */

const env = process.env.NODE_ENV;


const Setting = {
    /**
     * 基础配置
     * */
    // 网页标题的后缀
    titleSuffix: '',
    // 路由模式，可选值为 history 或 hash
    routerMode: 'history',
    // 页面切换时，是否显示模拟的进度条
    showProgressBar: true,
    // 接口请求地址
    apiURL: env === 'development' ? 'http://127.0.0.1:9090/rivermanage' : '/rivermanage',
    // 接口请求返回错误时，弹窗的持续时间，单位：秒
    modalDuration: 3,
    // 接口请求返回错误时，弹窗的类型，可选值为 Message 或 Notice
    errorModalType: 'Message',
    // Cookies 默认保存时间，单位：天
    cookiesExpires: 1,
    
};

export default Setting;
