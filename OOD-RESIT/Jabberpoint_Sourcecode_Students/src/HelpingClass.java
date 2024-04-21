import javax.swing.*;
import java.awt.*;
import java.io.File;

public class HelpingClass {
    public static String getStringInput(String title, int width, int height) {
        final String[] userInput = new String[1];
        SwingUtilities.invokeLater(() -> {
            // Using a JDialog tied to a JFrame
            JFrame parent = new JFrame();
            parent.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JDialog inputDialog = new JDialog(parent, title, true);
            inputDialog.setSize(width, height);
            inputDialog.setLayout(new BorderLayout());

            JTextArea inputArea = new JTextArea();
            inputDialog.add(inputArea, BorderLayout.CENTER);

            JButton saveButton = new JButton("Save");
            JButton cancelButton = new JButton("Cancel");

            // Action listeners for buttons
            saveButton.addActionListener(e -> {
                userInput[0] = inputArea.getText();
                inputDialog.dispose();
            });

            cancelButton.addActionListener(e -> inputDialog.dispose());

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(saveButton);
            buttonPanel.add(cancelButton);
            inputDialog.add(buttonPanel, BorderLayout.SOUTH);

            inputDialog.setLocationRelativeTo(null);  // Center the dialog
            inputDialog.setVisible(true);
        });

        // Return user input after dialog is disposed
        return userInput[0] != null && !userInput[0].isEmpty() ? userInput[0] : null;
    }

    public static String getSelectedFolderPathFromDirectory() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        chooser.setDialogTitle("Select a folder");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().toString();
        }
        return null;
    }

    public static String getFileNameInput(String title, String message) {
        return JOptionPane.showInputDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
    }
}
