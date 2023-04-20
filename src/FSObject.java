
public abstract class FSObject {
	private String name;
	private AccessDesctriptor access;
	public Folder parent;
	public String path;

	public FSObject(String name) {
		access = new AccessDesctriptor();
		this.name = name;
		parent = null;

	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public FSObject() {
		name = null;
		access = null;
		parent = null;
	}

	public void setAccessDesctriptor(AccessDesctriptor access) {
		this.access = access;
	}

	public void setPerent(Folder perent) {
		this.parent = perent;
	}

	public Folder getParent() {
		return parent;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public AccessDesctriptor getAccessDescriptor() {
		return access;
	}

	public abstract long size();

	public abstract FSObject copy();

	public abstract void delete();

	public abstract void accept(FilesystemVisitor visitor);

}
