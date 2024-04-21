import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class BitmapItem extends SlideItem {
	private BufferedImage bufferedImage;
	private final String imageName;

	protected static final String FILE = "File ";
	protected static final String NOTFOUND = " not found";

	public BitmapItem(int level, String name) {
		super(level);
		imageName = name;
	}

	private void loadImage() throws IOException {
		if (bufferedImage == null) {
			bufferedImage = ImageIO.read(new File(imageName));
		}
	}

	public String getName() {
		return imageName;
	}

	public Rectangle getBoundingBox(Graphics g, ImageObserver observer, float scale, Style myStyle) throws IOException {
		loadImage();
		return new Rectangle(calculateScaledWidth(myStyle.indent, scale),
				0,
				calculateScaledDimension(bufferedImage.getWidth(observer), scale),
				calculateScaledDimension(bufferedImage.getHeight(observer), scale) + calculateScaledHeight(myStyle.leading, scale));
	}

	public void draw(int x, int y, float scale, Graphics g, Style myStyle, ImageObserver observer) throws IOException {
		loadImage();
		g.drawImage(bufferedImage,
				x + calculateScaledWidth(myStyle.indent, scale),
				y + calculateScaledHeight(myStyle.leading, scale),
				calculateScaledDimension(bufferedImage.getWidth(observer), scale),
				calculateScaledDimension(bufferedImage.getHeight(observer), scale),
				observer);
	}

	private int calculateScaledWidth(float indent, float scale) {
		return (int) (indent * scale);
	}

	private int calculateScaledHeight(float leading, float scale) {
		return (int) (leading * scale);
	}

	private int calculateScaledDimension(int dimension, float scale) {
		return (int) (dimension * scale);
	}

	public String toString() {
		return "BitmapItem[" + getLevel() + "," + imageName + "]";
	}
}
