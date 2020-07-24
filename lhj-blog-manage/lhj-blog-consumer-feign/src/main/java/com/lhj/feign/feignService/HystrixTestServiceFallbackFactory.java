package com.lhj.feign.feignService;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * hsytrix断容器
 * @author 17822
 */
@Component //不要忘记添加
public class HystrixTestServiceFallbackFactory implements FallbackFactory<FeignTestService> {


    @Override
    public FeignTestService create(Throwable throwable) {
        return new FeignTestService() {
            @Override
            public List discovery() {
                List err = new ArrayList();
                err.add("服务调用异常，请联系管理员！");
                return err;
            }
        };
    }

}
