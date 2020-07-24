package com.lhj.feign.feignService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author 17822
 */
@FeignClient(value = "LHJ-BLOG-MANAGE",fallbackFactory=HystrixTestServiceFallbackFactory.class)
public interface FeignTestService {

    @RequestMapping(value = "/rivermanage/site/index/discovery", method = RequestMethod.GET)
    public List discovery();

}
