import javax.swing.*;
import java.awt.*;

public class GuiBuild{
	JFrame frame=new JFrame();
	JButton button=new JButton();
	SnakeBody snake=new SnakeBody();
	SnakeMovement snakemove=new SnakeMovement();
	int speed=1;
	public static void main(String[] arg)
	{
		GuiBuild abc=new GuiBuild();
		abc.play();
	}
	
	public GuiBuild()
	{
		frame.setSize(500,400);
		frame.getContentPane().add(snakemove);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		snakemove.setSize(500,400);
		snakemove.setLayout(null);
	}
	
	public void play()
	{
		while(!snake.isDead())
		{
			for(int i=0;i<snake.bodySize();i++)
			{
				snake.changeBodyCell(i,snake.getxBodyCell(i)-speed,snake.getyBodyCell(i));
			}
			if(!snake.isDead())
				snakemove.repaint();
			try
			{
				Thread.sleep(1000);
			}
			catch(Exception e)
			{}
		}
	}
	
	class SnakeMovement extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			Image img=new ImageIcon("/home/satyamholmes/Desktop/Eclipse/Snake-game/brown-background-18642-19114-hd-wallpapers.jpg").getImage();
			g.drawImage(img,0,0,this);
			img=new ImageIcon("/home/satyamholmes/Desktop/Eclipse/Snake-game/Snake-skin-texture.jpg").getImage();
			for(int i=0;i< snake.bodySize();i++)
			{
				g.drawImage(img,snake.getxBodyCell(i),snake.getyBodyCell(i),this);
			}
		}
	}
}