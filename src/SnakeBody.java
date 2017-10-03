import java.util.ArrayList;
import java.util.Collections;

public class SnakeBody {
	private int size=6;
	private final int cellsize=20;
	private final int xbegin=100;
	private final int ybegin=100;
	private ArrayList<Integer> xbodycoords;
	private ArrayList<Integer> ybodycoords;
	private boolean snakedead;
	
	public SnakeBody(int size)
	{
		this.size=size;
		snakedead=false;
		xbodycoords=new ArrayList<Integer>(size);
		ybodycoords=new ArrayList<Integer>(size);
		
		for(int i=0;i<size;i++)
		{
			xbodycoords.add(i,(xbegin+(i*cellsize)));
			ybodycoords.add(i,ybegin);
		}
	}
	
	public SnakeBody()
	{
		snakedead=false;
		xbodycoords=new ArrayList<Integer>(size);
		ybodycoords=new ArrayList<Integer>(size);
		
		for(int i=0;i<size;i++)
		{
			try
			{
				xbodycoords.add(i,(xbegin+(i*cellsize)));
				ybodycoords.add(i,ybegin);
			}
			catch(Exception e){}
		}
	}
	
	public int getxBodyCell(int i)
	{
		return xbodycoords.get(i);
	}
	
	public int getyBodyCell(int i)
	{
		return ybodycoords.get(i);		
	}
	
	public void addBodyCell()
	{
		size++;
		xbodycoords.add(xbodycoords.get(size-1)+20);
		ybodycoords.add(ybodycoords.get(size-1)+20);
	}
	
	public void changeBodyCell(int index,int xelem,int yelem)
	{
		if(index==0)
		{
			if(Collections.binarySearch(xbodycoords,xelem)>0 && Collections.binarySearch(ybodycoords,yelem)>0)
			{
				snakedead=true;
				return;
			}
		}
		xbodycoords.set(index,xelem);
		ybodycoords.set(index,yelem);
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
