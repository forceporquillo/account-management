package org.turbo.giants.accountmanagement.listener;

import org.turbo.giants.accountmanagement.Information;

public interface AccountManagerState {

    void contains(final Information information);

    boolean next();

    boolean previous();

    void display();

    void setListener(FileInputListener listener);
}
