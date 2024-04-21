import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.*;

public class XMLFIleSelector extends JFrame {

    public String selectXmlFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            if(! checkIfPresent(selectedFile.getName())){
                CopyInCurrentDirectory(filePath,selectedFile.getName());
            }
            return selectedFile.getName();
        } else {
            return null; // user cancelled the file chooser dialog
        }
    }
    public boolean checkIfPresent(String filename){
        // Get the current working directory
        String cwd = System.getProperty("user.dir");

        // Create the file path by joining the file name to the current working directory
        String filePath = cwd + File.separator + filename;

        // Check if the file exists
        File file = new File(filePath);
        if (file.exists()) {
            JOptionPane.showMessageDialog(null, "The file exists in the directory.");
            return true;
        } else {
            return false;
        }
    }
        public boolean CopyInCurrentDirectory(String sourceFilePath,String fileName)
    {
        // Source file location
        File sourceFile = new File(sourceFilePath   );

        // Destination file location (relative to Java main file)
        File destinationFile = new File(fileName);
        try{
            // Create input and output streams
            FileInputStream inputStream = new FileInputStream(sourceFile);
            FileOutputStream outputStream = new FileOutputStream(destinationFile);

            // Copy file contents from input stream to output stream
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, bytesRead);
            }

            // Close input and output streams
            inputStream.close();
            outputStream.close();

            System.out.println("File copied successfully.");
            return true;
        }
        catch (Exception e){
            System.out.println("Try Again");
            return false;
        }
    }


}
