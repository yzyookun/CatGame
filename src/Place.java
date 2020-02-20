import java.awt.image.BufferedImage;


public class Place {
	
	private BufferedImage image = null;
	private int x, y;
	private int posX, posY;
	private int type; //��ǰ�ڵ������ 1���հ� 2���Ҿ� 3���о�
	private int preX, preY; //���Ե��Ǹ��������

	private boolean visited;
	
	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public Place(int x, int y, BufferedImage image, int type){
		this.x = x;
		this.y = y;
		this.image = image;
		this.type = type;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
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
	
	public int getPreX() {
		return preX;
	}

	public void setPreX(int preX) {
		this.preX = preX;
	}

	public int getPreY() {
		return preY;
	}

	public void setPreY(int preY) {
		this.preY = preY;
	}
}
