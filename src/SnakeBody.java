import java.util.ArrayList;
import java.util.Collections;
import java.awt.Point;

public class SnakeBody {
	private int size=6;
	private final int cellsize=20;
	private final int xbegin=100;
	private final int ybegin=100;
	private ArrayList<Point> bodycoords;

	private boolean snakedead;
	
	public SnakeBody(int size)
	{
		this.size=size;
		snakedead=false;
		bodycoords=new ArrayList<Point>(size);
		
		for(int i=0;i<size;i++)
		{
			bodycoords.add(i,new Point((xbegin+(i*cellsize)),ybegin));
		}
	}
	
	public SnakeBody()
	{
		snakedead=false;
		bodycoords=new ArrayList<Point>(size);
		
		for(int i=0;i<size;i++)
		{
			bodycoords.add(i,new Point((xbegin+(i*cellsize)),ybegin));
		}
	}
	
	public int getxBodyCell(int i)
	{
		return bodycoords.get(i).x;
	}
	
	public int getyBodyCell(int i)
	{
		return bodycoords.get(i).y;		
	}
	
	public void addBodyCell()
	{
		int x=bodycoords.get(size-1).x+20;
		int y=bodycoords.get(size-1).y+20;
		
		bodycoords.add(new Point(x,y));
		size++;
	}
	
	public void changeBodyCell(int index,int xelem,int yelem,char ch)
	{
		if(index==0)
		{
			for(int i=1;i<size;i++)
			{
				if(bodycoords.get(i).x==xelem && bodycoords.get(i).y==yelem)
				{
					snakedead=true;
					return;
				}				
			}
			
			if(xelem>470 || xelem<20 || yelem<20 || yelem>350)
			{
				snakedead=true;
				return;
			}
		}
		System.out.println("\n"+xelem+yelem);
		bodycoords.set(index,new Point(xelem,yelem));
	}

	public int bodySize()
	{
		return size;
	}
	
	public boolean isDead()
	{
		return snakedead;
	}
}
