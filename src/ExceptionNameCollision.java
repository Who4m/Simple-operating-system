
public class ExceptionNameCollision extends FSException {

	Folder folder;
	String name;

	public ExceptionNameCollision(Folder folder, String name) {
		this.folder = folder;
		this.name = name;
	}

	public String what() {
		String odgovor;
		odgovor = "This name is already used";
		return odgovor;
	}

}
