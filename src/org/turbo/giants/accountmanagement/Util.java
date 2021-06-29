package org.turbo.giants.accountmanagement;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Util {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\." +
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";

    public static boolean isValid(String email) {
        if (email == null)
            return false;

        return Pattern.compile(EMAIL_REGEX)
                .matcher(email)
                .matches();
    }

    public static String[] objectToArray(Information information) {
        return new String[]{
                information.getStudentId(),
                information.getName(),
                information.getCourse(),
                String.valueOf(information.getYearLevel()),
                information.getAddress(),
                information.getEmail(),
                information.getPhoneNumber()
        };
    }

    public static Information arrayToObject(String[] strings) {
        if (strings.length != 7) {
            throw new IllegalArgumentException();
        }

        return new Information(
                strings[0],
                strings[1],
                strings[2],
                Integer.parseInt(strings[3]),
                strings[4],
                strings[5],
                strings[6]
        );
    }

    public static Information updateInfo(Information newInfo, Information oldInfo) {
        oldInfo.setStudentId(newInfo.getStudentId());
        oldInfo.setName(newInfo.getName());
        oldInfo.setCourse(newInfo.getCourse());
        oldInfo.setYearLevel(newInfo.getYearLevel());
        oldInfo.setAddress(newInfo.getAddress());
        oldInfo.setEmail(newInfo.getEmail());
        oldInfo.setPhoneNumber(newInfo.getPhoneNumber());

        return oldInfo;
    }

    public static String splitStream(String string) {
        UnaryOperator<String> capitalize = str ->
                str.substring(0, 1).toUpperCase() +
                        str.substring(1).toLowerCase();

        return Stream.of(
                string.split(" ")
        ).map(capitalize)
                .collect(
                        Collectors.joining(" ")
                );
    }
}
