package com.example.myhome;

import java.util.List;

public interface PropertiesCallback {
    void onPropertiesSuccess(List<PropertySummary> properties);
    void onPropertiesSuccess(Properties propiedad);
    void onPropertiesFailure(String errorMessage);

    void onPropertiesSuccess(Long propertyId);
}
