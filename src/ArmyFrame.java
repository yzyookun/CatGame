import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.plaf.SliderUI;

public class ArmyFrame extends JFrame implements Runnable, MouseListener{
	
	private static final long serialVersionUID = 1L;
	
	//记录走的步数
	private int stepCont = 0;

	private List<Scene> sceneList = new ArrayList<Scene>();
	private int sort;
	private Thread freshThread = null;
	private Place placeArr[][] = new Place[9][13];
	private int currentX = 4, currentY = 6;
	
	private int singelRow[][] = new int[6][2];
	private int doubleRow[][] = new int[6][2];
	
	public ArmyFrame(){
		this.setTitle("围住小猫");
		this.setSize(640, 480);
		int width=Toolkit.getDefaultToolkit().getScreenSize().width;
		int height=Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation((width-640)/2,(height-480)/2);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
		this.setResizable(false);
		
		Scene startScene = new Scene(1, 1);
		sceneList.add(startScene);
		
		Scene info1 = new Scene(2, 2);
		sceneList.add(info1);
		
		Scene info2 = new Scene(3, 2);
		sceneList.add(info2);
		
		Scene game = new Scene(4, 2);
		sceneList.add(game);
		
		Scene end = new Scene(5, 3);
		sceneList.add(end);
		
		this.sort = 0;
		
		this.addMouseListener(this);
		
		this.repaint();
		
		//初始化6个方向数组，因为单数行和偶数行的方向不同，所以要做两个数组
		singelRow[0][0] = -1;
		singelRow[0][1] = -1;
		singelRow[1][0] = -1;
		singelRow[1][1] = 0;
		singelRow[2][0] = 0;
		singelRow[2][1] = 1;
		singelRow[3][0] = 1;
		singelRow[3][1] = 0;
		singelRow[4][0] = 1;
		singelRow[4][1] = -1;
		singelRow[5][0] = 0;
		singelRow[5][1] = -1;
		
		doubleRow[0][0] = -1;
		doubleRow[0][1] = 0;
		doubleRow[1][0] = -1;
		doubleRow[1][1] = 1;
		doubleRow[2][0] = 0;
		doubleRow[2][1] = 1;
		doubleRow[3][0] = 1;
		doubleRow[3][1] = 1;
		doubleRow[4][0] = 1;
		doubleRow[4][1] = 0;
		doubleRow[5][0] = 0;
		doubleRow[5][1] = -1;
		
		
		freshThread = new Thread(this);
		freshThread.start();
	}
	
	public void paint(Graphics g){
		BufferedImage image=new BufferedImage(900,600,BufferedImage.TYPE_3BYTE_BGR);
		Graphics g2=image.getGraphics();
		if(sceneList.size() > sort){
			
			Scene currentScene = sceneList.get(sort);
			
			if(currentScene != null){
				//先画背景
				g2.drawImage(currentScene.getBgImage(),0,0,this);
				//再画里边固定位置的元素
				for(int i = 0; i < currentScene.getResorceList().size(); i++){
					Resource tempResource = currentScene.getResorceList().get(i);
					g2.drawImage(tempResource.getImage(), tempResource.getX(), tempResource.getY(), this);
				}
				//画定点
				placeArr = currentScene.getPlaceArr();
				for(int i = 0; i < currentScene.getPlaceList().size(); i++){
					Place place = currentScene.getPlaceList().get(i);
					g2.drawImage(place.getImage(), place.getX(), place.getY(), this);
				}
				
				if(this.sort == 3){//游戏中，记录步数
					g2.setFont(new Font("宋体",Font.BOLD,20));
					g2.drawString("已经设置的路障数量    " + stepCont, 70, 460);
					g2.drawImage(StaticValue.nandu, 320, 430, this);//难度图片位置
					g2.drawImage(StaticValue.restart, 440, 430, this);//重新开始图片位置
				}
			}
		}
		
		g.drawImage(image,0,0,this);
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(50);
				this.repaint();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 鼠标点击事件
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		
		if(this.sort == 0){//开始界面
			if(x >= 160 && x <= 280 && y >= 350 && y <= 385){//表示点击的是开始游戏
				this.sort ++;
			}else if(x >= 360 && x <= 480 && y >= 350 && y <= 385){//表示点击的结束游戏，关闭游戏
				System.exit(0);
			}
		}else  if(this.sort == 1){//提示一
			this.sort ++;
		}else  if(this.sort == 2){//提示二
			this.sort++;
		}else  if(this.sort == 3){//已经在游戏中了
			boolean flag = false;
			y = y - 40;
			int indexX = 0, indexY = 0;
			indexX = y / 40;
			if(indexX % 2 == 0){
				x = x - 40;
				if(x > 0 && x < 520 && y > 0 && y < 360){
					flag = true;
				}
			}else{
				x = x - 60;
				if(x > 0 && x < 520 && y > 0 && y < 360){
					flag = true;
				}
			}
			indexY = x / 40;
			
			if(flag && indexX < 9 && indexY < 13){//保证点击的位置是其中的某个节点
				if(placeArr[indexX][indexY].getType() == 1){//表示是空白的
					placeArr[indexX][indexY].setType(2);
					placeArr[indexX][indexY].setImage(StaticValue.luzhang);
					
					this.stepCont++;
					
					//玩家点击完成后，开始计算猫下一步的路子，使用广度优先算法
					Place endPoint = nextStep(this.currentX, this.currentY);
					
					if(endPoint == null){//也就是说猫没法走了，那么玩家就赢了
						endPoint = getCommonNextStep(this.currentX, this.currentY);
						if(endPoint == null){
							int res = JOptionPane.showConfirmDialog(this, "恭喜你，你一共用了"+this.stepCont+"步便抓住了小猫,是否再来一局？", "小猫被抓住了", JOptionPane.YES_NO_OPTION);
							//this.sort = 0;
							if(res == 0){//再来一局
								this.reSetGame();
							}else{//返回主界面
								this.reSetGame();
								this.sort = 0;
							}
						}else{
							placeArr[this.currentX][this.currentY].setImage(StaticValue.place);
							placeArr[this.currentX][this.currentY].setType(1);
							placeArr[endPoint.getPosX()][endPoint.getPosY()].setImage(StaticValue.cat);
							placeArr[endPoint.getPosX()][endPoint.getPosY()].setType(3);
							
							this.currentX = endPoint.getPosX();
							this.currentY = endPoint.getPosY();
						}
					}else{
						boolean findNextPoint = true;
						int nextX = this.currentX;
						int nextY = this.currentY;
						while(findNextPoint && endPoint != null){
							int preX = endPoint.getPreX();
							int preY = endPoint.getPreY();
							
							if(preX == this.currentX && preY == this.currentY){
								findNextPoint = false;
								nextX = endPoint.getPosX();
								nextY = endPoint.getPosY();
							}else{
								endPoint = placeArr[preX][preY];
							}
						}
						
						placeArr[this.currentX][this.currentY].setImage(StaticValue.place);
						placeArr[this.currentX][this.currentY].setType(1);
						placeArr[nextX][nextY].setImage(StaticValue.cat);
						placeArr[nextX][nextY].setType(3);
						
						this.currentX = nextX;
						this.currentY = nextY;
						if(this.currentX == 0 || this.currentY == 0 || this.currentX == 8 
								|| this.currentY == 12){//表示猫已经到了出口了，那么玩家就输了
							int res = JOptionPane.showConfirmDialog(this, "很遗憾，小猫跑掉了,是否再来一局？", "小猫跑了", JOptionPane.YES_NO_OPTION);
							//this.sort = 0;
							if(res == 0){//再来一局
								this.reSetGame();
							}else{//返回主界面
								this.reSetGame();
								this.sort = 0;
							}
						}
					}
				}
			}else if(e.getX() >= 320 && e.getX() <= 420 && e.getY() >= 430 && e.getY() <= 460){//点击了设置难度按钮
				Object[] options = {"简单","容易","一般", "复杂", "困难"};
				int res = JOptionPane.showOptionDialog(this, 
						"点击下列按钮设置难度", "设置难度",
						JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE,
						null, options, options[0]);
				if(res == 0){
					Config.nandu = 5;
				}else if(res == 1){
					Config.nandu = 7;
				}else if(res == 2){
					Config.nandu = 10;
				}else if(res == 3){
					Config.nandu = 15;
				}else if(res == 4){
					Config.nandu = 20;
				}
				if(res >= 0){
					this.reSetGame();
				}
			}else if(e.getX() >= 440 && e.getX() <= 540 && e.getY() >= 420 && e.getY() <= 450){//点击了重新开始按钮
				this.reSetGame();
			}
		}else  if(this.sort == 4){//提示一
			if(x >= 440 && x <= 560 && y >= 320 && y <= 355){//表示点击的结束游戏，关闭游戏
				System.exit(0);
			}
		}
		
	}
	/**
	 * 重置这个游戏，主要是针对第三个场景
	 */
	public void reSetGame(){
		Scene game = new Scene(4, 2);
		sceneList.set(3, game);
		this.currentX = 4;
		this.currentY = 6;
		this.stepCont = 0;
	}
	
	/**
	 * 该方法是在深度搜索后，没有找到合适路径，那么就找一个附近的可以走的节点即可
	 * @param x
	 * @param y
	 * @return
	 */
	public Place getCommonNextStep(int x, int y){
		for(int i = 0; i < 6; i++){
			int xx = 0, yy = 0;
			if(x % 2 == 0){//奇数行
				xx = x + singelRow[i][0];
				yy = y + singelRow[i][1];
			}else{//偶数行
				xx = x + doubleRow[i][0];
				yy = y + doubleRow[i][1];
			}
			if(isInMap(xx, yy)){//如果该点在点阵之内的话
				if(placeArr[xx][yy].getType() == 1){
					return placeArr[xx][yy];
				}
			}
		}
		return null;
	}
	
	/**
	 * 广度优先算法，去找最短路径
	 * @param x
	 * @param y
	 * @return
	 */
	public Place nextStep(int x, int y){
		
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 13; j++){
				placeArr[i][j].setVisited(false);
				placeArr[i][j].setPreX(0);
				placeArr[i][j].setPreY(0);
			}
		}
		
		Queue<Place> queue = new LinkedList<>();
		//Stack<Place> stack = new Stack<>();
		
		Place cur = placeArr[x][y];
		Place next = null;
		cur.setVisited(true);
		
		queue.add(cur);
		
		while(!queue.isEmpty()){
			cur = queue.poll();
			if(cur.getPosX() == 0 || cur.getPosY() == 0 || cur.getPosX() == 8 || cur.getPosY() == 12){
				return cur;
			}
			for(int i = 0; i < 6; i++){
				int xx = 0, yy = 0;
				if(cur.getPosX() % 2 == 0){//奇数行
					xx = cur.getPosX() + singelRow[i][0];
					yy = cur.getPosY() + singelRow[i][1];
				}else{//偶数行
					xx = cur.getPosX() + doubleRow[i][0];
					yy = cur.getPosY() + doubleRow[i][1];
				}
				if(isInMap(xx, yy)){//如果该点在点阵之内的话
					next = placeArr[xx][yy];
					if(next.getType() == 1 && !next.isVisited()){//当这个点是空白点，并且还没有访问过的话
						next.setPreX(cur.getPosX());
						next.setPreY(cur.getPosY());
						next.setVisited(true);
						queue.add(next);
					}
				}
			}
			
		}
		return null;
	}
	/**
	 * 判断这个节点是否在阵点内
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isInMap(int x, int y){
		if(x >= 0 && x <= 8 && y >= 0 && y <= 12){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public int getCurrentX() {
		return currentX;
	}

	public void setCurrentX(int currentX) {
		this.currentX = currentX;
	}

	public int getCurrentY() {
		return currentY;
	}

	public void setCurrentY(int currentY) {
		this.currentY = currentY;
	}
	
	public int getStepCont() {
		return stepCont;
	}

	public void setStepCont(int stepCont) {
		this.stepCont = stepCont;
	}
}
