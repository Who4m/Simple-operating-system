
public class DeleteObject extends FSOperation {

	FSObject obj;

	public DeleteObject(String opName, FSObject obj) {
		super(opName);
		this.obj = obj;
	}

	public void execute() {
		obj.delete();
	}
}
