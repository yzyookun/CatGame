

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class StaticValue {
	
	public static BufferedImage startImage=null;
	public static BufferedImage gameImage=null;
	
	public static BufferedImage info1=null;
	public static BufferedImage info2=null;

	
	public static BufferedImage startButton=null;
	public static BufferedImage endButton=null;
	
	public static BufferedImage place=null;
	public static BufferedImage cat=null;
	public static BufferedImage luzhang=null;
	
	public static BufferedImage nandu=null;
	public static BufferedImage restart=null;
	
	
	public static String imagePath=System.getProperty("user.dir")+"//bin/";
	
	//将图片全部初始化
	
	public static void init()
	{	
		try {
			startImage = ImageIO.read(new File(imagePath+"start.png"));
			gameImage=ImageIO.read(new File(imagePath+"background.png"));

			
			info1 = ImageIO.read(new File(imagePath+"info1.png"));
			info2=ImageIO.read(new File(imagePath+"info2.png"));
			
			startButton = ImageIO.read(new File(imagePath+"startButton.png"));
			endButton = ImageIO.read(new File(imagePath+"endButton.png"));
			
			place = ImageIO.read(new File(imagePath+"place.png"));
			cat = ImageIO.read(new File(imagePath+"cat.png"));
			luzhang = ImageIO.read(new File(imagePath+"luzhang.png"));
			
			nandu = ImageIO.read(new File(imagePath+"nandu.png"));
			restart = ImageIO.read(new File(imagePath+"restart.png"));
			
		} catch (IOException e) {
			System.out.println("初始化图片出错");
			e.printStackTrace();
		}
	}
}
