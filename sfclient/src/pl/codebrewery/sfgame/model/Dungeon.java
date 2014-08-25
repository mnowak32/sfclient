package pl.codebrewery.sfgame.model;

public class Dungeon {

	public int level, enemy;
	public boolean finished;
	
	public Dungeon(int level, int enemy, boolean finished) {
		this.level = level;
		this.enemy = enemy;
		this.finished = finished;
	}
	
	@Override
	public String toString() {
		return String.format("\t%2d\t%2d\t%1s", level, enemy, finished ? "X" : "");
	}
}
