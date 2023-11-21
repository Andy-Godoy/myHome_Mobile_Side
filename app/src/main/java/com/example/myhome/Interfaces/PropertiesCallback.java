package com.example.myhome.Interfaces;

import com.example.myhome.Api.Properties;
import com.example.myhome.Api.PropertySummary;

import java.util.List;

public interface PropertiesCallback {
    void onPropertiesSuccess(List<PropertySummary> properties);
    void onPropertiesSuccess(Properties propiedad);
    void onPropertiesFailure(String errorMessage);

    void onPropertiesSuccess(Long propertyId);



}
