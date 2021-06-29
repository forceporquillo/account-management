package org.turbo.giants.accountmanagement;

public interface AccountManagerState {

    void contains(final Information information);

    boolean next();

    boolean previous();

    void display();

    void setListener(FileInputLister listener);
}
