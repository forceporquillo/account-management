package com.force.codes.accountmanagement;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AccountManagerTest {

    @Before
    @Test
    public void valid_email() {
        String email = "force@gmail.com";
        Assert.assertTrue(Util.isValid(email));
    }

    @Test
    public void invalid_email() {
        String email = "force@gmail.com12313asdasd";
        Assert.assertFalse(Util.isValid(email));
    }

    @Test
    public void must_upper_case_per_space() {
        String test = "this is a test";
        Assert.assertEquals("This Is A Test", Util.splitStream(test));
    }
}
