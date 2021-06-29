package org.turbo.giants.accountmanagement;

public abstract class BaseTestCase {

    protected static final String STUDENT_ID = "201811941";
    protected static final String NAME = "John Doe";
    protected static final String COURSE = "BSCSSE";
    protected static final int YEAR = 1;
    protected static final String ADDRESS = "Manila Zoo";
    protected static final String EMAIL = "test123@gmail.com";
    protected static final String CONTACT = "09123456789";

    public Information createBuilderInstance() {
        return new Information.Builder()
                .setStudentId(STUDENT_ID)
                .setName(NAME)
                .setCourse(COURSE)
                .setYearLevel(YEAR)
                .setAddress(ADDRESS)
                .setEmail(EMAIL)
                .setPhoneNumber(CONTACT)
                .build();
    }
}
