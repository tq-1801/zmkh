package com.hqyj.SpringBoot.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hqyj.SpringBoot.account.entity.Contract;
import com.hqyj.SpringBoot.common.vo.SearchVo;

@Mapper
public interface ContractDao {

	@Select("select * from contract")
	List<Contract> getContracts();

	@Insert("insert into contract (contract_name)  values (#{contractName})")
	@Options(useGeneratedKeys = true, keyColumn = "contract_id", keyProperty = "contractId")
	void insertContract(Contract contract);

	@Select("select * from contract contract left join user_contract userContract "
			+ "on contract.contract_id = userContract.contract_id where userContract.user_id = #{userId}")
	List<Contract> getContractsByUserId(int userId);

	@Insert("insert into contract(contract_name, category, person, dates, moneyt, paymentv, period)value(#{contractName}, #{category}, #{person}, #{dates}, #{moneyt}, #{paymentv}, #{period})")
	@Options(useGeneratedKeys = true, keyProperty = "contractId", keyColumn = "contract_id")
	void addContract(Contract contract);

	// 更新数据
	@Update("update contract set contract_name = #{contractName},category = #{category},person = #{person},dates = #{dates}, "
			+ "moneyt = #{moneyt},paymentv = #{paymentv},period = #{period} where contract_id = #{contractId}")
	void updateContract(Contract contract);

	@Delete("delete from contract where contract_id = #{contractId}")
	void deleteContract(int contractId);

	@Select("<script>" + "select * from contract " + "<where> " + "<if test='keyWord != \"\" and keyWord != null'>"
			+ "and contract_name like '%${keyWord}%' " + "</if>" + "</where>" + "<choose>"
			+ "<when test='orderBy != \"\" and orderBy != null'>" + "order by ${orderBy} ${sort}" + "</when>"
			+ "<otherwise>" + "order by contract_id desc" + "</otherwise>" + "</choose>" + "</script>")
	List<Contract> getContractsBySearchVo(SearchVo searchVo);

	@Select("select * from contract contract left join contract_resource contractResource "
			+ "on contract.contract_id = contractResource.contract_id where contractResource.resource_id = #{resourceId}")
	List<Contract> getContractsByResourceId(int resourceId);

	@Select("select * from contract where contract_id=#{contractId}")
	Contract getContractById(int contractId);

	@Select("select * from contract where contract_name = #{contractName} limit 1")
	Contract getContractByContractName(String contractName);
}
