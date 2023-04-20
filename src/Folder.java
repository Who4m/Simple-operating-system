import java.util.ArrayList;

public class Folder extends FSObject {
	public ArrayList<FSObject> contains;

	public Folder(String name) {

		super(name);
		super.getAccessDescriptor().setObjekat(this);
		contains = new ArrayList<FSObject>();
	}

	public Folder() {
		super();
		contains = new ArrayList<FSObject>();
	}

	public Folder copy() {
		Folder copy = new Folder();
		FSObject pamti;
		AccessDesctriptor copyAccess = new AccessDesctriptor();
		copy.setName(super.getName());
		for (int i = 0; i < super.getAccessDescriptor().getAllowedOperations().size(); i++) {
			copyAccess.add(super.getAccessDescriptor().getAllowedOperations().get(i));
		}
		copyAccess.setObjekat(copy);
		copy.setAccessDesctriptor(copyAccess);
		for (int i = 0; i < contains.size(); i++) {
			pamti = contains.get(i).copy();
			copy.add(pamti);
			pamti.setPerent(copy);
		}
		return copy;
	}

	private boolean sadrzi(FSObject obj) {
		for (int i = 0; i < contains.size(); i++) {
			if (contains.get(i) == obj) {
				return true;
			}
		}
		return false;
	}

	public void add(FSObject obj) {
		if (sadrzi(obj)) {
			System.out.println("Object not added");
			return;
		}
		obj.setPerent(this);
		String parentP;
		parentP = this.getPath() + "/" + obj.getName();
		obj.setPath(parentP);
		contains.add(obj);

	}

	public long size() {
		long size = 0;
		for (int i = 0; i < contains.size(); i++) {
			size += contains.get(i).size();
		}
		return size;
	}

	public void remove(FSObject obj) {
		for (int i = 0; i < contains.size(); i++) {
			if (contains.get(i) == obj) {
				contains.get(i).setPerent(null);
				contains.remove(i);
			}
		}

	}

	public void delete() {
		contains.clear();
		parent.remove(this);
		super.setAccessDesctriptor(null);
		super.setName(null);
		parent = null;
	}

	public String toString() {
		String odgovor = "Ime ovog Foldera je: " + super.getName();
		for (int i = 0; i < contains.size(); i++) {
			odgovor += "\n";
			odgovor += contains.get(i).getName();
		}
		return odgovor;
	}

	public void accept(FilesystemVisitor visitor) {

		visitor.visitFolder(this);

	}

}
