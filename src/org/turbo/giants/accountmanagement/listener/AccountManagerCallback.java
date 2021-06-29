package org.turbo.giants.accountmanagement.listener;

import org.turbo.giants.accountmanagement.Information;

import java.util.List;

public interface AccountManagerCallback {

    void onAddSuccess(Information information);

    void onUpdateSuccess();

    void onInformationExist(Information information);

    void invalidateList(List<Information> informationList);
}