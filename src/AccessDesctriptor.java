import java.util.ArrayList;

public class AccessDesctriptor {
	private FSObject objekat;
	private ArrayList<String> operacije;

	public AccessDesctriptor() {
		operacije = new ArrayList<String>();
	}

	public void setObjekat(FSObject objekat) {
		this.objekat = objekat;
	}

	public void add(String imeOperacije) {
		for (int i = 0; i < operacije.size(); i++) {
			if (operacije.get(i).equals(imeOperacije)) {
				System.out.println("You already have access to this operation");
				return;
			}
		}
		operacije.add(imeOperacije);
	}

	public void remove(String imeOperacije) {
		for (int i = 0; i < operacije.size(); i++) {
			if (operacije.get(i).equals(imeOperacije)) {
				operacije.remove(i);
				return;
			}
		}
		System.out.println("That operation is not listed");
		return;
	}

	public ArrayList<String> getAllowedOperations() {
		return operacije;
	}

	public boolean checkAccess(String imeOperacije) {
		for (int i = 0; i < operacije.size(); i++) {
			if (operacije.get(i).equals(imeOperacije)) {
				return true;
			}
		}
		return false;
	}

	public String toString() {
		String odgovor;
		odgovor = "Ja sam access za obj: " + objekat.getName();
		return odgovor;
	}
}
