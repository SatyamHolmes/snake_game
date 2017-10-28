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
	private final int movespeed=10;
	private final int speed=100;
	private Point p1,p2;
	private char ch;
	private boolean start;
	private int sc;
	private String score;
	private Image back;
	private Image food;
	private Image board;

	public static void main(String[] arg)
	{
		GuiBuild abc=new GuiBuild();
		abc.play();
	}
	
	public GuiBuild()
	{
		back=new ImageIcon("../back.png").getImage();
		food=new ImageIcon("../food.png").getImage();
		board=new ImageIcon("../board.png").getImage();
		sc=0;
		score="0";
		ch='a';
		start=false;
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
	}
	
	public void play()
	{
			try
			{
				Timer time=new Timer(60,new ActionListener(){
					public void actionPerformed(ActionEvent a)
					{
						if(!snake.isDead())
						{
							if(start)
								snakemove.chooseDirection();
						}
						else
						{
							build.removeKeyListener(snakemove);
							((Timer)a.getSource()).stop();
							System.out.println("game over");
						}
					}
				});

				time.start();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	}

	
	class SnakeBuilder extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			g.drawImage(back,0,0,this);
			g.drawImage(food,feed.foodloc.x,feed.foodloc.y,this);
			/*
			switch(ch)
			{
				case 'a': img=new ImageIcon("../headleft.png").getImage(); break;
				case 'd': img=new ImageIcon("../headright.png").getImage(); break;
				case 'w': img=new ImageIcon("../headup.png").getImage(); break;
				case 's': img=new ImageIcon("../headdown.png").getImage(); break;
			}

			g.drawImage(img,snake.getxBodyCell(0),snake.getyBodyCell(0),this);
			*/
			g.setColor(Color.BLUE);
			g.fillOval(snake.getxBodyCell(0),snake.getyBodyCell(0),10,10);
			//img=new ImageIcon("../Snake-skin-texture.jpg").getImage();
			for(int i=1;i< snake.bodySize();i++)
			{
				//g.drawImage(img,snake.getxBodyCell(i),snake.getyBodyCell(i),this);
				g.fillRect(snake.getxBodyCell(i),snake.getyBodyCell(i),10,10);
			}

			g.drawImage(board,0,382,this);
			g.setColor(Color.WHITE);
			g.setFont(new Font("TimesRoman",Font.BOLD,20));
			g.drawString(score,250,400);

			Toolkit.getDefaultToolkit().sync();
			
			if(snake.isDead())
				g.drawString("Game Over",200,420);

		}
	}
	
	class SnakeMovement implements KeyListener
	{
		private void moveUp()
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
		}

		private void moveDown()
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
		}

		private void moveLeft()
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
		}

		private void moveRight()
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
			build.repaint();
		}

		public void keyPressed(KeyEvent e)
		{
			start=true;
			ch=e.getKeyChar();
			//chooseDirection();
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
