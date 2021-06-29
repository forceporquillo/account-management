package org.turbo.giants.accountmanagement;

import org.turbo.giants.accountmanagement.view.AccountManager;

import java.util.List;

interface FileInputLister {

    void invalidate(Information information);
}


public class AccountManagerStateImpl extends BaseAccountManager implements AccountManagerState {

    private static final Object STATE_LOCK = new Object();

    private static AccountManagerStateImpl INSTANCE = null;

    // optional if mag threading ka convert mo into atomic integer.
    private int index = 0;

    private FileInputLister lister;

    private AccountManagerStateImpl() {
        super.setCallback(new AccountManager(this));
    }

    public static AccountManagerStateImpl getInstance() {
        if (INSTANCE == null) {
            synchronized (STATE_LOCK) {
                INSTANCE = new AccountManagerStateImpl();
            }
        }
        return INSTANCE;
    }

    @Override
    void exist(Information information) {
        System.out.println("hiii...");
        lister.invalidate(information);
    }

    @Override
    public void contains(Information information) {
        containsInfo(information);
    }

    @Override
    public boolean next() {
        int size = getMap().size();

        if (size >= index) {
            index++;
        }

        invalidate(index);

        return size == index;
    }

    @Override
    public void display() {
        if (getMap().isEmpty()) {
            return;
        }

        System.out.println("Display");
        getMap().values().forEach(information -> System.out.println(information.toString()));
    }

    @Override
    public void setListener(FileInputLister listener) {
        this.lister = listener;
    }

    @Override
    public boolean previous() {
        if (index <= 0) {
            invalidate(0);

        }

        index--;
        invalidate(index);

        return index == size()
                || getMap().isEmpty();
    }

    private int size() {
        return getMap().size();
    }
}