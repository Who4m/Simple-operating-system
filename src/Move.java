
public class Move extends FSOperation {
	FSObject move;
	Folder destination;

	public Move(FSObject move, Folder destination, String opName) {
		super(opName);
		this.move = move;
		this.destination = destination;
	}

	public void execute() {
		move.getParent().remove(move);
		destination.add(move);
	}
}
