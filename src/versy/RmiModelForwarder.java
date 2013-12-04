package versy;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import forum.framework.IForumModel;
import forum.framework.IForumView;

public class RmiModelForwarder implements IRemoteForumModel{

	private IForumModel receiver;
	
	public RmiModelForwarder()
	{
		getReceiver();
	}
	
	private void getReceiver()
	{
		try {
			System.out.println("ModelForwarder->getting ModelReceiver...");
			Registry registry = LocateRegistry.getRegistry("localhost");
			receiver = (IForumModel) registry.lookup(RmiModelReceiver.NAME);
			System.out.println("ModelForwarder->got ModelReceiver.");
		} catch (Exception e) {
			System.err.printf("%s: %s.", e.getClass().getName(), e.getMessage());
		}
	}
	
	@Override
	public void deregisterView(String name) throws NotBoundException,
			IOException {
		receiver.deregisterView(name);	
	}

	@Override
	public void moveEast(String name) throws NotBoundException, IOException {
		receiver.moveEast(name);
	}

	@Override
	public void moveNorth(String name) throws NotBoundException, IOException {
		receiver.moveNorth(name);
	}

	@Override
	public void moveSouth(String name) throws NotBoundException, IOException {
		receiver.moveSouth(name);
	}

	@Override
	public void moveWest(String name) throws NotBoundException, IOException {
		receiver.moveWest(name);
	}

	@Override
	public void registerView(String name, IForumView view)
			throws AlreadyBoundException, IOException {
		System.out.println("ModelForwarder->registering ViewReceiver...");
		receiver.registerView(name, (IForumView) UnicastRemoteObject.exportObject(new RmiViewReceiver(view), 0));
	}

}
