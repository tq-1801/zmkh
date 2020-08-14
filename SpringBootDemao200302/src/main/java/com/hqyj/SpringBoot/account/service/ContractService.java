package com.hqyj.SpringBoot.account.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hqyj.SpringBoot.account.entity.Contract;
import com.hqyj.SpringBoot.common.vo.Result;
import com.hqyj.SpringBoot.common.vo.SearchVo;

public interface ContractService {

	List<Contract> getContracts();

	Result<Contract> editContract(Contract contract);

	Result<Contract> deleteContract(int contractId);

	PageInfo<Contract> getContracts(SearchVo searchVo);

	List<Contract> getContractsByUserId(int userId);

	List<Contract> getContractsByResourceId(int resourceId);

	Contract getContractById(int contractId);
}
