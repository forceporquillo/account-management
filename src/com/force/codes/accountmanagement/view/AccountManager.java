package com.force.codes.accountmanagement.view;

import com.force.codes.accountmanagement.AccountInformationException;
import com.force.codes.accountmanagement.Constants;
import com.force.codes.accountmanagement.Information;
import com.force.codes.accountmanagement.Util;
import com.force.codes.accountmanagement.listener.AccountManagerCallback;
import com.force.codes.accountmanagement.listener.AccountManagerState;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AccountManager extends JFrame implements AccountManagerCallback {

    public static final String EMPTY = "";
    private static final String TITLE = "Account Management";
    private static final List<String> courses = new ArrayList<String>() {
        {
            add("BSCSSE");
            add("BCCE");
            add("BSECE");
            add("BSME");
        }
    };
    private JPanel Main;
    private JTextField studentIdTextField;
    private JTextField nameTextField;
    private JComboBox<Integer> levelComboBox;
    private JComboBox<String> courseComboBox;
    private JTextField addressTextField;
    private JTextField emailTextField;
    private JTextField contactTextField;
    private final List<JTextField> fieldList = new ArrayList<JTextField>() {
        {
            add(studentIdTextField);
            add(nameTextField);
            add(addressTextField);
            add(emailTextField);
            add(contactTextField);
        }
    };
    private JButton nextButton;
    private JButton previousButton;
    private JButton updateButton;
    private JButton exitButton;

    // patanggal nalang if di mo need ng list view
    // private final InformationListView informationListView;
    private JButton showButton;
    private final List<JButton> buttonList = new ArrayList<JButton>() {
        {
            add(nextButton);
            add(previousButton);
            add(updateButton);
            add(exitButton);
            add(showButton);
        }
    };
    private AccountManagerState listener;

    public AccountManager(final AccountManagerState state) {

        // patanggal nalang if di mo need ng list view
        // informationListView = InformationListView.getINSTANCE();

        if (state != null) {
            this.listener = state;
        }

        setTitle(TITLE);
        setContentPane(Main);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        pack();

        initListeners();

        for (String course : courses) {
            courseComboBox.addItem(course);
        }

        for (int i = 0; i < 4; i++) {
            levelComboBox.addItem(i + 1);
        }
    }

    private void initListeners() {
        exitButton.addActionListener(e -> System.exit(0));
        updateButton.addActionListener(e -> update());

        nextButton.addActionListener(e -> {
            listener.display();
            System.out.print(listener.next());
        });

        previousButton.addActionListener(e -> {
            System.out.print(listener.previous());
        });

        showButton.setVisible(false);

        showButton.addActionListener(e -> {
            // patanggal nalang if di mo need ng list view
            // informationListView.hideOrShow();
        });

        for (JButton button : buttonList) {
            button.setFocusPainted(false);
        }
    }

    private void messageBox(String body, String title, int errorType) {
        JOptionPane.showMessageDialog(this, body, title, errorType);
    }

    private void update() {
        try {
            Information information = getInformation();
            listener.contains(information);
        } catch (Exception ignored) {
        }
    }

    private Information getInformation() throws AccountInformationException {
        String studentId = getStudentId();
        String name = getUserName();

        int index = courseComboBox.getSelectedIndex();
        String course = courses.get(index);

        int yearLevel = levelComboBox.getSelectedIndex();

        String address = getAddress();
        String email = getEmail();
        String phoneNumber = getPhoneNumber();

        return new Information.Builder()
                .setStudentId(studentId)
                .setName(name)
                .setCourse(course)
                .setYearLevel(yearLevel)
                .setAddress(address)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .build();
    }

    @Override
    public void onAddSuccess(Information information) {
        showMessage(true);
    }

    @Override
    public void onUpdateSuccess() {
        showMessage(false);
    }

    @Override
    public void onInformationExist(Information information) {
        if (information == null) {
            redraw();
            return;
        }

        final String[] infos = new String[]{
                information.getStudentId(),
                information.getName(),
                information.getAddress(),
                information.getEmail(),
                information.getPhoneNumber()
        };

        for (int i = 0; i < fieldList.size(); i++) {
            final String info = infos[i];
            fieldList.get(i).setText(info == null ? EMPTY : info);
        }

        for (int i = 0; i < courses.size(); i++) {
            final String course = information.getCourse();
            if (courses.get(i).equals(course)) {
                courseComboBox.setSelectedIndex(i);
                break;
            }
        }

        int index = 0;

        try {
            index = information.getYearLevel();
        } catch (IndexOutOfBoundsException ignore) {
        }

        levelComboBox.setSelectedIndex(index);
    }

    @Override
    public void invalidateList(List<Information> informationList) {
        // informationListView.appendInformation(informationList);
    }

    private void redraw() {
        fieldList.forEach(field -> field.setText(null));
        courseComboBox.setSelectedIndex(0);
        levelComboBox.setSelectedIndex(0);
    }

    private String dataOrException(String data, String title) throws AccountInformationException {
        fieldEmpty(data);

        String dataOrException;
        try {
            Long studentId = Long.parseLong(data);
            dataOrException = String.valueOf(studentId);
        } catch (NumberFormatException e) {
            messageBox(e.getMessage(), title);
            throw new AccountInformationException(title);
        }

        return dataOrException;
    }

    private String getEmail() throws AccountInformationException {
        String email = emailTextField.getText();

        fieldEmpty(email);

        if (Util.isValid(email))
            return email;
        messageBox(Constants.ERROR_BAD_FORMAT, Constants.INVALID_EMAIL);

        throw new AccountInformationException(Constants.INVALID_EMAIL);
    }

    private String getUserName() throws AccountInformationException {
        String name = nameTextField.getText();
        fieldEmpty(name);

        return Util.splitStream(name);
    }

    private String getStudentId() throws AccountInformationException {
        String studentId = studentIdTextField.getText();

        return dataOrException(studentId, Constants.INVALID_STUDENT_ID);
    }

    private String getPhoneNumber() throws AccountInformationException {
        String phoneNumber = contactTextField.getText();

        return dataOrException(phoneNumber, Constants.INVALID_PHONE);
    }

    private String getAddress() throws AccountInformationException {
        String address = addressTextField.getText();

        return fieldEmpty(address);
    }

    private void messageBox(String body, String title) {
        messageBox(body == null ? Constants.ERROR_EMPTY : body, title, JOptionPane.ERROR_MESSAGE);
    }

    private String fieldEmpty(String field) throws AccountInformationException {
        if (field == null || field.equals("")) {
            messageBox(null, Constants.ERROR);
            throw new AccountInformationException(Constants.ERROR_EMPTY);
        }

        return field;
    }

    private void showMessage(boolean add) {
        messageBox(
                !add ? Constants.MESSAGE_UPDATED : Constants.MESSAGE_SUCCESS,
                !add ? Constants.UPDATE_SUCCESS : Constants.SUCCESS,
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
