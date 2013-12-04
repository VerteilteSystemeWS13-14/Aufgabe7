package versy;

import java.io.Serializable;
import java.rmi.Remote;

import forum.framework.IForumView;

public interface IRemoteForumView extends IForumView, Remote, Serializable{

}
