import javax.swing.*;
import java.awt.*;
import java.io.File;

public class HelpingClass {
    public static String getStringInput(String title,int width,int height){
    // Create a JFrame to serve as the parent window for the popup dialog
        JFrame parent = new JFrame();

        // Create a Dialog to display
        JDialog inputDialog = new JDialog(parent, title, true);
        inputDialog.setSize(width, height); // Set the size of the dialog

        // Create a JTextArea for the user to input text
        JTextArea inputArea = new JTextArea();
        inputDialog.getContentPane().add(inputArea);

        //_____________________________________________________________________________
        //                      Buttons Setting
        // Create a "Save" button
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(event -> {
            // Get the text entered by the user
            // Close the input dialog
            inputDialog.dispose();
        });

        // Create a "Cancel" button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(event -> {
            inputDialog.dispose();
        });

        // Create a panel and add to the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);


        // merged into the Main diagonal to show
        inputDialog.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Show the inputDialog on screen
        inputDialog.setVisible(true);
        String userInput = inputArea.getText();
        if(userInput==""){
            return null;
        }
        return  userInput;
    }
    public static String getSelecteFolderPathFromDirectory(){
        // Create a file chooser dialog
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Select a folder");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        // Show the file chooser dialog and get the user's selection
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            String folderPath = chooser.getSelectedFile().toString();
            return folderPath;
            // Pass the folder path to the main program for further processing
        }
        return null;
    }

    public static String getFileNameInput(String title,String Message){
        String fileName = JOptionPane.showInputDialog(null, Message, title, JOptionPane.PLAIN_MESSAGE);

        if (fileName != null && !fileName.isEmpty()) {
            return fileName;
        } else {
            return null;
        }
    }
}
