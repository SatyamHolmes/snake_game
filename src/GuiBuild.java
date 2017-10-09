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
	private final int speed=400;
	private Point p1,p2;
	private char ch;
	private boolean start;
	private int sc;
	private String score;

	public static void main(String[] arg)
	{
		GuiBuild abc=new GuiBuild();
		abc.play();
	}
	
	public GuiBuild()
	{
		frame.setSize(500,450);
		frame.getContentPane().add(build);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		build.setSize(500,400);
		build.setLayout(null);
		build.setFocusable(true);
		build.requestFocusInWindow();
		build.addKeyListener(snakemove);
		sc=0;
		score="0";
		ch='a';
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
		build.addKeyListener(null);
		System.out.println("game ove");
	}

	
	class SnakeBuilder extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			Image img=new ImageIcon("../back.png").getImage();			
			g.drawImage(img,0,0,this);
			img=new ImageIcon("../food.png").getImage();
			g.drawImage(img,feed.foodloc.x,feed.foodloc.y,this);
			switch(ch)
			{
				case 'a': img=new ImageIcon("../headleft.png").getImage(); break;
				case 'd': img=new ImageIcon("../headright.png").getImage(); break;
				case 'w': img=new ImageIcon("../headup.png").getImage(); break;
				case 's': img=new ImageIcon("../headdown.png").getImage(); break;
			}
			g.drawImage(img,snake.getxBodyCell(0),snake.getyBodyCell(0),this);
			img=new ImageIcon("../Snake-skin-texture.jpg").getImage();
			for(int i=1;i< snake.bodySize();i++)
			{
				g.drawImage(img,snake.getxBodyCell(i),snake.getyBodyCell(i),this);
			}

			img=new ImageIcon("../board.png").getImage();
			g.drawImage(img,0,382,this);
			//g.fillRect(0,380,500,450);
			g.setColor(Color.WHITE);
			g.setFont(new Font("TimesRoman",Font.BOLD,20));
			g.drawString(score,250,400);
			
			if(snake.isDead())
				g.drawString("Game Over",200,420);

		}
	}
	
	class SnakeMovement implements KeyListener
	{
		public void moveUp()
		{
			p1=new Point(snake.getxBodyCell(0),snake.getyBodyCell(0));
			snake.changeBodyCell(0,p1.x,p1.y-movespeed);
			if(feed.hasEaten())
				snake.addBodyCell();
			for(int i=1;i<snake.bodySize();i++)
			{
				p2=new Point(snake.getxBodyCell(i),snake.getyBodyCell(i));
				snake.changeBodyCell(i,p1.x,p1.y);
				p1=p2;
			}
				build.repaint();
		}

		public void moveDown()
		{
			p1=new Point(snake.getxBodyCell(0),snake.getyBodyCell(0));
			snake.changeBodyCell(0,p1.x,p1.y+movespeed);
			if(feed.hasEaten())
				snake.addBodyCell();
			for(int i=1;i<snake.bodySize();i++)
			{
				p2=new Point(snake.getxBodyCell(i),snake.getyBodyCell(i));
				snake.changeBodyCell(i,p1.x,p1.y);
				p1=p2;
			}
				build.repaint();
		}

		public void moveLeft()
		{
			p1=new Point(snake.getxBodyCell(0),snake.getyBodyCell(0));
			snake.changeBodyCell(0,p1.x-movespeed,p1.y);
			if(feed.hasEaten())
				snake.addBodyCell();
			for(int i=1;i<snake.bodySize();i++)
			{
				p2=new Point(snake.getxBodyCell(i),snake.getyBodyCell(i));
				snake.changeBodyCell(i,p1.x,p1.y);
				p1=p2;
			}
				build.repaint();
		}

		public void moveRight()
		{
			p1=new Point(snake.getxBodyCell(0),snake.getyBodyCell(0));
			snake.changeBodyCell(0,p1.x+movespeed,p1.y);
			if(feed.hasEaten())
				snake.addBodyCell();
			for(int i=1;i<snake.bodySize();i++)
			{
				p2=new Point(snake.getxBodyCell(i),snake.getyBodyCell(i));
				snake.changeBodyCell(i,p1.x,p1.y);
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
			foodloc=new Point(rand.nextInt(420)+20,rand.nextInt(320)+20);
		}

		public boolean hasEaten()
		{
			int x=snake.getxBodyCell(0);
			int y=snake.getyBodyCell(0);
			if((x<=(foodloc.x+20) && x>=(foodloc.x-20)) && (y<=(foodloc.y+20) && y>=(foodloc.y-20)))
			{
				foodGenerate();
				sc++;
				score=""+sc;
				return true;
			}
			else
				return false;
		}
	}
}
