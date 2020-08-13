package com.example.demo.modules.account.service.serviceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modules.account.dao.ContractDao;
import com.example.demo.modules.account.entity.Contract;
import com.example.demo.modules.account.service.ContractService;
import com.example.demo.modules.common.vo.SearchVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ContractServiceImpl implements ContractService {

	@Autowired
	private ContractDao contractDao;

	@Override
	public List<Contract> getContracts() {
		return Optional.ofNullable(contractDao.getContracts()).orElse(Collections.emptyList());
	}

	@Override
	public PageInfo<Contract> getContracts(SearchVo searchVo) {

		searchVo.initSearchVo();
		PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
		return new PageInfo<Contract>(
				Optional.ofNullable(contractDao.getContractsBySearchVo(searchVo)).orElse(Collections.emptyList()));

	}
}
