import java.util.ArrayList;

public class ListDirectory extends FSOperation {
	public Folder folder;
	public ArrayList<FSObject> listDirectory;

	public ListDirectory(Folder folder, String opName) {
		super(opName);
		this.folder = folder;

	}

	public void execute() {
		this.listDirectory = folder.contains;
	}
}
