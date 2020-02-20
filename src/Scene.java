import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * �����࣬��߰���������Ҫ���ؽ��뵱ǰ��������Դ
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
	 * ��ʼ������
	 * index:1:��ʼ2������������
	 * type���������ͣ�1����ʼ�࣬2����Ϸ�࣬3������
	 * ���ݳ����������ñ���ͼƬ
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
		
		if(index == 1){//��ʼ��������һ����ʼ��ť
			Resource startButton = new Resource(160, 350, StaticValue.startButton);
			Resource endButton = new Resource(360, 350, StaticValue.endButton);
			resorceList.add(startButton);
			resorceList.add(endButton);
		}
		if(index == 2){//��ʾ����1
			Resource info1 = new Resource(140, 70, StaticValue.info1);
			resorceList.add(info1);
		}
		if(index == 3){//��ʾ����2
			Resource info2 = new Resource(140, 70, StaticValue.info2);
			resorceList.add(info2);
		}
		
		if(index == 4){//��Ϸ���棬��Ҫ��ʼ�����е�λ�õ�,Ӧ����13�к�10��
			for(int i = 0; i < 9; i++){
				for(int j = 0; j < 13; j++){
					int x = 0;
					int y = 0;
					if(i % 2 == 0){//������
						x = 40 + j * 40;
						y = 40 + i * 40;
					}else{//ż����
						x = 60 + j * 40;
						y = 40 + i * 40;
					}
					Place place = null;
					Random r = new Random();
					
					//����������������1�Ļ����Ͱѵ�ǰ�ĵ��ó��ϰ���
					//�����ϰ�������������Ͼ�������ģ���Լ��20������,�������õ��Ѷ��������
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
		if(index == 5){//��Ϸ��������
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
