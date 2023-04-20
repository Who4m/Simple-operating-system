import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		ArrayList<Byte> listaBajtova = new ArrayList<Byte>();
		ArrayList<Byte> listaBajtova2 = new ArrayList<Byte>();
		listaBajtova2.add((byte) -34);
		listaBajtova2.add((byte) 16);
		listaBajtova2.add((byte) 43);
		listaBajtova2.add((byte) 0);
		listaBajtova.add((byte) 11);
		listaBajtova.add((byte) 44);
		listaBajtova.add((byte) 123);
		listaBajtova.add((byte) 56);
		listaBajtova.add((byte) 12);
		listaBajtova.add((byte) -54);
		File prvi;
		File drugi;
		File treci;
		Folder folder1;
		Folder folder2;
		Folder folder3;
		Filesystem.instance(110000).getRoot().getAccessDescriptor().add("CreateFile");
		Filesystem.instance(110000).getRoot().getAccessDescriptor().add("CreateFolder");
		Filesystem.instance(110000).getRoot().getAccessDescriptor().add("WriteFile");
		Filesystem.instance(110000).getRoot().getAccessDescriptor().add("ReadFile");
		prvi = Filesystem.instance(110000).createFile("File1", Filesystem.instance(1).getRoot());
		prvi.getAccessDescriptor().add("WriteFile");
		prvi.getAccessDescriptor().add("DeleteObject");
		Filesystem.instance(1).writeFile(listaBajtova2, prvi);
		folder1 = Filesystem.instance(1).createFolder("Folder1", Filesystem.instance(1).getRoot());
		folder1.getAccessDescriptor().add("CreateFile");
		folder1.getAccessDescriptor().add("CreateFolder");
		folder2 = Filesystem.instance(1).createFolder("Folder2", Filesystem.instance(1).getRoot());
		folder3 = Filesystem.instance(1).createFolder("Folder3", folder1);
		folder3.getAccessDescriptor().add("CreateFile");
		folder3.getAccessDescriptor().add("CreateFolder");
		drugi = Filesystem.instance(1).createFile("File2", folder1);
		treci = Filesystem.instance(1).createFile("File3", folder3);
		treci.getAccessDescriptor().add("WriteFile");
		treci.getAccessDescriptor().add("ReadFile");
	}
}
