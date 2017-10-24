package com.boot.yourtest.rest.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created By shen on 2017-10-24 22:06
 */
@RestController
@RequestMapping(value = "/common")
public class CommonController extends BaseController {

    @RequestMapping(value = "/page/{pageName}" + REST_PATTERN, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView gotoPage(HttpServletRequest request, HttpServletResponse response,
                                 @PathVariable(value = "pageName") String pageName) {
        String pagePath = pageName.replaceAll("\\.", "/");
        return new ModelAndView();
    }

}
