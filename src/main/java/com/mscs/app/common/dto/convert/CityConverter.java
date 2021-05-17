package com.mscs.app.common.dto.convert;

import com.mscs.app.common.dto.req.ReqCityDto;
import com.mscs.app.web.model.City;

public class CityConverter {
	
	public static City buildCityInforObj(ReqCityDto dto) {
		City city =new City();
		city.setFhdm(dto.getFhdm());
		city.setSjdm(dto.getSjdm());
		city.setSjz(dto.getSjz());;
		return city;
	}

}
