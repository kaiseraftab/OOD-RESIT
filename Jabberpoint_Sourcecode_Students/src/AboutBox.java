import java.awt.Frame;
import javax.swing.JOptionPane;

public class AboutBox {

	private static final String ABOUT_TITLE = "About JabberPoint";
	private static final String ABOUT_MESSAGE = String.join(
			System.lineSeparator(),
			"JabberPoint is a primitive slide-show program in Java(tm). It",
			"is freely copyable as long as you keep this notice and",
			"the splash screen intact.",
			"Copyright (c) 1995-1997 by Ian F. Darwin, ian@darwinsys.com.",
			"Adapted by Gert Florijn (version 1.1) and Sylvia Stuurman (version 1.2 and higher) for the Open",
			"University of the Netherlands, 2002 -- now.",
			"Author's version available from http://www.darwinsys.com/"
	);

	public static void show(Frame parent) {
		try {
			JOptionPane.showMessageDialog(parent,
					ABOUT_MESSAGE,
					ABOUT_TITLE,
					JOptionPane.INFORMATION_MESSAGE
			);
		} catch (Exception e) {
			System.err.println("Failed to display the About dialog: " + e.getMessage());
		}
	}
}
