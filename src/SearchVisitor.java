import java.util.ArrayList;

public class SearchVisitor extends FilesystemVisitor {

	private String name;

	public ArrayList<FSObject> foundObjects;

	public SearchVisitor(String name) {
		this.name = name;
		foundObjects = new ArrayList<FSObject>();
	}

	public void visitFile(File file) {

		if (file.getName().equals(name)) {
			foundObjects.add(file);
		}

	}

	public void visitFolder(Folder folder) {

		if (folder.getName().equals(name)) {
			foundObjects.add(folder);
		}
		for (int i = 0; i < folder.contains.size(); i++) {
			folder.contains.get(i).accept(this);
		}

	}

}
