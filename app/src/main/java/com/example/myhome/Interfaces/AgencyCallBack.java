package com.example.myhome.Interfaces;

import com.example.myhome.model.Agencies;

public interface AgencyCallBack {

    void onFailure(String errorMessage);
    void onAgencySuccess(Agencies agency, Boolean isUpdate);
}
