package com.id.get.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/** 
 * <br>类 名: FreemakerServlet 
 * <br>描 述: 描述类完成的主要功能 
 * <br>作 者: yufenghe 
 * <br>创 建： 2016年1月18日 
 * <br>版 本：v1.0.0 
 * <br>
 * <br>历 史: (版本) 作者 时间 注释
 */
@Controller
public class FreemakerServlet {
	private static Logger logger = LoggerFactory.getLogger(FreemakerServlet.class);
	
	@RequestMapping(value="/welcome",method=RequestMethod.GET)   
    public ModelAndView getFirstPage() {  
		logger.debug("my first test");
        ModelAndView mv = new ModelAndView(); 
        mv.setViewName("welcome"); 
        mv.addObject("name", "this is freemaker test!!!");  
        return mv;  
    }
}
