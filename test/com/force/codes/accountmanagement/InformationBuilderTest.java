package com.force.codes.accountmanagement;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * This is just a basic test to test out constructing information using builder design pattern.
 */

public class InformationBuilderTest extends BaseTestCase {

    private Information information;


    @Test
    @Before
    public void createInfoInstance() {
        information = createBuilderInstance();
    }

    @Test
    public void test_get_student_id() {
        assertEquals(information.getStudentId(), STUDENT_ID);
    }

    @Test
    public void test_get_name() {
        assertEquals(information.getName(), NAME);
    }

    @Test
    public void test_get_course() {
        assertEquals(information.getCourse(), COURSE);
    }

    @Test
    public void test_get_year_level() {
        assertEquals(information.getYearLevel(), YEAR);
    }

    @Test
    public void test_get_address() {
        assertEquals(information.getAddress(), ADDRESS);
    }

    @Test
    public void test_get_email_address() {
        assertEquals(information.getEmail(), EMAIL);
    }

    @Test
    public void test_get_phone_number() {
        assertEquals(information.getPhoneNumber(), CONTACT);
    }
}
