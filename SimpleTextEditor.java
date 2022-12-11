import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class SimpleTextEditor implements ActionListener {

    JFrame frame;
    JTextArea jTextArea;
    JMenuBar jMenuBar;
    JMenu File,Edit,Close;
    JMenuItem OpenFile,NewFile,SaveFile,PrintFile;
    JMenuItem Cut,Copy,Past;
    JMenuItem CloseEditor;

    SimpleTextEditor(){
        frame = new JFrame("Simple Text Editor");
        frame.setBounds(0,0,800,900);
        jTextArea = new JTextArea("Write Something Here...");
        Font font = new Font("SanSerif", Font.BOLD,18);
        jTextArea.setFont(font);
//      Create MenuBar
        jMenuBar = new JMenuBar();

//      Create Menu For MenuBar
        File = new JMenu("File");
        Edit = new JMenu("Edit");
        Close = new JMenu("Close");
//      Adding File, Edit and Close Menu into MenuBar
        jMenuBar.add(File);
        jMenuBar.add(Edit);
        jMenuBar.add(Close);

//      --------------Create MenuItem For File Menu-------------
        OpenFile = new JMenuItem("Open");
        OpenFile.addActionListener(this);

        SaveFile = new JMenuItem("Save");
        SaveFile.addActionListener(this);

        NewFile = new JMenuItem("New");
        NewFile.addActionListener(this);

        PrintFile = new JMenuItem("Print");
        PrintFile.addActionListener(this);
//      Add Open, Save, New and Print MenuItem into the File Menu
        File.add(OpenFile);
        File.add(SaveFile);
        File.add(NewFile);
        File.add(PrintFile);

//      -------------Create MenuItem For Edit Menu---------------
        Cut = new JMenuItem("Cut");
        Cut.addActionListener(this);

        Copy = new JMenuItem("Copy");
        Copy.addActionListener(this);

        Past = new JMenuItem("Past");
        Past.addActionListener(this);

//      Add Cut, Copy and Past MenuItem into the Edit Menu
        Edit.add(Cut);
        Edit.add(Copy);
        Edit.add(Past);

//      -----------Create MenuItem For Close Menu-----------
        CloseEditor = new JMenuItem("Close");
        CloseEditor.addActionListener(this);
//      Add Close MenuItem into the Close Menu
        Close.add(CloseEditor);

        frame.setJMenuBar(jMenuBar);
        frame.add(jTextArea); // Adding the Component to the frame
//      To Stop the Execution of the Program
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        SimpleTextEditor editor = new SimpleTextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if(s.equals("Copy")){
            jTextArea.copy();
        }
        else if (s.equals("Cut")) {
            jTextArea.cut();
        }
        else if(s.equals("Past")){
            jTextArea.paste();
        }
        else if (s.equals("Print")) {
            try {
                jTextArea.print();
            }
            catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }
        else if (s.equals("New")) {
            jTextArea.setText("");
        }
        else if (s.equals("Close")) {
            frame.setVisible(false);
            System.exit(0);
        }
        else if (s.equals("Open")) {
            JFileChooser jFileChooser = new JFileChooser("D");
//          for open and close Options
            int option = jFileChooser.showOpenDialog(null);
//          Approve to Open a file
            if(option == JFileChooser.APPROVE_OPTION){
//              File Path Stored
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                String str1 = "";
                StringBuilder str2;
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    str2 = new StringBuilder(bufferedReader.readLine());
                    while ((str1 = bufferedReader.readLine()) != null){
                        str2.append(str1).append("\n");
                    }
                    jTextArea.setText(String.valueOf(str2));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        else if (s.equals("Save")) {
            JFileChooser jFileChooser = new JFileChooser("D");
            int options = jFileChooser.showOpenDialog(null);
            if(options == JFileChooser.APPROVE_OPTION){
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                BufferedWriter writer = null;
                try {
                    writer = new BufferedWriter(new FileWriter(file,false));
//                  get Text from jTextArea
//                  and Write into Internal file
                    writer.write(jTextArea.getText());
//                  clear the writer
                    writer.flush();
//                  close our writer;
                    writer.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
