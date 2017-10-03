import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GuiBuild extends KeyAdapter{
	private JFrame frame=new JFrame();
	private JButton button=new JButton();
	private SnakeBody snake=new SnakeBody();
	private SnakeMovement snakemove=new SnakeMovement();
	private int speed=1,x1,x,y,y1;
	private char ch;
	
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
		ch='a';
	}
	
	public void play()
	{
		while(!snake.isDead())
		{
			
		}
	}

	public void moveUp()
	{
		x=snake.getxBodyCell(0);
		y=snake.getyBodyCell(0);
		snake.changeBodyCell(0,snake.getxBodyCell(0),snake.getyBodyCell(0)-speed);
		for(int i=1;i<snake.bodySize();i++)
		{
			x1=x;
			y1=y;
			snake.changeBodyCell(i,x,y);
			x=x1;
			y=y1;
		}
		if(!snake.isDead())
			snakemove.repaint();
	}

	public void moveDown()
	{
		x=snake.getxBodyCell(0);
		y=snake.getyBodyCell(0);
		snake.changeBodyCell(0,snake.getxBodyCell(0),snake.getyBodyCell(0)+speed);
		for(int i=1;i<snake.bodySize();i++)
		{
			x1=x;
			y1=y;
			snake.changeBodyCell(i,x,y);
			x=x1;
			y=y1;
		}
		if(!snake.isDead())
			snakemove.repaint();
	}

	public void moveLeft()
	{
		x=snake.getxBodyCell(0);
		y=snake.getyBodyCell(0);
		snake.changeBodyCell(0,snake.getxBodyCell(0)+speed,snake.getyBodyCell(0));
		for(int i=1;i<snake.bodySize();i++)
		{
			x1=x;
			y1=y;
			snake.changeBodyCell(i,x,y);
			x=x1;
			y=y1;
		}
		if(!snake.isDead())
			snakemove.repaint();
	}

	public void moveRight()
	{
		x=snake.getxBodyCell(0);
		y=snake.getyBodyCell(0);
		snake.changeBodyCell(0,snake.getxBodyCell(0)-speed,snake.getyBodyCell(0));
		for(int i=1;i<snake.bodySize();i++)
		{
			x1=x;
			y1=y;
			snake.changeBodyCell(i,x,y);
			x=x1;
			y=y1;
		}
		if(!snake.isDead())
			snakemove.repaint();
	}

	public void keyPressed(KeyEvent e)
	{
		char ch=e.getKeyChar();
		switch(ch)
		{
			case 'w': moveUp(); break;
			case 'a': moveLeft(); break;
			case 'd': moveRight(); break;
			case 's': moveDown(); break;
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