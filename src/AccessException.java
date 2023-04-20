
public class AccessException extends FSException {

	FSObject objekat;
	String opName;

	public AccessException(FSObject obj, String opName) {
		this.objekat = obj;
		this.opName = opName;
	}

	public String what() {
		String odgovor;
		odgovor = "You dont have access to this operation: " + opName + " On object: " + objekat.getName();
		return odgovor;
	}

}
