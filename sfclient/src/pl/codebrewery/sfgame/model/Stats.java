package pl.codebrewery.sfgame.model;

import pl.codebrewery.sfgame.model.Game.Attr;

public class Stats {
	public long hp;
	public Attr str;
	public Attr dex;
	public Attr intel;
	public Attr endur;
	public Attr luck;

	public Stats() {
	}
	
	public Stats(long hp, int str, int dex, int intel, int endur, int luck) {
		this.hp = hp;
		this.str = new Attr(str, 0, 0);
		this.dex = new Attr(dex, 0, 0);
		this.intel = new Attr(intel, 0, 0);
		this.endur = new Attr(endur, 0, 0);
		this.luck = new Attr(luck, 0, 0);
	}
	
}