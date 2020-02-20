import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 场景类，里边包含所有需要加载进入当前场景的资源
 * @author Administrator
 *
 */
public class Scene {

	private BufferedImage bgImage = null;
	private int index;
	private int type;
	
	private List<Resource> resorceList = new ArrayList<>();
	private List<Place> placeList = new ArrayList<>();
	private Place placeArr[][] = new Place[9][13];
	
	/**
	 * 初始化场景
	 * index:1:开始2。。。。。。
	 * type：场景类型：1：开始类，2：游戏类，3：结束
	 * 根据场景类型设置背景图片
	 * @param index
	 * @param type
	 */
	public Scene(int index, int type){
		this.index = index;
		this.type = type;
		
		if(type == 1){
			this.bgImage = StaticValue.startImage;
		}else if(type == 2){
			this.bgImage = StaticValue.gameImage;
		}
		
		if(index == 1){//开始界面中有一个开始按钮
			Resource startButton = new Resource(160, 350, StaticValue.startButton);
			Resource endButton = new Resource(360, 350, StaticValue.endButton);
			resorceList.add(startButton);
			resorceList.add(endButton);
		}
		if(index == 2){//提示界面1
			Resource info1 = new Resource(140, 70, StaticValue.info1);
			resorceList.add(info1);
		}
		if(index == 3){//提示界面2
			Resource info2 = new Resource(140, 70, StaticValue.info2);
			resorceList.add(info2);
		}
		
		if(index == 4){//游戏界面，需要初始化所有的位置点,应该是13行和10列
			for(int i = 0; i < 9; i++){
				for(int j = 0; j < 13; j++){
					int x = 0;
					int y = 0;
					if(i % 2 == 0){//奇数行
						x = 40 + j * 40;
						y = 40 + i * 40;
					}else{//偶数行
						x = 60 + j * 40;
						y = 40 + i * 40;
					}
					Place place = null;
					Random r = new Random();
					
					//区间的整数，如果是1的话，就把当前的点置成障碍物
					//这样障碍物的设置理论上就是随机的，大约在20个左右,根据配置的难度随机产生
					int haha = r.nextInt(Config.nandu);
					
					if(i == 4 && j == 6){
						place = new Place(x, y, StaticValue.cat, 3);
					}else if(haha == 1){
						place = new Place(x, y, StaticValue.luzhang, 2);
					}else{
						place = new Place(x, y, StaticValue.place, 1);
					}
					place.setPosX(i);
					place.setPosY(j);
					placeArr[i][j] = place;
					placeList.add(place);
				}
			}
		}
		if(index == 5){//游戏结束界面
			Resource endButton = new Resource(440, 320, StaticValue.startButton);
			resorceList.add(endButton);
		}
		
	}
	public Place[][] getPlaceArr() {
		return placeArr;
	}
	public void setPlaceArr(Place[][] placeArr) {
		this.placeArr = placeArr;
	}
	public BufferedImage getBgImage() {
		return bgImage;
	}
	public void setBgImage(BufferedImage bgImage) {
		this.bgImage = bgImage;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public List<Resource> getResorceList() {
		return resorceList;
	}
	public void setResorceList(List<Resource> resorceList) {
		this.resorceList = resorceList;
	}
	public List<Place> getPlaceList() {
		return placeList;
	}
	public void setPlaceList(List<Place> placeList) {
		this.placeList = placeList;
	}
}
