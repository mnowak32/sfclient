package pl.codebrewery.sfgame.model;

public class Quest {

    private int level, type, enemy, location, duration, reward, exp, gold;

    
	public Quest(int level, int type, int enemy, int location, int duration, int reward, int exp, int gold) {
		this.level = level;
		this.type = type;
		this.enemy = enemy;
		this.location = location;
		this.duration = duration;
		this.reward = reward;
		this.exp = exp;
		this.gold = gold;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getEnemy() {
		return enemy;
	}

	public void setEnemy(int enemy) {
		this.enemy = enemy;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getReward() {
		return reward;
	}

	public void setReward(int reward) {
		this.reward = reward;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	@Override
	public String toString() {
		return String.format("Quest [level=%s, type=%s, enemy=%s, location=%s, duration=%s, reward=%s, exp=%s, gold=%s]", level,
			type, enemy, location, duration, reward, exp, gold);
	}
	
}
