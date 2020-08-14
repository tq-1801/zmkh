package com.hqyj.SpringBoot.account.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hqyj.SpringBoot.account.entity.Resource;
import com.hqyj.SpringBoot.common.vo.Result;
import com.hqyj.SpringBoot.common.vo.SearchVo;


public interface ResourceService {

	Result<Resource> editResource(Resource resource);
	
	Result<Resource> deleteResource(int resourceId);
	
	PageInfo<Resource> getResources(SearchVo searchVo);
	
	List<Resource> getResourcesByRoleId(int roleId);
	
	Resource getResourceById(int resourceId);
}
