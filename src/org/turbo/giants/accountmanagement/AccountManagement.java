package org.turbo.giants.accountmanagement;

public class AccountManagement {

    public static void main(String[] args) {
        AccountManagerState state = AccountManagerStateImpl.getInstance();

        state.setListener(information -> {
            System.out.println("called...");
            InformationWriter.write(information);
        });

    }
}

