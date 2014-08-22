package pl.codebrewery.sfgame.model;

import java.util.LinkedList;
import java.util.List;

import pl.codebrewery.sfgame.interfaces.Ansi;

public class Fight {

	public enum HitType {
		NORMAL(Ansi._W, "hit", "hits"),
		BLOCK(Ansi.Y, "block", "blocks"),
		NORMAL2(Ansi._W, "hit", "hits"),
		CRITICAL(Ansi._R, "*devastate*", "*devastates*"),
		CATAPULT(Ansi._G, "catapult shroom on", "catapults shroom on");
		
		public final Ansi color;
		public final String you, opp;
		
		private HitType(Ansi c, String you, String opp) {
			color = c;
			this.you = you;
			this.opp = opp;
		}
		
		static HitType valueOf(int i) {
			if (i < 1 || i >= values().length) {
				return NORMAL;
			}
			return values()[i];
		}

		public String action(Fighter ft) {
			if (ft == Fighter.OPPONENT) {
				return opp;
			}
			return you;
		}
	}
	
	public enum Fighter {
		YOU(Ansi._B), OPPONENT(Ansi._R);
		
		private Ansi color;
		
		private Fighter(Ansi c) {
			color = c;
		}
		
		public Fighter next() {
			if (this == YOU) {
				return OPPONENT;
			}
			return YOU;
		}
		
		public String getName() {
			return String.format("#%s%s#%s", color, this, Ansi.Z);
		}
		
		//helper lambda
		public static Fighter winner(Fighter f1, Fighter f2) {
			return f2;
		}
	}
	
	public class Round {
		public int life, damage;
		public HitType hit;
		public Fighter who;
		
		Round(int life, int damage, HitType hit, Fighter who) {
			this.life = life;
			this.damage = damage;
			this.hit = hit;
			this.who = who;
		}
	}

	public List<Round> rounds = new LinkedList<>();
	public Stats charStats, oppStats;
	
	public void setRounds(int[] fightData) {
		if (fightData.length < 6) { //dmuchamy na zimne
			return;
		}
		Fighter who = Fighter.YOU; //tu jest coś nie tak...
		
		for (int i = 0; i < fightData.length; i += 3) {
			if (i == 0 && fightData[i + 1] == 0 && fightData[i + 2] == 0) { //zaczyna drugi zawodnik...
				// pomijamy taki wpis
			} else {
				rounds.add(new Round(fightData[i], fightData[i + 1], HitType.valueOf(fightData[i + 2]), who));
			}
			who = who.next();
		}
	}
	
	public void setStats(int[] fighterData) {
		if (fighterData.length < 12) {
			return;
		}
		charStats = new Stats(fighterData[0], fighterData[1], fighterData[2], fighterData[3], fighterData[4], fighterData[5]);
		oppStats = new Stats(fighterData[6], fighterData[7], fighterData[8], fighterData[9], fighterData[10], fighterData[11]);
	}
}
