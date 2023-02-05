import javax.swing.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Gui {
    static boolean YesNo = false;

    public Gui() throws FileNotFoundException {
        // Getting list of useful AIs
        Scanner aiReader = new Scanner(new File("ai.txt"));
        ArrayList<Integer> aiNumber = new ArrayList<>();
        ArrayList<String> aiDescripion = new ArrayList<>();
        while(aiReader.hasNext()){
            String[] tempAi = new String[2];
            tempAi = aiReader.nextLine().split(",");
            aiNumber.add(Integer.valueOf(tempAi[0]));
            aiDescripion.add(tempAi[1]);
        }
        aiReader.close();

        // frame setup
        JFrame frame = new JFrame("Intelleship");
        JPanel root = new JPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.PAGE_AXIS));
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.LINE_AXIS));

        JPanel otherInnerPanel = new JPanel();
        otherInnerPanel.setLayout(new BoxLayout(otherInnerPanel, BoxLayout.LINE_AXIS));
        JLabel maybe = new JLabel("RFID used on SSCC: ");
        JButton yes = new JButton("yes");
        JButton no = new JButton("no");
        JLabel test = new JLabel();
        JComboBox test2 = new JComboBox<>();

        yes.addActionListener(e -> {
            test2.removeItem("Observe Event");
            test2.addItem("Observe Event");
        });

        no.addActionListener(e -> {
            test2.removeItem("Observe Event");
        });

        // get information from file and store in arraylist
        Scanner reader = new Scanner(new File("lines.txt"));

        ArrayList<JLabel> allLabels = new ArrayList<>();
        JButton capture = new JButton("Capture");
        capture.addActionListener(e -> {
            // Clearing text
            for (JLabel jLabel : allLabels) {
                jLabel.setText(null);
            }
            test.setText(null);
            test2.removeAllItems();

            // Getting and parsing element string
            String[] tempAi = new String[2];
            String[] partialParseAi = reader.nextLine().split("\\(");
            ArrayList<Integer> ai = new ArrayList<>();
            ArrayList<String> data = new ArrayList<>();

            for (String partial : partialParseAi) {
                if (!partial.equals("")){
                    tempAi = partial.split("\\)");
                    ai.add(Integer.valueOf(tempAi[0]));
                    data.add(tempAi[1]);
                }
            }

            // Displaying information
            FileManager fileManager = new FileManager();
            int i = 0;
            for (; i < ai.size(); i++) {
                for (int j = 0; j < aiNumber.size(); j++){
                    if (ai.get(i) == aiNumber.get(j)){
                        allLabels.get(i).setText(aiDescripion.get(j) + ": " + data.get(i));
                        fileManager.getData().add(aiDescripion.get(j) + ": " + data.get(i));
                    }
                }
            }
            fileManager.writeToFile();

            String[] events = {"Aggregation Event", "Object Ship Event", "Object Receive Event", "Observe Event"};
            test.setText("Event Type: ");
            for (String string : events) {
                test2.addItem(string);
            }
            // if (!YesNo){
            //     test2.removeItemAt(events.length-1);
            // }
        });

        root.add(capture);
        otherInnerPanel.add(maybe);
        otherInnerPanel.add(yes);
        otherInnerPanel.add(no);
        root.add(otherInnerPanel);
        // Setting number of labels
        for (int i = 0; i < 4; i++){
            allLabels.add(new JLabel());
            root.add(allLabels.get(i));
        }

        innerPanel.add(test);
        innerPanel.add(test2);
        root.add(innerPanel);

        // frame settings
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(root);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Gui();
    }
    
}
