package versy;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import forum.framework.ForumModel;
import forum.framework.IForumModel;
import forum.framework.IForumView;

public class RmiModelReceiver implements IRemoteForumModel, Runnable{

	public static final String NAME = "RmiModelReceiver";
	
	private static final IForumModel model = ForumModel.INSTANCE;
	
	@Override
	public void deregisterView(String name) throws NotBoundException,
			IOException {
		model.deregisterView(name);
	}

	@Override
	public void moveEast(String name) throws NotBoundException, IOException {
		model.moveEast(name);
	}

	@Override
	public void moveNorth(String name) throws NotBoundException, IOException {
		model.moveNorth(name);
	}

	@Override
	public void moveSouth(String name) throws NotBoundException, IOException {
		model.moveSouth(name);		
	}

	@Override
	public void moveWest(String name) throws NotBoundException, IOException {
		model.moveWest(name);
	}

	@Override
	public void registerView(String name, IForumView view)
			throws AlreadyBoundException, IOException {
		model.registerView(name, view);
	}

	@Override
	public void run() {
		register();
	}
	
	private void register()
	{
		try {
			System.out.println("Receiver->registering...");
			Registry registry = LocateRegistry.getRegistry();
			
			IRemoteForumModel stub = (IRemoteForumModel) UnicastRemoteObject.exportObject(this, 0);
			
			registry.rebind(NAME, stub);
			System.out.println("Receiver->done.");
		} catch (Exception e) {
			System.err.printf("Receiver->%s: %s.", e.getClass().getName(), e.getMessage());
		}
	}

}
