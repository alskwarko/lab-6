import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Kontrast {

	public Kontrast() {
		try {

		 //zapis do pliku zmodyfikowanego obrazu
			File ouptut;
			
			ouptut = new File("res/inContrast.jpg");
			ImageIO.write(inKontrast("res/2.jpg", 50), "jpg", ouptut);
			
			ouptut = new File("res/deContrast.jpg");
			ImageIO.write(deKontrast("res/2.jpg", -50), "jpg", ouptut);
			
		} catch (Exception e) {}
	}
	
	// zwiekszanie kontrastu
	public BufferedImage inKontrast(String strImg, int b) throws IOException {
		BufferedImage img = ImageIO.read(new File(strImg));
		int width = img.getWidth();
		int height = img.getHeight();
		
		for(int i=0; i<height; i++){
			 
			 for(int j=0; j<width; j++){
	
				 Color c = new Color(img.getRGB(j, i));
				 int red = (int)(c.getRed());
				 int green = (int)(c.getGreen());
				 int blue = (int)(c.getBlue());

				 red = (127 / (127 - b)) * (red - b);
				 green = (127 / (127 - b)) * (green - b);
				 blue = (127 / (127 - b)) * (blue - b);
				
				 red = sprawdzenie(red);
				 green = sprawdzenie(green);
				 blue = sprawdzenie(blue);
	 
				 Color newColor = new Color(red, green,blue);
				 img.setRGB(j,i,newColor.getRGB());

			 }
		}
	
		return img;
		
	}
	
	// zmniejszenie kontrastu
		public BufferedImage deKontrast(String strImg, int b) throws IOException {
			BufferedImage img = ImageIO.read(new File(strImg));
			int width = img.getWidth();
			int height = img.getHeight();
			
			for(int i=0; i<height; i++){
				 
				 for(int j=0; j<width; j++){
		
					 Color c = new Color(img.getRGB(j, i));
					 int red = (int)(c.getRed());
					 int green = (int)(c.getGreen());
					 int blue = (int)(c.getBlue());

					 red = ((127 - b) / 127) * (red - b);
					 green = ((127 - b)/ 127) * (green - b);
					 blue = ((127 - b) / 127) * (blue - b);
					
					 red = sprawdzenie(red);
					 green = sprawdzenie(green);
					 blue = sprawdzenie(blue);
		 
					 Color newColor = new Color(red, green,blue);
					 img.setRGB(j,i,newColor.getRGB());

				 }
			}
		
			return img;
			
		}
	
	int sprawdzenie(int x)
    {
        if (x > 255)
            return 255;
        else if (x < 0)
            return 0;
        else
        	return x;
    }
		
	public static void main(String[] args) {
		// konwersja zdjec
		Kontrast obj = new Kontrast();
		
		// okno do zdjec
		int WIDTH = 1200, HEIGHT = 600;
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = screenSize.width / 2 - WIDTH / 2;
		int y = screenSize.height / 2 - HEIGHT / 2;
		
		  JFrame f=new JFrame("Image Processing");
		  f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// zamykanie okna kiedy nacisniemy krzyzyk
		  f.setBounds(x, y,WIDTH, HEIGHT);
		  
		  Image image = new ImageIcon("res/2.jpg").getImage();
		  Image image1 = new ImageIcon("res/inContrast.jpg").getImage();
		  Image image2 = new ImageIcon("res/deContrast.jpg").getImage();
		  
		  JLabel label1 = new JLabel(new ImageIcon(image));
	      label1.setBounds(0,0, 500,500);  
	      JLabel label2 = new JLabel(new ImageIcon(image1));
	      label2.setBounds(0,0, 500,500);
	      JLabel label3 = new JLabel(new ImageIcon(image2));
	      label3.setBounds(0,0, 500,500);  
	      
		  JPanel panel1=new JPanel();  
	      panel1.setBounds(20,20,500,500); 
     
	      panel1.add(label1);
	  	      
	      JPanel panel2=new JPanel();  
	      panel2.setBounds(680,10,500,500);
	      
	      // button 1 dla zwiekszania kontrastu
		  JButton b1=new JButton("Zwiekszanie kontrastu");  
		  b1.setBounds(550,100,100,50); 
		  b1.addActionListener(new ActionListener()
		  {  
			  public void actionPerformed(ActionEvent e){ 
				  panel2.removeAll();
				  panel2.add(label2); 
		          f.repaint();
			  } 
		  });

		  // button 2 dla zmniejszania kontrastu
		  JButton b2=new JButton("Zmniejszenie kontrastu");  
		  b2.setBounds(550,200,100,50); 
		  b2.addActionListener(new ActionListener()
		  {  
			  public void actionPerformed(ActionEvent e){  
				  panel2.removeAll();
				  panel2.add(label3); 
		          f.repaint();  
			  } 
		  });
		  
		  
		  
		  f.add(panel1);
		  f.add(panel2);
		  f.add(b1);
		  f.add(b2);
	      f.setLayout(null);  
		  f.setVisible(true);
		  
	}

}
