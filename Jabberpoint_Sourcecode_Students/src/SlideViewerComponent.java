import java.awt.*;
import java.io.IOException;
import javax.swing.*;

public class SlideViewerComponent extends JComponent {

	private Slide slide;
	private Font labelFont;
	private Presentation presentation;
	private JFrame frame;

	private static final long serialVersionUID = 227L;

	private static final Color BACKGROUND_COLOR = Color.white;
	private static final Color TEXT_COLOR = Color.black;
	private static final String FONT_NAME = "Dialog";
	private static final int FONT_STYLE = Font.BOLD;
	private static final int FONT_HEIGHT = 10;
	private static final int TITLE_X_POS = 1100;
	private static final int TITLE_Y_POS = 20;

	public SlideViewerComponent(Presentation pres, JFrame frame) {
		this.presentation = pres;
		this.frame = frame;
		initializeComponent();
	}

	private void initializeComponent() {
		setBackground(BACKGROUND_COLOR);
		labelFont = new Font(FONT_NAME, FONT_STYLE, FONT_HEIGHT);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(Slide.WIDTH, Slide.HEIGHT);
	}

	public void update(Presentation presentation, Slide slide) {
		this.presentation = presentation;
		this.slide = slide;
		repaint();
		updateFrameTitle();
	}

	private void updateFrameTitle() {
		if (frame != null && presentation != null) {
			frame.setTitle(presentation.getTitle());
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, getSize().width, getSize().height);

		if (presentation == null || presentation.getSlideNumber() < 0 || slide == null) {
			return;
		}

		try
		{
			drawSlideDetails(g);
		} catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	private void drawSlideDetails(Graphics g) throws IOException
	{
		g.setFont(labelFont);
		g.setColor(TEXT_COLOR);
		g.drawString("Slide " + (1 + presentation.getSlideNumber()) + " of " + presentation.getSize(),
				TITLE_X_POS, TITLE_Y_POS);
		Rectangle area = new Rectangle(0, TITLE_Y_POS, getWidth(), (getHeight() - TITLE_Y_POS));
		slide.draw(g, area, this);
	}
}