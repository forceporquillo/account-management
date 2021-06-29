package org.turbo.giants.accountmanagement;

import org.turbo.giants.accountmanagement.listener.AccountManagerState;

public class AccountManagement {

    public static void main(String[] args) {
        AccountManagerState state = AccountManagerStateImpl.getInstance();

        state.setListener(InformationWriter::write);

    }
}

