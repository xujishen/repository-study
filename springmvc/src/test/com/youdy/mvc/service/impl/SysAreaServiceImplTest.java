package com.youdy.mvc.service.impl;

import com.youdy.bean.SysAreaBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
// locations 与 classes 只配置一个
@ContextConfiguration(/*classes={SysAreaServiceImpl.class}, */locations={"classpath:config/spring-servlet.xml"})
public class SysAreaServiceImplTest {

    @Autowired
    SysAreaServiceImpl areaService;

    @Test
    public void searchAreas() {
        SysAreaBean bean = new SysAreaBean();
        bean.setPageCnt(10);
        bean.setPageNumber(1);
        List<SysAreaBean> sysAreaBeans = areaService.searchAreas(bean);
        System.out.println(sysAreaBeans);
    }
}
