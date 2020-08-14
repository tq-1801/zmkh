package com.hqyj.SpringBoot.test.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hqyj.SpringBoot.common.vo.Result;
import com.hqyj.SpringBoot.common.vo.SearchVo;
import com.hqyj.SpringBoot.test.entity.City;

public interface CityService {
	
	List<City>getCitiesByCountryId(int countryID);
	
	City getCityByName(String cityName, String localCityName);
	
PageInfo<City> getCitiesByPage(int currentPage, int pageSize, int countryId);
	
PageInfo<City> getCitiesBySearchVo(SearchVo searchVo);

Result<City> insetCity(City city);

Result<City> updateCity(City city);

Result<Object> deleteCity(int cityId);

Object migrateCitiesByCountryId(int countryId);
}
