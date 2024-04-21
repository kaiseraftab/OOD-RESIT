import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class XMLFIleSelector extends JFrame {

    public String selectXmlFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (!checkIfPresent(selectedFile.getName())) {
                copyInCurrentDirectory(selectedFile.getAbsolutePath(), selectedFile.getName());
            }
            return selectedFile.getAbsolutePath();  // It might be more useful to return the full path
        }
        return null; // User cancelled the file chooser dialog
    }

    public boolean checkIfPresent(String filename) {
        File file = new File(filename);
        if (file.exists()) {
            JOptionPane.showMessageDialog(this, "The file exists in the directory.");
            return true;
        } else {
            return false;
        }
    }

    public boolean copyInCurrentDirectory(String sourceFilePath, String fileName) {
        File sourceFile = new File(sourceFilePath);
        File destinationFile = new File(System.getProperty("user.dir"), fileName);
        try (FileInputStream inputStream = new FileInputStream(sourceFile);
             FileOutputStream outputStream = new FileOutputStream(destinationFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, bytesRead);
            }
            System.out.println("File copied successfully.");
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to copy the file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
