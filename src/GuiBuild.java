import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Point;
import java.util.Random;
import java.util.LinkedList;

public class GuiBuild extends JPanel{
	private JFrame frame=new JFrame();
	private JButton button=new JButton();
	private SnakeBuilder build=new SnakeBuilder();
	private SnakeBody snake=new SnakeBody();
	private SnakeMovement snakemove=new SnakeMovement();
	private FeedSnake feed=new FeedSnake();
	private final int movespeed=20;
	private final int speed=500;
	private Point p1,p2;
	private boolean start;

	public static void main(String[] arg)
	{
		GuiBuild abc=new GuiBuild();
		abc.play();
	}
	
	public GuiBuild()
	{
		frame.setSize(500,400);
		frame.getContentPane().add(build);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		build.setSize(500,400);
		build.setLayout(null);
		build.setFocusable(true);
		build.requestFocusInWindow();
		build.addKeyListener(snakemove);
		start=false;
	}
	
	public void play()
	{
		while(!snake.isDead())
		{
			if(start)
				snakemove.chooseDirection();
			try{
				Thread.sleep(speed);
			}
			catch(Exception e)
			{

			}
		}
		System.out.println("game ove");
	}


	class SnakeBuilder extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			Image img=new ImageIcon("/home/satyamholmes/Desktop/Eclipse/Snake-game/brown-background-18642-19114-hd-wallpapers.jpg").getImage();			
			g.drawImage(img,0,0,this);
			img=new ImageIcon("/home/satyamholmes/Desktop/Eclipse/Snake-game/food.png").getImage();
			g.drawImage(img,feed.foodloc.x,feed.foodloc.y,this);
			img=new ImageIcon("/home/satyamholmes/Desktop/Eclipse/Snake-game/Snake-skin-texture.jpg").getImage();
			for(int i=0;i< snake.bodySize();i++)
			{
				g.drawImage(img,snake.getxBodyCell(i),snake.getyBodyCell(i),this);
			}
		}
	}
	
	class SnakeMovement implements KeyListener
	{
		private LinkedList<Character> chlist=new LinkedList<Character>();
		private char ch;
		public void moveUp()
		{
			p1=new Point(snake.getxBodyCell(0),snake.getyBodyCell(0));
			snake.changeBodyCell(0,p1.x,p1.y-movespeed,'u');
			if(feed.hasEaten())
				snake.addBodyCell();
			for(int i=1;i<snake.bodySize();i++)
			{
				p2=new Point(snake.getxBodyCell(i),snake.getyBodyCell(i));
				snake.changeBodyCell(i,p1.x,p1.y,'u');
				p1=p2;
			}
				build.repaint();
		}

		public void moveDown()
		{
			p1=new Point(snake.getxBodyCell(0),snake.getyBodyCell(0));
			snake.changeBodyCell(0,p1.x,p1.y+movespeed,'d');
			if(feed.hasEaten())
				snake.addBodyCell();
			for(int i=1;i<snake.bodySize();i++)
			{
				p2=new Point(snake.getxBodyCell(i),snake.getyBodyCell(i));
				snake.changeBodyCell(i,p1.x,p1.y,'d');
				p1=p2;
			}
				build.repaint();
		}

		public void moveLeft()
		{
			p1=new Point(snake.getxBodyCell(0),snake.getyBodyCell(0));
			snake.changeBodyCell(0,p1.x-movespeed,p1.y,'l');
			if(feed.hasEaten())
				snake.addBodyCell();
			for(int i=1;i<snake.bodySize();i++)
			{
				p2=new Point(snake.getxBodyCell(i),snake.getyBodyCell(i));
				snake.changeBodyCell(i,p1.x,p1.y,'l');
				p1=p2;
			}
				build.repaint();
		}

		public void moveRight()
		{
			p1=new Point(snake.getxBodyCell(0),snake.getyBodyCell(0));
			snake.changeBodyCell(0,p1.x+movespeed,p1.y,'r');
			if(feed.hasEaten())
				snake.addBodyCell();
			for(int i=1;i<snake.bodySize();i++)
			{
				p2=new Point(snake.getxBodyCell(i),snake.getyBodyCell(i));
				snake.changeBodyCell(i,p1.x,p1.y,'r');
				p1=p2;
			}
				build.repaint();
		}

		public void chooseDirection()
		{
			System.out.println(snake.bodySize());
			
			switch(ch)
			{
				case 'w': moveUp(); break;
				case 'a': moveLeft(); break;
				case 'd': moveRight(); break;
				case 's': moveDown(); break;
			}
		}

		public void keyPressed(KeyEvent e)
		{
			start=true;
			ch=e.getKeyChar();
			chooseDirection();
		}

		public void keyReleased(KeyEvent e)
		{

		}

		public void keyTyped(KeyEvent e)
		{

		}
	}

	class FeedSnake
	{
		public Point foodloc;
		private Random rand=new Random();
		private FeedSnake()
		{
			foodGenerate();
		}

		public void foodGenerate()
		{
			foodloc=new Point(rand.nextInt(450),rand.nextInt(350));
		}

		public boolean hasEaten()
		{
			int x=snake.getxBodyCell(0);
			int y=snake.getyBodyCell(0);
			if((x<=(foodloc.x+20) && x>=(foodloc.x-20)) && (y<=(foodloc.y+20) && y>=(foodloc.y-20)))
			{
				foodGenerate();
				return true;
			}
			else
				return false;
		}
	}
}