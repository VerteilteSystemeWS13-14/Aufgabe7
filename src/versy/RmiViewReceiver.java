package versy;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import forum.framework.IForumView;
import forum.framework.Position;

public class RmiViewReceiver implements IRemoteForumView{

	private List<IForumView> viewList;
	
	private RmiViewReceiver()
	{
		viewList = new LinkedList<IForumView>();
	}
	
	public void addView(IForumView view)
	{
		viewList.add(view);
	}
	
	@Override
	public void notifyView(Map<String, Position> arg0) throws IOException {
		for(IForumView view : viewList)
			view.notifyView(arg0);
	}

}
