package com.hb.style.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

@Component
public class MyZuulFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterType.pre.name();
    }

    @Override
    public int filterOrder() {
        //执行时序， 值是0,1,2....N等自然数。 0的优先级最高，即最先执行
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //是否需要执行run函数
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //拦截器的具体实现
        System.out.println("...MyFilter...");
        return null;
    }

    /**
     * ZuulFilter的过滤器类型
     * pre表示路由之前， routing表示路由当中， post表示路由之后， error表示路由发生错误
     */
    enum FilterType {
        pre, routing, post, error
    }

}
