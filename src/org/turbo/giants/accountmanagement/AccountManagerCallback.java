package org.turbo.giants.accountmanagement;

import java.util.List;

public interface AccountManagerCallback {

    void onAddSuccess(Information information);

    void onUpdateSuccess();

    void onInformationExist(Information information);

    void invalidateList(List<Information> informationList);
}