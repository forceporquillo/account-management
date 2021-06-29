package com.force.codes;

import java.util.LinkedHashMap;
import java.util.List;

abstract class BaseStateManager {

    protected static final LinkedHashMap<String, Information> map = new LinkedHashMap<>();

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

    private void itemExist(Information information) {
        callback.onInformationExist(information);
        exist(information);
    }

    abstract void exist(Information information);

    private void update(String keyId, Information information) {
        map.put(keyId, information);
        callback.onUpdateSuccess();
    }

    private void add(Information information) {
        map.put(information.getStudentId(), information);
        callback.onAddSuccess(information);
    }

    protected int invalidate(int index) {
        Object[] infos = map.values().toArray();

        if (infos.length == 0) {
            return index;
        }

        for (int i = 0; i < infos.length; i++) {
            if (i == index) {
                callback.onInformationExist((Information) infos[i]);
                break;
            }
        }

        return index;
    }

}
