import java.util.ArrayList;

public class Filesystem {
	static long size;
	private Folder root;
	static Filesystem obj;

	public Folder getRoot() {
		return root;
	}

	private Filesystem() {
		root = new Folder("Root");
		root.setPath("Root");
	}

	public static Filesystem instance(long velicina) {
		if (obj == null) {
			obj = new Filesystem();
			size = velicina;
		}
		return obj;
	}

	public ArrayList<Byte> readFile(File file) {
		if ((exceptionVisitor(file)) && (exceptionAccess(file, "ReadFile"))) {
			ReadFile citam = new ReadFile("ReadFile", file);
			citam.execute();
			return citam.dovucen;
		}
		return null;
	}

	public void writeFile(ArrayList<Byte> content, File parent) {
		if ((exceptionVisitor(parent)) && (exceptionAccess(parent, "WriteFile")) && (exceptionMemory(content))) {
			WriteFile pisem = new WriteFile("WriteFile", parent, content);
			pisem.execute();
		}
	}

	public File createFile(String name, Folder parent) {
		if ((exceptionVisitor(parent)) && (exceptionAccess(parent, "CreateFile"))
				&& (exceptionNameUsed(parent, name))) {
			CreateFile pravim = new CreateFile("CreateFile", name, parent);
			pravim.execute();
			return pravim.novi;
		}
		return null;
	}

	public Folder createFolder(String name, Folder parent) {
		if ((exceptionVisitor(parent)) && (exceptionAccess(parent, "CreateFolder"))
				&& (exceptionNameUsed(parent, name))) {
			CreateFolder pravim = new CreateFolder("CreateFolder", name, parent);
			pravim.execute();
			return pravim.novi;
		}
		return null;
	}

	public boolean delete(FSObject obj) {
		if ((exceptionVisitor(obj)) && (exceptionAccess(obj, "DeleteObject"))) {
			DeleteObject brisem = new DeleteObject("DeleteObject", obj);
			brisem.execute();
			return true;
		}
		return false;
	}

	@SuppressWarnings("static-access")
	public long freeSpace() {
		long freeSize;
		freeSize = this.size - root.size();
		return freeSize;
	}

	public boolean exceptionMemory(ArrayList<Byte> content) {
		MemoryOwerflowException aa = new MemoryOwerflowException();
		long newSize;
		newSize = root.size() + content.size();
		if (newSize > size) {
			try {
				throw aa;
			} catch (MemoryOwerflowException a) {
				System.out.println(a.what());
				a.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public boolean exceptionMemory(FSObject obj) {
		MemoryOwerflowException aa = new MemoryOwerflowException();

		long newSize;
		newSize = root.size() + obj.size();
		if (newSize > size) {
			try {
				throw aa;
			} catch (MemoryOwerflowException a) {
				System.out.println(a.what());
				a.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public boolean exceptionNameUsed(Folder obj, String name) {
		ExceptionNameCollision exceptionName = new ExceptionNameCollision(obj, name);
		for (int i = 0; i < obj.contains.size(); i++) {
			if (obj.contains.get(i).getName().equals(name)) {
				try {
					throw exceptionName;
				} catch (ExceptionNameCollision a) {
					System.out.println(a.what());
					a.printStackTrace();
				}

				return false;
			}
		}
		return true;
	}

	public boolean exceptionVisitor(FSObject obj) {
		ObjectNotFoundException aa = new ObjectNotFoundException(obj);
		try {
			if (obj.getName() == null) {
				throw aa;

			}
			int mozeVisitor = 0;
			SearchVisitor visitor = new SearchVisitor(obj.getName());
			root.accept(visitor);
			if (visitor.foundObjects.isEmpty()) {
				throw aa;

			}
			for (int i = 0; i < visitor.foundObjects.size(); i++) {
				if (visitor.foundObjects.get(i) == obj) {
					mozeVisitor++;
				}
			}
			if (mozeVisitor == 0) {
				throw aa;

			}
		} catch (ObjectNotFoundException a) {
			System.out.println(a.what());
			a.printStackTrace();
			return false;
		}

		return true;
	}

	public boolean exceptionAccess(FSObject obj, String opName) {
		AccessException access = new AccessException(obj, opName);
		int moze = 0;
		for (int i = 0; i < obj.getAccessDescriptor().getAllowedOperations().size(); i++) {
			if (obj.getAccessDescriptor().getAllowedOperations().get(i).equals(opName)) {
				moze++;
			}
		}
		if (moze == 0) {
			try {
				throw access;
			} catch (AccessException a) {
				System.out.println(a.what());
				a.printStackTrace();
			}

			return false;
		}
		return true;
	}

	public void grantAccess(FSObject obj, String opName) {
		if (exceptionVisitor(obj)) {
			obj.getAccessDescriptor().add(opName);
		}
	}

	public void revokeAccess(FSObject obj, String opName) {
		if (exceptionVisitor(obj)) {
			obj.getAccessDescriptor().remove(opName);
		}
	}

	public void copyPaste(FSObject objToCopy, Folder destFolder, String newName) {
		if ((exceptionAccess(objToCopy, "CopyPaste")) && (exceptionVisitor(objToCopy))) {
			if (exceptionAccess(destFolder, "CopyPaste") && (exceptionVisitor(destFolder))
					&& (exceptionNameUsed(destFolder, newName)) && (exceptionMemory(objToCopy))) {
				CopyPaste radim = new CopyPaste("CopyPaste", objToCopy, destFolder, newName);
				radim.execute();
				return;
			}
		}
		return;
	}

	public void move(FSObject objToMove, Folder destFolder) {
		if ((exceptionVisitor(objToMove)) && (exceptionAccess(objToMove, "Move"))) {
			if ((exceptionVisitor(destFolder) && (exceptionAccess(destFolder, "Move")))
					&& (exceptionNameUsed(destFolder, objToMove.getName()))) {
				Move move = new Move(objToMove, destFolder, "Move");
				move.execute();
				return;
			}
		}
	}

	public ArrayList<FSObject> search(String objName) {
		Search pretraga = new Search("Search", objName, root);
		pretraga.execute();
		if (pretraga.list.isEmpty()) {
			System.out.println("Object not found");
			return null;
		}
		return pretraga.list;
	}

	public ArrayList<FSObject> listFolder(Folder folder) {
		if ((exceptionVisitor(folder)) && (exceptionAccess(folder, "ListDIrectory"))) {
			ListDirectory list = new ListDirectory(folder, "ListDirectory");
			list.execute();
			return list.listDirectory;
		}
		return null;
	}

	public Folder openFolder(String path) {
		PathVisitor visitor = new PathVisitor(path);
		root.accept(visitor);
		if (visitor.obj == null) {
			System.out.println("ObjectNotFoundException");
			return null;
		}
		if (exceptionAccess(visitor.obj, "OpenFolder")) {
			return visitor.obj;
		}
		return null;
	}

}
