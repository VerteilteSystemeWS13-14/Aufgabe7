package versy;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

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
			Registry registry = LocateRegistry.getRegistry();
			receiver = (IForumModel) registry.lookup(RmiModelReceiver.NAME);
		} catch (Exception e) {
			System.err.printf("%s: %s.", e.getClass().getName(), e.getMessage());
		}
	}
	
	@Override
	public void deregisterView(String arg0) throws NotBoundException,
			IOException {
		receiver.deregisterView(arg0);	
	}

	@Override
	public void moveEast(String arg0) throws NotBoundException, IOException {
		receiver.moveEast(arg0);
	}

	@Override
	public void moveNorth(String arg0) throws NotBoundException, IOException {
		receiver.moveNorth(arg0);
	}

	@Override
	public void moveSouth(String arg0) throws NotBoundException, IOException {
		receiver.moveSouth(arg0);
	}

	@Override
	public void moveWest(String arg0) throws NotBoundException, IOException {
		receiver.moveWest(arg0);
	}

	@Override
	public void registerView(String arg0, IForumView arg1)
			throws AlreadyBoundException, IOException {
		receiver.registerView(arg0, arg1);
	}

}
