
public class CreateFile extends FSOperation {

	public Folder parent;
	public File novi;

	public CreateFile(String opName, String name, Folder parent) {
		super(opName);
		this.parent = parent;
		novi = new File(name);
	}

	public void execute() {
		parent.add(novi);
	}
}
