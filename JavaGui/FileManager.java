import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FileManager {

    private File outputFile;
    private PrintWriter printWriter;
    private DateTimeFormatter formatter;
    final String date;
    private ArrayList<String> data;
    public FileManager() {
        formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        date = LocalDate.now().format(formatter);
        outputFile = new File("scans_" + date);
            try {
                printWriter = new PrintWriter(outputFile);
            } catch (FileNotFoundException e) {
                // What went wrong.
                e.printStackTrace();
            }
            data = new ArrayList<String>();
    }

    public void writeToFile() {
        for(String dataString : data) {
            printWriter.println(dataString);
        }
        printWriter.flush();
        printWriter.close();
    }

    public PrintWriter getPrintWriter() {
        return printWriter;
    }

    public ArrayList<String> getData() {
        return data;
    }


}