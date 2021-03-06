package com.hqyj.SpringBoot.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hqyj.SpringBoot.common.vo.SearchVo;
import com.hqyj.SpringBoot.test.entity.City;
import com.hqyj.SpringBoot.test.entity.Country;

@Mapper
public interface CityDao {
	/**
	 * 配置文件方式
	 * application.properties
	 * mybatis.type-aliases-package=com.hqyj.demo.modules.*.entity
	 * mybatis.mapper-locations=classpath:mapper/*Mapper.xml
	 * 读取cityMapper.xml，方法名和mapper中设置的id一致
	 */
	
	/**
	 * #{countryId} ---- prepared statement, select * from m_city where country_id = ?
	 * ${countryId} ---- statement, select * from m_city where country_id = 1
	 */
  List<City>getCitiesByCountryId(int countryID);
  @Select("select * from m_city where country_id = #{countryId}")
  
  
  List<City>getCitiesByCountryId2(int countryID);
  @Select("select * from m_city where city_name=#{cityName} and local_city_name=#{localCityName}")
  @Results(id = "cityResult", value = {
			@Result(column = "country_id", property = "countryId"),
			@Result(column = "country_id", property = "country", 
					javaType = Country.class, 
					one = @One(select = "com.hqyj.SpringBoot.test.dao.CountryDao.getCountryByCountryId"))}
	)
	City getCityByName( String cityName, String localCityName);
  
  @Select("<script>" + 
			"select * from m_city "
			+ "<where> "
			+ "<if test='keyWord != \"\" and keyWord != null'>"
			+ " and (city_name like '%${keyWord}%' or local_city_name like '%${keyWord}%') "
			+ "</if>"
			+ "</where>"
			+ "<choose>"
			+ "<when test='orderBy != \"\" and orderBy != null'>"
			+ " order by ${orderBy} ${sort}"
			+ "</when>"
			+ "<otherwise>"
			+ " order by city_id desc"
			+ "</otherwise>"
			+ "</choose>"
			+ "</script>")
	List<City> getCitiesBySearchVo(SearchVo searchVo);
  
  @Insert("insert into m_city (city_name, local_city_name, country_id, date_created) "
			+ "values (#{cityName}, #{localCityName}, #{countryId}, #{dateCreated})")
	@Options(useGeneratedKeys = true, keyColumn = "city_id", keyProperty = "cityId")
	void insetCity(City city);
	
  @Update("update m_city set local_city_name = #{localCityName} where city_id = #{cityId}")
	void updateCity(City city);
  
  @Delete("delete from m_city where city_id = #{cityId}")
	void deleteCity(int cityId);
}
