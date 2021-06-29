package com.force.codes.accountmanagement.listener;

import com.force.codes.accountmanagement.Information;

public interface AccountManagerState {

    void contains(final Information information);

    boolean next();

    boolean previous();

    void display();

    void setListener(FileInputListener listener);
}
