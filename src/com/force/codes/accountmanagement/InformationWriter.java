package com.force.codes.accountmanagement;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class InformationWriter {

    private static final String FILE_NAME = "Information.csv";
    private static CSVWriter CSV_WRITER = null;

    public static void write(Information information) {
        try {
            List<Information> csvInfos = csvReader();

            CSV_WRITER = new CSVWriter(
                    new FileWriter(FILE_NAME), ',',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END
            );

            if (csvInfos.isEmpty()) {
                writeInternal(information);
                return;
            }

            List<String[]> objToString = new ArrayList<>();

            for (Information info : csvInfos) {
                if (info.getStudentId().equals(information.getStudentId())) {
                    if (!info.isEqualNoID(information)) {
                        csvInfos.remove(info);

                        final Information mutate = Util.updateInfo(information, info);
                        final String[] infoToArr = Util.objectToArray(mutate);
                        objToString.add(infoToArr);
                    }
                    break;
                } else {
                    CSV_WRITER.writeNext(Util.objectToArray(information));
                }
            }

            for (Information inf : csvInfos) {
                objToString.add(Util.objectToArray(inf));
            }

            Set<String[]> set = new LinkedHashSet<>(objToString);
            objToString.clear();
            objToString.addAll(set);

            CSV_WRITER.writeAll(objToString);
            CSV_WRITER.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeInternal(Information information) throws IOException {
        CSV_WRITER.writeNext(Util.objectToArray(information));
        CSV_WRITER.close();
    }

    private static List<Information> csvReader() {
        List<Information> information = new ArrayList<>();

        try (CSVReader csvReader = new CSVReader(new FileReader(FILE_NAME))) {
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                final String[] info = new String[7];

                System.arraycopy(nextRecord, 0, info, 0, nextRecord.length);

                information.add(Util.arrayToObject(info));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return information;
    }
}
