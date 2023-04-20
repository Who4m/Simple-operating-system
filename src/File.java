import java.util.ArrayList;

public class File extends FSObject {
	public ArrayList<Byte> content;

	public File(String name) {
		super(name);
		super.getAccessDescriptor().setObjekat(this);
		content = new ArrayList<Byte>();
	}

	public File() {
		super();
		content = new ArrayList<Byte>();
	}

	public void write(ArrayList<Byte> content) {
		for (int i = 0; i < content.size(); i++) {
			this.content.add(content.get(i));
		}
	}

	public long size() {
		long x = 0;
		for (int i = 0; i < content.size(); i++) {
			x += 1;
		}
		return x;
	}

	public ArrayList<Byte> read() {
		return content;
	}

	public String toString() {
		String odgovor = "";
		for (int i = 0; i < content.size(); i++) {
			odgovor += content.get(i) + "\n";
		}
		return odgovor;
	}

	public File copy() {
		File copy = new File();
		AccessDesctriptor copyAccess = new AccessDesctriptor();
		for (int i = 0; i < super.getAccessDescriptor().getAllowedOperations().size(); i++) {
			copyAccess.add(super.getAccessDescriptor().getAllowedOperations().get(i));
		}
		copyAccess.setObjekat(copy);
		copy.setAccessDesctriptor(copyAccess);
		for (int i = 0; i < content.size(); i++) {
			copy.content.add(content.get(i));
		}
		copy.setName(super.getName());
		return copy;

	}

	public void delete() {
		content.clear();
		super.setName(null);
		super.setPerent(null);
		super.setAccessDesctriptor(null);

	}

	public void accept(FilesystemVisitor visitor) {
		visitor.visitFile(this);
	}

}
