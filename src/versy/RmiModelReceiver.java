package versy;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import forum.framework.ForumModel;
import forum.framework.IForumView;

public class RmiModelReceiver implements IRemoteForumModel, Runnable{

	public static final String NAME = "RmiModelReceiver";
	
	@Override
	public void deregisterView(String name) throws NotBoundException,
			IOException {
		ForumModel.INSTANCE.deregisterView(name);
	}

	@Override
	public void moveEast(String name) throws NotBoundException, IOException {
		ForumModel.INSTANCE.moveEast(name);
	}

	@Override
	public void moveNorth(String name) throws NotBoundException, IOException {
		ForumModel.INSTANCE.moveNorth(name);
	}

	@Override
	public void moveSouth(String name) throws NotBoundException, IOException {
		ForumModel.INSTANCE.moveSouth(name);		
	}

	@Override
	public void moveWest(String name) throws NotBoundException, IOException {
		ForumModel.INSTANCE.moveWest(name);
	}

	@Override
	public void registerView(String name, IForumView view)
			throws AlreadyBoundException, IOException {
		System.out.println("ModelReceiver->registering ViewForwarder...");
		
		ForumModel.INSTANCE.registerView(name, new RmiViewForwarder(view));
	}

	@Override
	public void run() {
		startReg();
		register();
	}
	
	private void startReg()
	{
		try {
			LocateRegistry.createRegistry(1099);
		} catch (RemoteException e) {
			System.err.printf("Could not create Registry:\n%s: %s.", e.getClass().getName(), e.getMessage());
		}
	}
	
	private void register()
	{
		try {
			System.out.println("ModelReceiver->registering...");
			Registry registry = LocateRegistry.getRegistry();
			
			IRemoteForumModel stub = (IRemoteForumModel) UnicastRemoteObject.exportObject(this, 0);
			
			registry.rebind(NAME, stub);
			System.out.println("ModelReceiver->registering done.");
		} catch (Exception e) {
			System.err.printf("ModelReceiver->%s: %s.", e.getClass().getName(), e.getMessage());
		}
	}

}
