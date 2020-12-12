package com.ribbon.boostrap.fallback;

import com.ribbon.boostrap.clients.FeignServices;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
/**
 * 全局容错降级机制回滚
 */
@Component
public class ErrorFallbackFactory
        implements FallbackFactory<FeignServices> {
    @Override
    public FeignServices create(Throwable cause) {
        return cause::getMessage;
    }
}
