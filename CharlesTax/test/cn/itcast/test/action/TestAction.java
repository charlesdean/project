package cn.itcast.test.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.test.service.TestService;

public class TestAction extends ActionSupport {
	@Resource
	TestService service;
	@Override
	public String execute() throws Exception {
		
		service.say();
		return SUCCESS;
	}
}
