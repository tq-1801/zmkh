package com.hqyj.SpringBoot.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hqyj.SpringBoot.account.entity.Customer;
import com.hqyj.SpringBoot.common.vo.SearchVo;

@Mapper
public interface CustomerDao {

	@Select("select * from customer")
	List<Customer> getCustomers();

	@Insert("insert into customer (customer_name) values (#{customerName})")
	@Options(useGeneratedKeys = true, keyColumn = "customer_id", keyProperty = "customerId")
	void insertCustomer(Customer customer);

	@Select("select * from customer customer left join user_customer userCustomer "
			+ "on customer.customer_id = userCustomer.customer_id where userCustomer.user_id = #{userId}")
	List<Customer> getCustomersByUserId(int userId);

	@Insert("insert customer(customer_name,industry,contacts,address,telephone,demand,salesman,results) "
			+ "value(#{customerName},#{industry},#{contacts},#{address},#{telephone},#{demand},#{salesman},#{results})")
	@Options(useGeneratedKeys = true, keyProperty = "customerId", keyColumn = "customer_id")
	void addCustomer(Customer customer);

	@Update("update customer set customer_name = #{customerName} where customer_id = #{customerId}")
	void updateCustomer(Customer customer);

	@Delete("delete from customer where customer_id = #{customerId}")
	void deleteCustomer(int customerId);

	@Select("<script>" + "select * from customer " + "<where> " + "<if test='keyWord != \"\" and keyWord != null'>"
			+ "and customer_name like '%${keyWord}%' " + "</if>" + "</where>" + "<choose>"
			+ "<when test='orderBy != \"\" and orderBy != null'>" + "order by ${orderBy} ${sort}" + "</when>"
			+ "<otherwise>" + "order by customer_id desc" + "</otherwise>" + "</choose>" + "</script>")
	List<Customer> getCustomersBySearchVo(SearchVo searchVo);

	@Select("select * from customer customer left join customer_resource customerResource "
			+ "on customer.customer_id = customerResource.customer_id where customerResource.resource_id = #{resourceId}")
	List<Customer> getCustomersByResourceId(int resourceId);

	@Select("select * from customer where customer_id=#{customerId}")
	Customer getCustomerById(int customerId);

	@Select("select * from customer where customer_name = #{customerName} limit 1")
	Customer getCustomerByCustomerName(String customerName);
}
