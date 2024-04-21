import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.io.IOException;

public abstract class SlideItem {
	private int level; // The level of the SlideItem

	public SlideItem(int lev) {
		if (lev < 0) {
			throw new IllegalArgumentException("Level must be non-negative");
		}
		this.level = lev;
	}

	public int getLevel() {
		return level;
	}

	public abstract Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style) throws IOException;

	public abstract void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver observer) throws IOException;
}