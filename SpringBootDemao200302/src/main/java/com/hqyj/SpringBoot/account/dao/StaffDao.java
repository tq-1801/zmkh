package com.hqyj.SpringBoot.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hqyj.SpringBoot.account.entity.Staff;
import com.hqyj.SpringBoot.common.vo.SearchVo;

@Mapper
public interface StaffDao {

	@Select("select * from staff")
	List<Staff> getStaffs();

	@Insert("insert into staff (staff_name) values (#{staffName})")
	@Options(useGeneratedKeys = true, keyColumn = "staff_id", keyProperty = "staffId")
	void insertStaff(Staff staff);

	@Select("select * from staff staff left join user_staff userStaff "
			+ "on staff.staff_id = userStaff.staff_id where userStaff.user_id = #{userId}")
	List<Staff> getStaffsByUserId(int userId);

	// 增加数据
	@Insert("insert staff(staff_name,sex,telephone,email,department)"
			+ " value(#{staffName},#{sex},#{telephone},#{email},#{department})")
	@Options(useGeneratedKeys = true, keyProperty = "staffId", keyColumn = "staff_id")
	void addStaff(Staff staff);

	@Update("update staff set staff_name = #{staffName},sex = #{sex},telephone = #{telephone},email = #{email},department = #{department} where staff_id = #{staffId}")
	void updateStaff(Staff staff);

	@Delete("delete from staff where staff_id = #{staffId}")
	void deleteStaff(int staffId);

	@Select("<script>" + "select * from staff " + "<where> " + "<if test='keyWord != \"\" and keyWord != null'>"
			+ "and staff_name like '%${keyWord}%' " + "</if>" + "</where>" + "<choose>"
			+ "<when test='orderBy != \"\" and orderBy != null'>" + "order by ${orderBy} ${sort}" + "</when>"
			+ "<otherwise>" + "order by staff_id desc" + "</otherwise>" + "</choose>" + "</script>")
	List<Staff> getStaffsBySearchVo(SearchVo searchVo);

	@Select("select * from staff staff left join staff_resource staffResource "
			+ "on staff.staff_id = staffResource.staff_id where staffResource.resource_id = #{resourceId}")
	List<Staff> getStaffsByResourceId(int resourceId);

	@Select("select * from staff where staff_id=#{staffId}")
	Staff getStaffById(int staffId);

	@Select("select * from staff where staff_name = #{staffName} limit 1")
	Staff getStaffByStaffName(String staffName);
}
