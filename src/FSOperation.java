
public abstract class FSOperation {
	private String opName;

	public FSOperation(String opName) {
		this.opName = opName;
	}

	public String getName() {
		return opName;
	}

	public abstract void execute();
}
