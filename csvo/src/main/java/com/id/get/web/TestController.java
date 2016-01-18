package com.id.get.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.id.get.model.Test;


@Controller
@RequestMapping(value = "/test")
public class TestController {

	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public String init(Model model) {
		logger.debug("跳转到test.jsp页面");
		return "dashbord/index";
	}
	@RequestMapping(value = "/init1", method = RequestMethod.GET)
	public String init1(Model model) {
		logger.debug("跳转到test.jsp页面");
		return "jsp/test";
	}
	@RequestMapping(value = "/init2", method = RequestMethod.GET)
	public String init2(Model model) {
		return "test.vm";
	}
	@RequestMapping(value = "/init3", method = RequestMethod.GET)
	public String init3(Model model) {
		return "/ftl/hello.ftl";
	}

	@RequestMapping(value = "/submit")
	@ResponseBody
	public String submit(Test test) {
		System.out.println("test:" + test.toString());
		return "";
	}

}
