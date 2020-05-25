package UI.TestFiles;

import javax.swing.*;

//The primary class is a JFrame which can be shown when the program starts
public class JListDemo extends JFrame {
 
 /*  main is just housed inside of the primary class. It could be part of a driver
     class but this implementation is simpler. Here we just create a new 
     instance of the class to show the frame which contains the UI and all the 
     application logic. */
 public static void main(String[] args) {

     JListDemo demo = new JListDemo();
 }
 
 public JListDemo()
 {
     // create the list items using a DefaultListModel. This allows us to add/remove
     // items later
     DefaultListModel model = new DefaultListModel();
     model.addElement("piña");
     model.addElement("maracuya");
     model.addElement("guanábana");

     JList list = new JList(model);
     add(list);
     
     // finish typical frame setup
     setDefaultCloseOperation(EXIT_ON_CLOSE);
     pack();
     setVisible(true);

 }
}
