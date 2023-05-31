package library.management.com.example.icons;

import java.awt.Image;

import javax.swing.ImageIcon;

public class IconSet {
    // add more icon paths here
    private static final String HOME_ICON_PATH =  "image/home.jpg";
    private static final String PASSWORD_PATH =  "image/password.jpg";
    private static final String PIKACHU_PATH =  "image/pikachu.jpg";
    private static final String PIKACHU1_PATH =  "image/pikachu1.jpg";
    private static final String PIKACHU2_PATH =  "image/pikachu2.jpg";
    
    // add more getter methods for other icons here
	public static ImageIcon getHomeIcon(int width, int height) {
		ImageIcon homeIcon = new ImageIcon(IconSet.class.getResource(HOME_ICON_PATH));
		Image image = homeIcon.getImage();
		Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}
	
	public static ImageIcon getLogin(int width, int height) {
		ImageIcon loginIcon = new ImageIcon(IconSet.class.getResource(PASSWORD_PATH));
		Image image = loginIcon.getImage();
		Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}
	
	public static ImageIcon getPikachu(int width, int height) {
		ImageIcon PikachuIcon = new ImageIcon(IconSet.class.getResource(PIKACHU_PATH));
		Image image = PikachuIcon.getImage();
		Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}
	
	public static ImageIcon getPikachu1(int width, int height) {
		ImageIcon Pikachu1Icon = new ImageIcon(IconSet.class.getResource(PIKACHU1_PATH));
		Image image = Pikachu1Icon.getImage();
		Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}
	
	public static ImageIcon getPikachu2(int width, int height) {
		ImageIcon Pikachu2Icon = new ImageIcon(IconSet.class.getResource(PIKACHU2_PATH));
		Image image = Pikachu2Icon.getImage();
		Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}
}
