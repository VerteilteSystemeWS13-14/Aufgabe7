package versy;

import java.io.IOException;
import java.util.Map;

import forum.framework.IForumView;
import forum.framework.Position;

public class RmiViewReceiver implements IRemoteForumView{

	private static final long serialVersionUID = -2324495364615492923L;
	
	private IForumView view;
	
	public RmiViewReceiver(IForumView p_view)
	{
		view = p_view;
	}
		
	@Override
	public void notifyView(Map<String, Position> folks) throws IOException {
		System.out.println("ViewReceiver->notifying folks...");
		view.notifyView(folks);
	}

}
