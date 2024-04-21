import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;

public class SlideViewerFrame extends JFrame {
	private static final long serialVersionUID = 3227L;

	private static final String FRAME_TITLE = "Jabberpoint 1.6 - OU";
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 800;

	private Presentation presentation; // Class field to hold the presentation

	public SlideViewerFrame(String jabversion, Presentation presentation) {
		super(FRAME_TITLE);
		this.presentation = presentation; // Assign to class field
		initialize();
	}

	private void initialize() {
		SlideViewerComponent slideViewerComponent = new SlideViewerComponent(presentation, this);
		presentation.setShowView(slideViewerComponent);
		setupComponents(slideViewerComponent);
		setupListeners();
		finalizeFrame();
	}

	private void setupComponents(SlideViewerComponent slideViewerComponent) {
		getContentPane().add(slideViewerComponent);
		setMenuBar(new MenuController(this, presentation)); // Now 'presentation' is accessible
	}

	private void setupListeners() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exitProcedure();
			}
		});
		addKeyListener(new KeyController(presentation)); // Accessible here as well
	}

	private void finalizeFrame() {
		setSize(new Dimension(WIDTH, HEIGHT));
		setVisible(true);
	}

	private void exitProcedure() {
		// Handle any cleanup or save operations here
		dispose(); // Ensure all resources are freed
		System.exit(0); // Consider using a more controlled shutdown approach
	}
}
