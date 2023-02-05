import javax.swing.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Gui {
    public Gui() throws FileNotFoundException{
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
        JFrame frame = new JFrame("Title Here");
        JPanel root = new JPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.PAGE_AXIS));

        // get information from file and store in arraylist
        Scanner reader = new Scanner(new File("lines.txt"));

        ArrayList<JLabel> allLabels = new ArrayList<>();
        JButton capture = new JButton("Capture");
        capture.addActionListener(e -> {
            // Clearing text
            for (JLabel jLabel : allLabels) {
                jLabel.setText(null);
            }

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
            for (int i = 0; i < ai.size(); i++) {
                for (int j = 0; j < aiNumber.size(); j++){
                    if (ai.get(i) == aiNumber.get(j)){
                        allLabels.get(i).setText(aiDescripion.get(j) + ": " + data.get(i));
                    }
                }
            }
        });

        root.add(capture);
        // Setting number of labels
        for (int i = 0; i < 10; i++){
            allLabels.add(new JLabel());
            root.add(allLabels.get(i));
        }

        // frame settings
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(root);
        frame.setSize(500, 900);
        frame.setVisible(true);
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Gui();
    }
    
}
