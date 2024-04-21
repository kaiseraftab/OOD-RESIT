import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.TextLayout;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedString;
import java.text.AttributedCharacterIterator;
import java.awt.font.TextAttribute; // Ensure this import is correct
import java.util.ArrayList;
import java.util.List;

public class TextItem extends SlideItem {
	private final String text;
	private static final String EMPTY_TEXT = "No Text Given";

	public TextItem(int level, String text) {
		super(level);
		this.text = (text != null) ? text : EMPTY_TEXT;
	}

	public String getText() {
		return text;
	}

	private AttributedString getAttributedString(Style style, float scale) {
		AttributedString attributedString = new AttributedString(text);
		// Using TextAttribute.FONT here
		attributedString.addAttribute(TextAttribute.FONT, style.getFont(scale), 0, text.length());
		return attributedString;
	}

	public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style) {
		Graphics2D g2d = (Graphics2D) g;
		FontRenderContext frc = g2d.getFontRenderContext();
		AttributedString attrStr = getAttributedString(style, scale);
		TextLayout layout = new TextLayout(attrStr.getIterator(), frc);
		Rectangle2D bounds = layout.getBounds();
		return new Rectangle(
				(int) (style.getIndent() * scale),
				0,
				(int) (bounds.getWidth()),
				(int) (layout.getAscent() + layout.getDescent() + layout.getLeading())
		);
	}

	public void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver observer) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(style.getColor());
		AttributedString attrStr = getAttributedString(style, scale);
		FontRenderContext frc = g2d.getFontRenderContext();
		TextLayout layout = new TextLayout(attrStr.getIterator(), frc);
		layout.draw(g2d, x + style.getIndent(), y + (int) (style.getLeading() * scale + layout.getAscent()));
	}
}
