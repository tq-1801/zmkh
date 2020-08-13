package com.example.demo.modules.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.example.demo.modules.account.entity.Contract;
import com.example.demo.modules.common.vo.SearchVo;

@Mapper
public interface ContractDao {

	@Select("select * from contract")
	List<Contract> getContracts();

	@Insert("insert into contract (contract_name) values (#{contractName})")
	@Options(useGeneratedKeys = true, keyColumn = "contract_id", keyProperty = "contractId")
	void insertContract(Contract contract);

	@Select("<script>" + "select * from contract " + "<where> " + "<if test='keyWord != \"\" and keyWord != null'>"
			+ " and (contract_name like '%${keyWord}%') " + "</if>" + "</where>" + "<choose>"
			+ "<when test='orderBy != \"\" and orderBy != null'>" + " order by ${orderBy} ${sort}" + "</when>"
			+ "<otherwise>" + " order by contract_id desc" + "</otherwise>" + "</choose>" + "</script>")

	List<Contract> getContractsBySearchVo(SearchVo searchVo);
}
