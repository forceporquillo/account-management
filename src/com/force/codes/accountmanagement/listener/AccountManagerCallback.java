package com.force.codes.accountmanagement.listener;

import com.force.codes.accountmanagement.Information;

import java.util.List;

public interface AccountManagerCallback {

    void onAddSuccess(Information information);

    void onUpdateSuccess();

    void onInformationExist(Information information);

    void invalidateList(List<Information> informationList);
}