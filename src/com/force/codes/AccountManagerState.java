package com.force.codes;

public interface AccountManagerState {

    void contains(final Information information);

    int next();

    int previous();

    void display();
}
