
public class CopyPaste extends FSOperation {
	FSObject copy;
	Folder paste;
	String newName;

	public CopyPaste(String opName, FSObject copy, Folder newParent, String newName) {
		super(opName);
		this.copy = copy;
		this.paste = paste;
		this.newName = newName;
	}

	public void execute() {
		FSObject newCopy;
		newCopy = copy.copy();
		newCopy.setName(newName);
		paste.add(newCopy);
	}
}
