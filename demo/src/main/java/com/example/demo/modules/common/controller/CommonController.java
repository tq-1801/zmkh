package com.example.demo.modules.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/common")
public class CommonController {

	/**
	 * 127.0.0.1/common/dashboard 跳转到dashboard页面
	 * 
	 * @return
	 */

	@RequestMapping("/dashboard")
	public String dashboardPage() {
		return "index";
	}
}
