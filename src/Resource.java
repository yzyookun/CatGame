import java.awt.image.BufferedImage;

/**
 * �����е���Դ
 * @author Administrator
 *
 */
public class Resource {
	private int x;
	private int y;
	private BufferedImage image = null;
	
	public Resource(int x, int y, BufferedImage image){
		this.x = x;
		this.y = y;
		this.image = image;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
}
