package versy;

import forum.framework.ForumClient;

public class RmiForumClient {

	public static void main(String[] args)
	{
		try {
			ForumClient client = new ForumClient(new RmiModelForwarder());
			client.register();
		} catch (Exception e) {
			System.err.printf("%s: %s.", e.getClass().getName(), e.getMessage());
		}
		
	}
}
