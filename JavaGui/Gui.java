import javax.swing.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Gui {
    public Gui() throws FileNotFoundException{
        // frame setup
        JFrame frame = new JFrame("Title Here");
        JPanel root = new JPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.PAGE_AXIS));

        // get information from file and store in arraylist
        ArrayList<String> information = new ArrayList<>();
        File file = new File("lines.txt");
        Scanner reader = new Scanner(file);
        while (reader.hasNext()){
            information.add(reader.nextLine());
        }

        // parsing probably here

        // display
        ArrayList<JLabel> labelArray = new ArrayList<>();
        for (String info : information) {
            JLabel infoDisplay = new JLabel(info);
            labelArray.add(infoDisplay);
        }

        // adding to root
        for (JLabel label : labelArray) {
            root.add(label);
        }

        // standard closing frame
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(root);
        frame.setSize(500, 900);
        frame.setVisible(true);
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Gui();
    }
    
}
