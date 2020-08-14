package com.hqyj.SpringBoot.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hqyj.SpringBoot.account.service.UserService;

@Controller
@RequestMapping("/account")
public class AccountController {
	@Autowired
	private UserService userService;

	/**
	 * 127.0.0.1/account/login 登录页面
	 * 
	 * @return
	 */

	@RequestMapping("/login")
	public String loginPage() {
		return "indexSimple";
	}

	/**
	 * http://127.0.0.1/account/logout 退出到登录页面
	 * 
	 * @return
	 */
	@RequestMapping("/logout")
	public String logOut(ModelMap modelMap) {
		userService.logout();
		modelMap.addAttribute("template", "account/login");
		return "indexSimple";
	}

	/**
	 * 127.0.0.1/account/register 注册页面
	 * 
	 * @return
	 */

	@RequestMapping("/register")
	public String registerPag() {
		return "indexSimple";
	}

	/**
	 * http://127.0.0.1/account/shoppingLogin
	 */
	@RequestMapping("/shoppingLogin")
	public String shoppingLoginPage() {
		return "shoppingIndexSimple";
	}

	/**
	 * http://127.0.0.1/account/shoppingRegister
	 */
	@RequestMapping("/shoppingRegister")
	public String shoppingRegister() {
		return "shoppingIndexSimple";
	}

	/**
	 * http://127.0.0.1/account/users 跳转到user页面
	 * 
	 * @return
	 */
	@RequestMapping("/users")
	public String usersPage() {
		return "index";
	}

	/**
	 * http://127.0.0.1/account/roles 跳转到roles页面
	 */
	@RequestMapping("/roles")
	public String rolesPage() {
		return "index";
	}

	/**
	 * http://127.0.0.1/account/contracts 跳转到roles页面
	 */
	@RequestMapping("/contracts")
	public String contractsPage() {
		return "index";
	}

	/**
	 * http://127.0.0.1/account/resources 跳转到resource页面
	 */
	@RequestMapping("/resources")
	public String resourcesPage() {
		return "index";
	}

	/**
	 * http://127.0.0.1/account/profile 跳转到profile页面
	 */
	@RequestMapping("/profile")
	public String profilePage() {
		return "index";
	}
}