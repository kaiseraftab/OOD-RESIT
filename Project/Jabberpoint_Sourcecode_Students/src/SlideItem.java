import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.io.IOException;

/**
 * The abstract class for items on a slide.
 * All SlideItems have drawing capabilities and are characterized by a 'level'.
 */
public abstract class SlideItem {
	private int level; // The level of the SlideItem

	/**
	 * Constructs a SlideItem with the specified level.
	 * @param lev the level of the slide item
	 * @throws IllegalArgumentException if the level is negative
	 */
	public SlideItem(int lev) {
		if (lev < 0) {
			throw new IllegalArgumentException("Level must be non-negative");
		}
		this.level = lev;
	}

	/**
	 * Returns the level of this SlideItem.
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Returns the bounding box of this SlideItem.
	 * @param g the graphics context
	 * @param observer the image observer
	 * @param scale the scale factor
	 * @param style the style to apply
	 * @return the bounding box as a Rectangle
	 */
	public abstract Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style) throws IOException;

	/**
	 * Draws the item on the given graphics context.
	 * @param x the x coordinate for the drawing start point
	 * @param y the y coordinate for the drawing start point
	 * @param scale the scale factor for the drawing
	 * @param g the graphics context
	 * @param style the style to apply
	 * @param observer the image observer
	 */
	public abstract void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver observer) throws IOException;
}
