import java.util.ArrayList;

public class WriteFile extends FSOperation {

	File file;
	ArrayList<Byte> content;

	public WriteFile(String opName, File file, ArrayList<Byte> content) {
		super(opName);
		this.file = file;
		this.content = content;
	}

	public void execute() {
		file.write(content);
	}
}
