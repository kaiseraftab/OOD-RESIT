import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Slide {
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 800;
	private String title; // The title of the slide
	private List<SlideItem> items; // The SlideItems are kept in a list

	public Slide() {
		items = new ArrayList<>();
	}

	// Adds a SlideItem
	public void append(SlideItem anItem) {
		items.add(anItem);
	}

	// Returns the title of the slide
	public String getTitle() {
		return title;
	}

	// Changes the title of the slide
	public void setTitle(String newTitle) {
		title = newTitle;
	}

	// Creates a TextItem from a String and adds it
	public void append(int level, String message) {
		append(new TextItem(level, message));
	}

	// Returns a specific SlideItem
	public SlideItem getSlideItem(int number) {
		if (number >= 0 && number < items.size()) {
			return items.get(number);
		}
		return null;
	}

	// Returns all SlideItems
	public List<SlideItem> getSlideItems() {
		return items;
	}

	// Returns the number of SlideItems
	public int getSize() {
		return items.size();
	}

	// Draws the slide
	public void draw(Graphics g, Rectangle area, ImageObserver view) throws IOException
    {
		float scale = getScale(area);
		int y = area.y;
		SlideItem titleItem = new TextItem(0, getTitle());
		Style titleStyle = Style.getStyle(titleItem.getLevel());
		titleItem.draw(area.x, y, scale, g, titleStyle, view);
		y += titleItem.getBoundingBox(g, view, scale, titleStyle).height;

		for (SlideItem slideItem : items) {
			Style style = Style.getStyle(slideItem.getLevel());
			slideItem.draw(area.x, y, scale, g, style, view);
			y += slideItem.getBoundingBox(g, view, scale, style).height;
		}
	}

	// Calculates the scaling factor for the slide based on the area
	private float getScale(Rectangle area) {
		return Math.min((float)area.width / WIDTH, (float)area.height / HEIGHT);
	}
}
