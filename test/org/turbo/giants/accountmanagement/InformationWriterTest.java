package org.turbo.giants.accountmanagement;

import com.opencsv.CSVWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;

public class InformationWriterTest extends BaseTestCase {

    private static final String[] header = {
            "Student ID", "Name", "Course", "Year", "Address", "Email", "Contact"
    };

    private static final String FILE_NAME = "Information.csv";

    private static FileWriter FILE_WRITER = null;
    private static CSVWriter CSV_WRITER;

    @Before
    @Test
    public void test() {
        try {
            FILE_WRITER = new FileWriter(
                    FILE_NAME,
                    true
            );
            CSV_WRITER = new CSVWriter(FILE_WRITER, ',',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
        } catch (IOException e) {
            e.printStackTrace();
        }

        CSV_WRITER.writeNext(new String[]{"Name", "Class", "Marks"});
    }

    @After
    @Test
    public void append() {
        CSV_WRITER.writeNext(Util.objectToArray(createBuilderInstance()));
    }
}