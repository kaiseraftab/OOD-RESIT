import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

public class MenuController extends MenuBar {

	private final Frame parent;
	private final Presentation presentation;

	private static final long serialVersionUID = 227L;

	public MenuController(Frame frame, Presentation pres) {
		this.parent = frame;
		this.presentation = pres;
		createMenu();
	}

	private void createMenu() {
		add(createFileMenu());
		add(createViewMenu());
		add(createHelpMenu());
	}

	private Menu createFileMenu() {
		Menu fileMenu = new Menu("File");
		fileMenu.add(createMenuItem("Open", e -> openPresentation()));
		fileMenu.add(createMenuItem("New", e -> newPresentation()));
		fileMenu.add(createMenuItem("Save", e -> savePresentation()));
		fileMenu.addSeparator();
		fileMenu.add(createMenuItem("Exit", e -> presentation.exit(0)));
		return fileMenu;
	}

	private Menu createViewMenu() {
		Menu viewMenu = new Menu("View");
		viewMenu.add(createMenuItem("Next", e -> presentation.nextSlide()));
		viewMenu.add(createMenuItem("Prev", e -> presentation.prevSlide()));
		viewMenu.add(createMenuItem("Go to", e -> goToSlide()));
		return viewMenu;
	}

	private Menu createHelpMenu() {
		Menu helpMenu = new Menu("Help");
		helpMenu.add(createMenuItem("About", e -> AboutBox.show(parent)));
		setHelpMenu(helpMenu); // Needed for portability (Motif, etc.)
		return helpMenu;
	}

	private MenuItem createMenuItem(String label, ActionListener listener) {
		MenuItem menuItem = new MenuItem(label, new MenuShortcut(label.charAt(0)));
		menuItem.addActionListener(listener);
		return menuItem;
	}

	private void openPresentation() {
		presentation.clear();
		Accessor xmlAccessor = new XMLAccessor();
		try {
			XMLFIleSelector XFS = new XMLFIleSelector();
			xmlAccessor.loadFile(presentation, XFS.selectXmlFile());
			presentation.setSlideNumber(0);
		} catch (IOException exc) {
			JOptionPane.showMessageDialog(parent, "IO Exception: " + exc, "Load Error", JOptionPane.ERROR_MESSAGE);
		}
		parent.repaint();
	}

	private void newPresentation() {
		presentation.clear();
		XMLAccessor xmlAccessor = new XMLAccessor();
		try {
			String newFileName = xmlAccessor.LoadNewFile();
			if (newFileName != null) {
				xmlAccessor.loadFile(presentation, newFileName);
				presentation.setSlideNumber(0);
			}
		} catch (IOException exc) {
			JOptionPane.showMessageDialog(parent, "IO Exception: " + exc, "Load Error", JOptionPane.ERROR_MESSAGE);
		}
		parent.repaint();
	}

	private void savePresentation() {
		Accessor xmlAccessor = new XMLAccessor();
		try {
			String filePath = HelpingClass.getSelectedFolderPathFromDirectory();
			xmlAccessor.saveFile(presentation, "savedPresentation.xml");
		} catch (IOException exc) {
			JOptionPane.showMessageDialog(parent, "IO Exception: " + exc, "Save Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void goToSlide() {
		String pageNumberStr = JOptionPane.showInputDialog(parent, "Page number?");
		try {
			int pageNumber = Integer.parseInt(pageNumberStr);
			presentation.setSlideNumber(pageNumber - 1);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(parent, "Invalid number format", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
