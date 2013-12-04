package versy;

import java.io.IOException;
import java.util.Map;

import forum.framework.IForumView;
import forum.framework.Position;

public class RmiViewForwarder implements IRemoteForumView {

	private static final long serialVersionUID = -5327954658183400673L;
	
	private IForumView view;
	
	public RmiViewForwarder(IForumView p_view)
	{
		view = p_view;
	}
	
	@Override
	public void notifyView(Map<String, Position> folks) throws IOException {
		System.out.println("ViewForwarder->notifying folks...");
		view.notifyView(folks);
	}

}
