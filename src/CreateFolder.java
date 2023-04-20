
public class CreateFolder extends FSOperation {

	public Folder parent;
	public Folder novi;

	public CreateFolder(String opName, String name, Folder parent) {
		super(opName);
		novi = new Folder(name);
		this.parent = parent;
	}

	public void execute() {
		parent.add(novi);
	}
}
