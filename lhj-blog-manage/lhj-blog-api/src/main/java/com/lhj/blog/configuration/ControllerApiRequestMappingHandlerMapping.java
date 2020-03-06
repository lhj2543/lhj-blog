package com.lhj.blog.configuration;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author zhanghaishan
 * @version V1.0
 * org.mountcloud.mvc.common.handler
 * TODO:
 * 2020年1月6日.
 */
public class ControllerApiRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    /**
     * 是否打印mapping
     */
    private boolean logMappingPath = false;

    /*日志*/
    Logger logger = LoggerFactory.getLogger(getClass());

    public ControllerApiRequestMappingHandlerMapping(){};
    public ControllerApiRequestMappingHandlerMapping(boolean logMappingPath) {
        this.logMappingPath = logMappingPath;
    }

    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo mappingInfo = super.getMappingForMethod(method, handlerType);
        Class<?> superClass = handlerType.getSuperclass();
        mappingInfo = appendParentRequestMapping(superClass,mappingInfo);
        logMapping(mappingInfo);
        return mappingInfo;
    }

    /**
     * 添加夫类的mapping
     * @param handlerType
     * @param mappingInfo
     * @return
     */
    protected RequestMappingInfo appendParentRequestMapping(Class<?> handlerType,RequestMappingInfo mappingInfo) {
        if(handlerType==null) {
            return mappingInfo;
        }
        RequestMapping parentRequestMapping = handlerType.getAnnotation(RequestMapping.class);
        if(parentRequestMapping!=null&&parentRequestMapping.value().length>0) {
            //使用path工具向前追加夫类的path
            mappingInfo = RequestMappingInfo.paths(parentRequestMapping.value()).build().combine(mappingInfo);
        }
        return appendParentRequestMapping(handlerType.getSuperclass(),mappingInfo);
    }

    /**
     * 由于spring boot2不打印mapping了，不习惯，就自己打印一下，但是有些系统mapping也不打印，有空再研究怎么打印
     * @param info
     */
    private void logMapping(RequestMappingInfo info) {
        if(!logMappingPath||info==null) {
            return;
        }
        //此处等价Logger.info
        logger.info("mapping path:"+info.toString());
    }
}
