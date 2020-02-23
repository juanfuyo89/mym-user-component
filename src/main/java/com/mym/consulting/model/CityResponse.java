package com.mym.consulting.model;

import com.mym.consulting.entities.Ciudad;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityResponse extends Response {

    public CityResponse(String message, List<Ciudad> cityList){
        super.setResponseMessage(message);
        this.cityList = cityList;
    }

    private List<Ciudad> cityList;

}
