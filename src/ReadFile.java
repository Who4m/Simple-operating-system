import java.util.ArrayList;

public class ReadFile extends FSOperation {

	private File obj;
	public ArrayList<Byte> dovucen;

	public ReadFile(String opName, File obj) {
		super(opName);
		this.obj = obj;
	}

	public void execute() {
		dovucen = obj.read();
	}
}
