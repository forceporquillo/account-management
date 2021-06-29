package org.turbo.giants.accountmanagement;

import org.turbo.giants.accountmanagement.listener.AccountManagerCallback;

import java.util.LinkedHashMap;
import java.util.List;

abstract class BaseAccountManager {

    private static final LinkedHashMap<String, Information> map = new LinkedHashMap<>();

    private AccountManagerCallback callback;

    public void setCallback(AccountManagerCallback callback) {
        this.callback = callback;
    }

    protected void containsInfo(final Information information) {

        final String keyId = information.getStudentId();

        if (map.containsKey(keyId)) {
            final Information info = map.get(keyId);

            if (!information.equals(info)) {
                update(info.getStudentId(), information);
            }

        }

        if (map.isEmpty() || !map.containsKey(keyId)) {
            add(information);
        }


        callback.invalidateList((List<Information>) map.values());
    }

    abstract void exist(Information information);

    private void update(String keyId, Information information) {
        map.put(keyId, information);
        exist(information);
        callback.onUpdateSuccess();
    }

    private void add(Information information) {
        map.put(information.getStudentId(), information);
        exist(information);
        callback.onAddSuccess(information);
    }

    protected void invalidate(int index) {
        Object[] infos = map.values().toArray();

        boolean isExist = false;

        if (infos.length == 0) {
            return;
        }

        for (int i = 0; i < infos.length; i++) {
            if (i == index) {
                notifyData((Information) infos[i]);
                System.out.print("Item Exist at Index " + i + "\n" + infos[i].toString());
                isExist = true;
                break;
            }
        }

        if (!isExist) {
            notifyData(null);
        }

    }

    private void notifyData(Information notification) {
        callback.onInformationExist(notification);
    }

    public static LinkedHashMap<String, Information> getMap() {
        return map;
    }
}
