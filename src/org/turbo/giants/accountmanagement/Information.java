package org.turbo.giants.accountmanagement;

import java.io.Serializable;
import java.util.Objects;

public class Information implements Serializable {

    private String      studentId;
    private String      name;
    private String      course;
    private int         yearLevel;
    private String      address;
    private String      email;
    private String      phoneNumber;

    public Information() {
        this(null, null, null, 0, null, null, null);
    }

    public Information(
            String studentId,
            String name,
            String course,
            int yearLevel,
            String address,
            String email,
            String phoneNumber
    ) {
        this.studentId = studentId;
        this.name = name;
        this.course = course;
        this.yearLevel = yearLevel;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public int getYearLevel() {
        return yearLevel;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setYearLevel(int yearLevel) {
        this.yearLevel = yearLevel;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Information{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", course='" + course + '\'' +
                ", yearLevel=" + yearLevel +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Information that = (Information) o;
        return yearLevel == that.yearLevel &&
                Objects.equals(studentId, that.studentId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(course, that.course) &&
                Objects.equals(address, that.address) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phoneNumber, that.phoneNumber);
    }

    public boolean isEqualNoID(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Information that = (Information) o;
        return yearLevel == that.yearLevel &&
                Objects.equals(name, that.name) &&
                Objects.equals(course, that.course) &&
                Objects.equals(address, that.address) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, name, course, yearLevel, address, email, phoneNumber);
    }

    public static class Builder {

        private String      studentId;
        private String      name;
        private String      course;
        private int         yearLevel;
        private String      address;
        private String      email;
        private String        phoneNumber;

        public Builder setStudentId(String studentId) {
            this.studentId = studentId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setCourse(String course) {
            this.course = course;
            return this;
        }

        public Builder setYearLevel(int yearLevel) {
            this.yearLevel = yearLevel;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Information build() {
            return new Information(
                    studentId, name, course, yearLevel,
                    address, email, phoneNumber
            );
        }
    }
}
