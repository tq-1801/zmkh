package com.hqyj.SpringBoot.account.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hqyj.SpringBoot.account.dao.ContractDao;
import com.hqyj.SpringBoot.account.entity.Contract;
import com.hqyj.SpringBoot.account.service.ContractService;
import com.hqyj.SpringBoot.common.vo.Result;
import com.hqyj.SpringBoot.common.vo.Result.ResultStatus;
import com.hqyj.SpringBoot.common.vo.SearchVo;

@Service
public class ContractServiceImpl implements ContractService {

	@Autowired
	private ContractDao contractDao;

	@Override
	public List<Contract> getContracts() {
		return Optional.ofNullable(contractDao.getContracts()).orElse(Collections.emptyList());
	}

	@Override
	@Transactional
	public Result<Contract> editContract(Contract contract) {
		Contract contractTemp = contractDao.getContractByContractName(contract.getContractName());
		if (contractTemp != null && contractTemp.getContractId() != contract.getContractId()) {
			return new Result<Contract>(ResultStatus.FAILED.status, "Contract name is repeat.");
		}

		if (contract.getContractId() > 0) {
			contractDao.updateContract(contract);
		} else {
			contractDao.addContract(contract);
		}

		return new Result<Contract>(ResultStatus.SUCCESS.status, "success", contract);
	}

	@Override
	@Transactional
	public Result<Contract> deleteContract(int contractId) {
		contractDao.deleteContract(contractId);
		return new Result<Contract>(ResultStatus.SUCCESS.status, "");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public PageInfo<Contract> getContracts(SearchVo searchVo) {
		searchVo.initSearchVo();
		PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
		return new PageInfo(
				Optional.ofNullable(contractDao.getContractsBySearchVo(searchVo)).orElse(Collections.emptyList()));
	}

	@Override
	public List<Contract> getContractsByUserId(int userId) {
		return contractDao.getContractsByUserId(userId);
	}

	@Override
	public List<Contract> getContractsByResourceId(int resourceId) {
		return contractDao.getContractsByResourceId(resourceId);
	}

	@Override
	public Contract getContractById(int contractId) {
		return contractDao.getContractById(contractId);
	}
}
