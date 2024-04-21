import java.awt.Color;
import java.awt.Font;

public class Style {
	private static final Style[] styles = new Style[5];
	private static final String FONT_NAME = "Helvetica";
	private static final int DEFAULT_STYLE_LEVEL = 4;

	static {
		styles[0] = new Style(0, Color.red, 48, 20);
		styles[1] = new Style(20, Color.blue, 40, 10);
		styles[2] = new Style(50, Color.black, 36, 10);
		styles[3] = new Style(70, Color.black, 30, 10);
		styles[4] = new Style(90, Color.black, 24, 10);
	}

	private int indent;
	private Color color;
	private Font font;
	private int fontSize;
	private int leading;

	private Style(int indent, Color color, int fontSize, int leading) {
		this.indent = indent;
		this.color = color;
		this.fontSize = fontSize;
		this.leading = leading;
		this.font = new Font(FONT_NAME, Font.BOLD, fontSize);
	}

	public static Style getStyle(int level) {
		if (level < 0 || level >= styles.length) {
			return styles[DEFAULT_STYLE_LEVEL];
		}
		return styles[level];
	}

	public int getIndent() {
		return indent;
	}

	public Color getColor() {
		return color;
	}

	public int getLeading() {
		return leading;
	}

	public Font getFont(float scale) {
		return font.deriveFont(font.getStyle(), fontSize * scale);
	}

	@Override
	public String toString() {
		return "[" + indent + "," + color + "; " + fontSize + " on " + leading + "]";
	}
}