
public class MemoryOwerflowException extends FSException {

	public MemoryOwerflowException() {
	}

	@Override
	public String what() {
		String odgovor;
		odgovor = "We dont have enought memory";
		return odgovor;
	}

}
