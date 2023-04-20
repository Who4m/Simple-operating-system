
public class ObjectNotFoundException extends FSException {

	FSObject obj;

	public ObjectNotFoundException(FSObject obj) {
		this.obj = obj;
	}

	@Override
	public String what() {
		String odgovor;
		odgovor = "This Object is not in this system";
		return odgovor;

	}

}
