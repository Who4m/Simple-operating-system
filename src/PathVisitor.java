
public class PathVisitor extends FilesystemVisitor {

	String path;
	Folder obj;

	public PathVisitor(String path) {
		this.path = path;
		obj = null;
	}

	public void visitFile(File file) {

	}

	public void visitFolder(Folder folder) {
		if (folder.getPath().equals(path)) {
			this.obj = folder;
		}
		for (int i = 0; i < folder.contains.size(); i++) {
			folder.contains.get(i).accept(this);
		}
	}
}
