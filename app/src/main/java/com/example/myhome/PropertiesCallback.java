package com.example.myhome;

import java.util.List;

public interface PropertiesCallback {
    void onPropertiesSuccess(List<PropertySummary> properties);
    void onPropertiesFailure(String errorMessage);

}
