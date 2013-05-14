package pl.codebrewery.sfgame.model;

import java.util.Arrays;

import pl.codebrewery.sfgame.engine.Net;
import pl.codebrewery.sfgame.engine.ResponseHandler;

public class Game {

	public static final String VERSION = "v1.70";
	public static final int CONTENT_MAX = 1700;
	
	public static final Game I = new Game();
	public Net net = new Net();
	public ResponseHandler rh = new ResponseHandler();
	
	
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
	private boolean newChat = false;
	
	private int questTime;
	private Quest[] quests = new Quest[3];
	
	private boolean beerFest;
	
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
	
	private Game() {
		
	}

	public void restoreFromSave(String sg) {
		String[] saveGame = ("0/" + sg).split("/");

		serverTime = Long.parseLong(saveGame[Const.SG_SERVER_TIME]) * 1000; //lokalnie w milisekundach
		gameTime = System.currentTimeMillis();
		serverDeltaT = serverTime - gameTime;
		
		playerId = Integer.parseInt(saveGame[Const.SG_PLAYER_ID]);
		guildId = Integer.parseInt(saveGame[Const.SG_GUILD_INDEX]);
		playerClass = Integer.parseInt(saveGame[Const.SG_CLASS]);
		int gender = Integer.parseInt(saveGame[Const.SG_GENDER]);
		String flags = toBinStr(gender, 32);
		
		for (int i = 0; i < 13; i++) {
			mirrorParts[i] = (flags.charAt(i + 1) == '1');
		}
		
		hasMirror = (flags.charAt(23) == '1');
		canRob = (flags.charAt(22) == '1');
		gender = (flags.charAt(31) == '1') ? 1 : 2;
		
		int mount = Integer.parseInt(saveGame[Const.SG_MOUNT]);
		towerLevel = mount >> 16;
		mount = mount & 0xffff;
		
		level = Integer.parseInt(saveGame[Const.SG_LEVEL]);
		gold = Integer.parseInt(saveGame[Const.SG_GOLD]);
		shroom = Integer.parseInt(saveGame[Const.SG_MUSH]);
		exp = Integer.parseInt(saveGame[Const.SG_EXP]);
		expNext = Integer.parseInt(saveGame[Const.SG_EXP_FOR_NEXTLEVEL]);
		
		int base = Integer.parseInt(saveGame[Const.SG_ATTR_STAERKE]);
		int bonus = Integer.parseInt(saveGame[Const.SG_ATTR_STAERKE_BONUS]);
		int bought = Integer.parseInt(saveGame[Const.SG_ATTR_STAERKE_GEKAUFT]);
		str = new Attr(base, bonus, bought);
		
		base = Integer.parseInt(saveGame[Const.SG_ATTR_BEWEGLICHKEIT]);
		bonus = Integer.parseInt(saveGame[Const.SG_ATTR_BEWEGLICHKEIT_BONUS]);
		bought = Integer.parseInt(saveGame[Const.SG_ATTR_BEWEGLICHKEIT_GEKAUFT]);
		dex = new Attr(base, bonus, bought);
		
		base = Integer.parseInt(saveGame[Const.SG_ATTR_INTELLIGENZ]);
		bonus = Integer.parseInt(saveGame[Const.SG_ATTR_INTELLIGENZ_BONUS]);
		bought = Integer.parseInt(saveGame[Const.SG_ATTR_INTELLIGENZ_GEKAUFT]);
		endur = new Attr(base, bonus, bought);
		
		base = Integer.parseInt(saveGame[Const.SG_ATTR_AUSDAUER]);
		bonus = Integer.parseInt(saveGame[Const.SG_ATTR_AUSDAUER_BONUS]);
		bought = Integer.parseInt(saveGame[Const.SG_ATTR_AUSDAUER_GEKAUFT]);
		intel = new Attr(base, bonus, bought);
		
		base = Integer.parseInt(saveGame[Const.SG_ATTR_WILLENSKRAFT]);
		bonus = Integer.parseInt(saveGame[Const.SG_ATTR_WILLENSKRAFT_BONUS]);
		bought = Integer.parseInt(saveGame[Const.SG_ATTR_WILLENSKRAFT_GEKAUFT]);
		luck = new Attr(base, bonus, bought);
		
		action = Integer.parseInt(saveGame[Const.SG_ACTION_STATUS]);
		actionCountdown = Integer.parseInt(saveGame[Const.SG_ACTION_ENDTIME]);
		
		questTime = Integer.parseInt(saveGame[Const.SG_TIMEBAR]);
		
		for (int i = 0; i < 3; i++) { //quests
			quests[i] = new Quest(
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
		
		guildName = parts[0];
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
				"GameData [login=%s, password=%s, sessionId=%s, gameTime=%s, serverTime=%s, serverDeltaT=%s, playerId=%s, guildId=%s, playerClass=%s, gender=%s, guildName=%s, mirrorParts=%s, hasMirror=%s, canRob=%s, towerLevel=%s, mount=%s, level=%s, gold=%s, shroom=%s, exp=%s, expNext=%s, str=%s, dex=%s, intel=%s, endur=%s, luck=%s, action=%s, actionCountdown=%s, newChat=%s, questTime=%s, quests=%s, beerFest=%s]",
				login, password, sessionId, gameTime, serverTime, serverDeltaT, playerId, guildId, playerClass, gender, guildName,
				Arrays.toString(mirrorParts), hasMirror, canRob, towerLevel, mount, level, gold, shroom, exp, expNext, str, dex,
				intel, endur, luck, action, actionCountdown, newChat, questTime, Arrays.toString(quests), beerFest);
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

	public boolean isBeerFest() {
		return beerFest;
	}

	public void setBeerFest(boolean beerFest) {
		this.beerFest = beerFest;
	}

	
	
	
	
}
