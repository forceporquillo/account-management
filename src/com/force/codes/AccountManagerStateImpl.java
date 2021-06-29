package com.force.codes;

import com.force.codes.ui.AccountManager;


public class AccountManagerStateImpl extends BaseStateManager implements AccountManagerState {

    // pantaggal nalang netong LOCK if di ka mag thread.
    private static final Object STATE_LOCK = new Object();

    private static AccountManagerStateImpl INSTANCE = null;

    private int index = 0;

    private AccountManagerStateImpl() {
        super.setCallback(new AccountManager(this));
    }

    public static AccountManagerStateImpl getInstance() {
        if (INSTANCE == null) {
            // pantaggal nalang if di ka mag threading
            synchronized (STATE_LOCK) {
                INSTANCE = new AccountManagerStateImpl();
            }
        }
        return INSTANCE;
    }

    @Override
    void exist(Information information) {

    }

    @Override
    public void contains(Information information) {
        containsInfo(information);
    }

    @Override
    public int next() {
        index++;

        invalidate(index);
        return invalidate(index);
    }

    @Override
    public void display() {

    }

    @Override
    public int previous() {
        if (index <= 0) {
            return 0;
        }

        index--;

        return invalidate(index);
    }
}