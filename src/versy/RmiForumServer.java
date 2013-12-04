package versy;

import forum.framework.ForumServer;

public class RmiForumServer {
	
	private static ForumServer server;
	
	public static void main(String[] args)
	{
		server = new ForumServer(new RmiModelReceiver());
		try {
			server.run();
			System.out.println("Server up and running.");
		} catch (Exception e) {
			System.err.printf("ForumServer->%s: %s.", e.getClass().getName(), e.getMessage());
		}
	}
}
