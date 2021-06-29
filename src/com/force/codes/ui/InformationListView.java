package com.force.codes.ui;

import com.force.codes.Information;
import com.force.codes.Util;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

// di pa to tapos

public class InformationListView {

    private static InformationListView INSTANCE;

    private  InformationTableView tableModel;

    private JFrame frame = new JFrame();
    private JTable table;

    private InformationListView() {
        tableModel = new InformationTableView();
        table = new JTable(tableModel);
        initView();
    }

    public static InformationListView getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new InformationListView();
        }

        return INSTANCE;
    }

    public void hideOrShow() {
        if (frame.isVisible()) {
            frame.setVisible(false);
            return;
        }
        frame.setVisible(true);
    }

    private void initView() {

        frame.setTitle("List View Information");

        final JScrollPane pane = new JScrollPane(table);

        table.setFocusable(false);
        table.setBounds(30, 50, 800, 500);

        frame.add(pane);
        frame.setSize(800, 302);
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    public void appendInformation(List<Information> information) {

        for (Information value : information) {
            String[] info = Util.objectToArray(value);

//            for (int j = 0; j < info.length; j++) {
//                tableModel.addRow();
//            }

            tableModel.setValueAt(information, 0, 0);
        }

        tableModel.fireTableDataChanged();
    }

    static class InformationTableView extends DefaultTableModel {

        private static final String[] COLUMN_NAMES = {
                "Student ID", "Name", "Course", "Year Level", "Address", "Email", "Phone Number"
        };

        private static final int MAX_ROW = 15;
        private static final int MAX_COLUMN = 7;

        public InformationTableView() {

        }

        @SuppressWarnings("unused")
        public static int getMaxColumn() {
            return MAX_COLUMN;
        }

        @SuppressWarnings("unused")
        public static int getMaxRow() {
            return MAX_ROW * 15;
        }

        @Override
        public String getColumnName(int column) {
            return COLUMN_NAMES[column];
        }

        @Override
        public int getRowCount() {
            return MAX_ROW;
        }

        @Override
        public int getColumnCount() {
            return MAX_COLUMN;
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return null;
        }
    }
}
