package com.hqyj.SpringBoot.test.service;

import com.hqyj.SpringBoot.test.entity.Country;

public interface CountryService {

	 Country getCountryByCountryId(int countryId);
	 
	 Country getCountryByCountryName(String countryName);
}
