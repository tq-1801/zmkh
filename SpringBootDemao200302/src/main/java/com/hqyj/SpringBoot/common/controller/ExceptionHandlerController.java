package com.hqyj.SpringBoot.common.controller;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hqyj.SpringBoot.common.vo.Result;
import com.hqyj.SpringBoot.common.vo.Result.ResultStatus;

@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(value = AuthorizationException.class)
	@ResponseBody
//	public Result<Object> exceptionHandlerFor403() {
//		return new Result<Object>(ResultStatus.FAILED.status, "You have no permission.");
//	}
	public Result<String> exceptionHandlerFor403() {
		return new Result<String>(ResultStatus.FAILED.status, "You have no permission.", "/common/403");
	}
}
