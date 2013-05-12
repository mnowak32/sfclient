package pl.codebrewery.sfgame.model;

import java.util.Arrays;

public class GameData {

	public static final String VERSION = "v1.70";
	public static final int CONTENT_MAX = 1700;
	
	public static final GameData I = new GameData();
	
	private String login, password;
	private String sessionId;
	private long gameTime, serverTime;
	private long serverDeltaT;
	
	private int playerId, guildId;
	private int playerClass, gender;
	
	private String guildName;
	
	private boolean[] mirrorParts = new boolean[13];
	private boolean hasMirror, canRob;
	private int towerLevel, mount;
	private int level, gold, shroom;
	private int exp, expNext;
	
	private Attr str, dex, intel, endur, luck;
	
	private int action, actionCountdown;
	private boolean newChat;
	
	private int questTime;
	private Quest[] quests = new Quest[3];
	
	public static class Attr {
		private int base, bonus, bought;
		
		public Attr(int base, int bonus, int bought) {
			this.base = base;
			this.bonus = bonus;
			this.bought = bought;
		}

		public int getBase() {
			return base;
		}

		public void setBase(int base) {
			this.base = base;
		}

		public int getBonus() {
			return bonus;
		}

		public void setBonus(int bonus) {
			this.bonus = bonus;
		}

		public int getBought() {
			return bought;
		}

		public void setBought(int bought) {
			this.bought = bought;
		}
		
		public int getTotal() {
			return base + bonus;
		}

		@Override
		public String toString() {
			return String.format("Attr [base=%s, bonus=%s, bought=%s]", base, bonus, bought);
		}
	}
	
	private GameData() {
		
	}

	public void restoreFromSave(String sg) {
		String[] saveGame = ("0/" + sg).split("/");

		I.serverTime = Long.parseLong(saveGame[Const.SG_SERVER_TIME]) * 1000; //lokalnie w milisekundach
		I.gameTime = System.currentTimeMillis();
		I.serverDeltaT = I.serverTime - I.gameTime;
		
		I.playerId = Integer.parseInt(saveGame[Const.SG_PLAYER_ID]);
		I.guildId = Integer.parseInt(saveGame[Const.SG_GUILD_INDEX]);
		I.playerClass = Integer.parseInt(saveGame[Const.SG_CLASS]);
		int gender = Integer.parseInt(saveGame[Const.SG_GENDER]);
		String flags = toBinStr(gender, 32);
		
		for (int i = 0; i < 13; i++) {
			I.mirrorParts[i] = (flags.charAt(i + 1) == '1');
		}
		
		I.hasMirror = (flags.charAt(23) == '1');
		I.canRob = (flags.charAt(22) == '1');
		I.gender = (flags.charAt(31) == '1') ? 1 : 2;
		
		int mount = Integer.parseInt(saveGame[Const.SG_MOUNT]);
		I.towerLevel = mount >> 16;
		I.mount = mount & 0xffff;
		
		I.level = Integer.parseInt(saveGame[Const.SG_LEVEL]);
		I.gold = Integer.parseInt(saveGame[Const.SG_GOLD]);
		I.shroom = Integer.parseInt(saveGame[Const.SG_MUSH]);
		I.exp = Integer.parseInt(saveGame[Const.SG_EXP]);
		I.expNext = Integer.parseInt(saveGame[Const.SG_EXP_FOR_NEXTLEVEL]);
		
		int base = Integer.parseInt(saveGame[Const.SG_ATTR_STAERKE]);
		int bonus = Integer.parseInt(saveGame[Const.SG_ATTR_STAERKE_BONUS]);
		int bought = Integer.parseInt(saveGame[Const.SG_ATTR_STAERKE_GEKAUFT]);
		I.str = new Attr(base, bonus, bought);
		
		base = Integer.parseInt(saveGame[Const.SG_ATTR_BEWEGLICHKEIT]);
		bonus = Integer.parseInt(saveGame[Const.SG_ATTR_BEWEGLICHKEIT_BONUS]);
		bought = Integer.parseInt(saveGame[Const.SG_ATTR_BEWEGLICHKEIT_GEKAUFT]);
		I.dex = new Attr(base, bonus, bought);
		
		base = Integer.parseInt(saveGame[Const.SG_ATTR_INTELLIGENZ]);
		bonus = Integer.parseInt(saveGame[Const.SG_ATTR_INTELLIGENZ_BONUS]);
		bought = Integer.parseInt(saveGame[Const.SG_ATTR_INTELLIGENZ_GEKAUFT]);
		I.endur = new Attr(base, bonus, bought);
		
		base = Integer.parseInt(saveGame[Const.SG_ATTR_AUSDAUER]);
		bonus = Integer.parseInt(saveGame[Const.SG_ATTR_AUSDAUER_BONUS]);
		bought = Integer.parseInt(saveGame[Const.SG_ATTR_AUSDAUER_GEKAUFT]);
		I.intel = new Attr(base, bonus, bought);
		
		base = Integer.parseInt(saveGame[Const.SG_ATTR_WILLENSKRAFT]);
		bonus = Integer.parseInt(saveGame[Const.SG_ATTR_WILLENSKRAFT_BONUS]);
		bought = Integer.parseInt(saveGame[Const.SG_ATTR_WILLENSKRAFT_GEKAUFT]);
		I.luck = new Attr(base, bonus, bought);
		
		I.action = Integer.parseInt(saveGame[Const.SG_ACTION_STATUS]);
		I.actionCountdown = Integer.parseInt(saveGame[Const.SG_ACTION_ENDTIME]);
		
		I.questTime = Integer.parseInt(saveGame[Const.SG_TIMEBAR]);
		
		for (int i = 0; i < 3; i++) { //quests
			I.quests[i] = new Quest(
				Integer.parseInt(saveGame[Const.SG_QUEST_OFFER_LEVEL1 + i]),
				Integer.parseInt(saveGame[Const.SG_QUEST_OFFER_TYPE1 + i]),
				Integer.parseInt(saveGame[Const.SG_QUEST_OFFER_ENEMY1 + i]),
				Integer.parseInt(saveGame[Const.SG_QUEST_OFFER_LOCATION1 + i]),
				Integer.parseInt(saveGame[Const.SG_QUEST_OFFER_DURATION1 + i]),
				Integer.parseInt(saveGame[Const.SG_QUEST_OFFER_REWARD_ITM1 + i]),
				Integer.parseInt(saveGame[Const.SG_QUEST_OFFER_EXP1 + i]),
				Integer.parseInt(saveGame[Const.SG_QUEST_OFFER_GOLD1 + i]));
		}
	}

	public void updateGuild(String[] parts) {
		if (parts.length != 2) {
			return;
		}
		
		I.guildName = parts[0];
	}
	
	private static String toBinStr(int v, int bits) {
		String bin = Integer.toBinaryString(v);
		while (bin.length() < bits) {
			bin = "0" + bin;
		}
		return bin;
	}

	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public int getGuildId() {
		return guildId;
	}

	public void setGuildId(int guildId) {
		this.guildId = guildId;
	}

	public int getPlayerClass() {
		return playerClass;
	}

	public void setPlayerClass(int playerClass) {
		this.playerClass = playerClass;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return String
			.format(
				"GameData [login=%s, password=%s, sessionId=%s, gameTime=%s, playerId=%s, guildId=%s, playerClass=%s, gender=%s, guildName=%s, mirrorParts=%s, hasMirror=%s, canRob=%s, towerLevel=%s, mount=%s, level=%s, gold=%s, shroom=%s, exp=%s, expNext=%s, str=%s, dex=%s, intel=%s, endur=%s, luck=%s, action=%s, actionCountdown=%s]",
				login, password, sessionId, gameTime, playerId, guildId, playerClass, gender, guildName,
				Arrays.toString(mirrorParts), hasMirror, canRob, towerLevel, mount, level, gold, shroom, exp, expNext, str, dex,
				intel, endur, luck, action, actionCountdown);
	}

	public boolean[] getMirrorParts() {
		return mirrorParts;
	}

	public void setMirrorParts(boolean[] mirrorParts) {
		this.mirrorParts = mirrorParts;
	}

	public boolean isHasMirror() {
		return hasMirror;
	}

	public void setHasMirror(boolean hasMirror) {
		this.hasMirror = hasMirror;
	}

	public boolean isCanRob() {
		return canRob;
	}

	public void setCanRob(boolean canRob) {
		this.canRob = canRob;
	}

	public int getMount() {
		return mount;
	}

	public void setMount(int mount) {
		this.mount = mount;
	}

	public int getTowerLevel() {
		return towerLevel;
	}

	public void setTowerLevel(int towerLevel) {
		this.towerLevel = towerLevel;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getShroom() {
		return shroom;
	}

	public void setShroom(int shroom) {
		this.shroom = shroom;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getExpNext() {
		return expNext;
	}

	public void setExpNext(int expNext) {
		this.expNext = expNext;
	}

	public String getGuildName() {
		return guildName;
	}

	public void setGuildName(String guildName) {
		this.guildName = guildName;
	}

	public Attr getStr() {
		return str;
	}

	public void setStr(Attr str) {
		this.str = str;
	}

	public Attr getDex() {
		return dex;
	}

	public void setDex(Attr dex) {
		this.dex = dex;
	}

	public Attr getIntel() {
		return intel;
	}

	public void setIntel(Attr intel) {
		this.intel = intel;
	}

	public Attr getEndur() {
		return endur;
	}

	public void setEndur(Attr endur) {
		this.endur = endur;
	}

	public Attr getLuck() {
		return luck;
	}

	public void setLuck(Attr luck) {
		this.luck = luck;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public int getActionCountdown() {
		return actionCountdown;
	}

	public void setActionCountdown(int actionCountdown) {
		this.actionCountdown = actionCountdown;
	}

	public long getGameTime() {
		return gameTime;
	}

	public void setGameTime(long gameTime) {
		this.gameTime = gameTime;
	}

	public long getServerTime() {
		return serverTime;
	}

	public void setServerTime(long serverTime) {
		this.serverTime = serverTime;
	}

	public long getServerDeltaT() {
		return serverDeltaT;
	}

	public void setServerDeltaT(long serverDeltaT) {
		this.serverDeltaT = serverDeltaT;
	}

	public boolean isNewChat() {
		return newChat;
	}

	public void setNewChat(boolean newChat) {
		this.newChat = newChat;
	}

	public int getQuestTime() {
		return questTime;
	}

	public void setQuestTime(int questTime) {
		this.questTime = questTime;
	}

	public Quest[] getQuests() {
		return quests;
	}

	public void setQuests(Quest[] quests) {
		this.quests = quests;
	}

	
	
	
	
}
