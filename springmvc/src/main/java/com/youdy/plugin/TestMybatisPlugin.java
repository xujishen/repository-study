package com.youdy.plugin;

import com.youdy.bean.SysAreaBean;
import com.youdy.mvc.service.impl.SysAreaServiceImpl;
import org.apache.ibatis.plugin.*;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Su Jishen on 2017/1/10 14:58.
 */
@Intercepts(@Signature(type = SysAreaServiceImpl.class, method = "searchAreas", args = {SysAreaBean.class}))
public class TestMybatisPlugin implements Interceptor, Serializable {

	private static final long serialVersionUID = -4387265975123292699L;

	@Override
    public Object intercept(Invocation invocation) throws Throwable {
        final Object target = invocation.getTarget();
        final Method method = invocation.getMethod();
        final Object[] args = invocation.getArgs();
        System.out.println("what a fucking man !! ");
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
    	System.out.println((o));
    	// o ==> 某一个 #Executor, #StatementHandler, #ResultSetHandler, ParameterHandler
        // this ==> 实现了 Interceptor接口的实现类
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
        if (properties != null) {
            Set keys = properties.keySet();
            Iterator it = keys.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                System.out.println(properties.get(String.valueOf(next)));
            }
        }
    }
}
