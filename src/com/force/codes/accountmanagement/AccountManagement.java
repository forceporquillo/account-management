package com.force.codes.accountmanagement;

import com.force.codes.accountmanagement.listener.AccountManagerState;

public class AccountManagement {

    public static void main(String[] args) {
        AccountManagerState state = AccountManagerStateImpl.getInstance();

        state.setListener(InformationWriter::write);

    }
}

