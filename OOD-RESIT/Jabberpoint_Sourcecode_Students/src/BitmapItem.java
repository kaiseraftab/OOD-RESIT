import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class BitmapItem extends SlideItem {
	private BufferedImage image;
	private final String imageName;

	public BitmapItem(int level, String imageName) {
		super(level);
		this.imageName = imageName;
		loadImage();
	}

	private void loadImage() {
		try {
			image = ImageIO.read(new File(imageName));
		} catch (IOException e) {
			System.err.println("Error loading image: " + imageName);
			image = null;
		}
	}

	public String getName() {
		return imageName;
	}

	public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style style) {
		if (image == null) return new Rectangle(0, 0, 0, 0);
		return new Rectangle(
				(int) (style.getIndent() * scale),
				0,
				(int) (image.getWidth(observer) * scale),
				(int) (image.getHeight(observer) * scale)
		);
	}

	public void draw(int x, int y, float scale, Graphics g, Style style, ImageObserver observer) {
		if (image != null) {
			g.drawImage(image,
					x + style.getIndent(),
					y,
					(int) (image.getWidth(observer) * scale),
					(int) (image.getHeight(observer) * scale),
					observer);
		}
	}
}
