package com.example.demo.modules.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

	/**
	 * 127.0.0.1/account/login
	 * 
	 * 登录
	 */
	@RequestMapping("/login")
	public String loginPage() {
		return "indexSimple";
	}

	/**
	 * 127.0.0.1/account/customer
	 * 
	 * 跳转到customer页面
	 * 
	 * @return
	 */
	@RequestMapping("/customer")
	public String customerPage() {
		return "index";
	}

	/**
	 * 127.0.0.1/account/contract
	 * 
	 * 跳转到contract页面
	 * 
	 * @return
	 */
	@RequestMapping("/contract")
	public String contractPage() {
		return "index";
	}
}
