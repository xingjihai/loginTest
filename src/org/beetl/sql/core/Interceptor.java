package org.beetl.sql.core;

import java.util.Map;

/**
 * @author daihy
 *         Created on 2016/6/1.
 */
public interface Interceptor {

    void beforeRun(String sqlId,Map<String, Object> paras);

    void before(InterceptorContext ctx);

    void after(InterceptorContext ctx);

}
