import java.util.ArrayList;

public class Search extends FSOperation {
	public String name;
	public ArrayList<FSObject> list;
	Folder root;

	public Search(String opName, String name, Folder root) {
		super(opName);
		this.name = name;
		this.root = root;
	}

	public void execute() {
		SearchVisitor visitor = new SearchVisitor(name);
		root.accept(visitor);
		this.list = visitor.foundObjects;
	}
}
