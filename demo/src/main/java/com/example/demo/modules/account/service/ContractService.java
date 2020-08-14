package com.example.demo.modules.account.service;

import java.util.List;

import com.example.demo.modules.account.entity.Contract;
import com.example.demo.modules.common.vo.SearchVo;
import com.github.pagehelper.PageInfo;

public interface ContractService {

	List<Contract> getContracts();

	// 分页
	PageInfo<Contract> getContractsBySearchVo(SearchVo searchVo);

}
