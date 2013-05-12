package pl.codebrewery.sfgame;

import java.io.Console;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.LinkedList;

import pl.codebrewery.sfgame.engine.Net;
import pl.codebrewery.sfgame.engine.Request;
import pl.codebrewery.sfgame.engine.Response;
import pl.codebrewery.sfgame.model.ChatLine;
import pl.codebrewery.sfgame.model.Const;
import pl.codebrewery.sfgame.model.GameData;

public class Cli {
	
	private static final boolean COLOR = true;
//	private static final boolean COLOR = false;
	
	private GameData gd = GameData.I;
	private Console con;
	private Net net;

	public static void main(String[] args) {
		if (args.length < 2) {
			System.err.println("za mało params. daj username i pass");
			return;
		}
		
		Cli cli = new Cli();
		cli.start(args[0], args[1]);
	}
	
	public void start(String uname, String pass) {
		con = System.console();
		if (con == null) {
			System.err.println("ni mam konsoli. fuuuuuuu!");
			return;
		}
		
		net = new Net();
		Response resp = net.call(new Request(Const.ACT_LOGIN, null, uname, Md5.hash(pass), GameData.VERSION));
		if (resp.isError()) {
			con.printf("Fehler! " + resp.getCode());
			return;
		}

		String[] parts = resp.getParts();
		if (parts.length != 6) {
			con.printf("Fehler! incorrect response");
			return;
		}
		
		gd.setSessionId(parts[2]);
		gd.restoreFromSave(parts[0]);
		gd.setLogin(uname);
		gd.setPassword(pass);
		
		if (gd.getGuildId() > 0) {
			resp = net.call(new Request(Const.ACT_REQUEST_GUILD, gd.getSessionId(), Integer.toString(gd.getGuildId())));
			if (resp.isError()) {
				con.printf("Fehler! " + resp.getCode());
			} else {
				gd.updateGuild(resp.getParts());
			}
		}
		
		while (true) {
			print(getPrompt());
			String line = con.readLine();
			
			if (line == null || line.startsWith("qui")) {
				print("\nbajo!\n");
				return;
			} else if (line.startsWith("sta")) {
				printStats();
			} else if (line.startsWith("que")) {
				enterTaverne();
			} else if (line.startsWith("cha")) {
				showChat();
			} else if (line.startsWith("abort ")) {
				doAbort(line.substring(6));
			} else {
				print("Unrecognized command!");
			}
		}
	}
	
	/**
	 * abort work or quest
	 * @param substring
	 */
	private void doAbort(String substring) {
		// TODO Auto-generated method stub
		
	}

	private void showChat() {
		Response resp = net.call(new Request(Const.ACT_GET_CHAT_HISTORY, gd.getSessionId(), Integer.toString(gd.getGuildId())));
		if (!resp.isError()) {
			gd.setNewChat(false);
			String[] lines = resp.getParts()[0].split("/");
			LinkedList<ChatLine> chat = new LinkedList<ChatLine>();
			for (String line : lines) {
				chat.addFirst(new ChatLine(line));
			}
			for (ChatLine cl : chat) {
				print(cl.getLine() + "\n");
			}
		}
	}

	private void enterTaverne() {
		Response resp = net.call(new Request(Const.ACT_SCREEN_TAVERNE, gd.getSessionId()));
//		con.printf(resp.toString());
		
		if (!resp.isError()) {
			switch (resp.getCode()) {
			case Const.ACT_SCREEN_ARBEITEN:
			case Const.ACT_SCREEN_STALL:
				int gold = Integer.parseInt(resp.getParts()[0]);
				print(String.format("You are at WORK (will earn G #_Y%d#Z S #_K%d#Z per hour), come back later! You can 'abort work'.", gold / 100, gold % 100));
				break;
			case Const.ACT_SCREEN_TAVERNE:
				gd.restoreFromSave(resp.getParts()[0]);
				con.printf(Arrays.asList(gd.getQuests()).toString());
				break;
			default:
				print("#_RUnknown response#Z");
				break;
			}
		}
	}

	private String getPrompt() {
		return String.format("\n%s[#_G%s#Z] [LVL #_B%d#Z EXP #_W%d#Z/#_W%d#Z] (G #_Y%d#Z S #_K%d#Z M #Y%d#Z) " +
			"[%s] {%s} > ",
			gd.getLogin(), gd.getGuildName(), gd.getLevel(), gd.getExp(), gd.getExpNext(), gd.getGold() / 100, gd.getGold() % 100, gd.getShroom(),
			getActionStatus(), getFlags());
	}
	
	private String getFlags() {
		StringBuilder sb = new StringBuilder();
		if (gd.isNewChat()) {
			sb.append('C');
		}
		//TODO więcej flag
		return sb.toString();
	}

	private void printStats() {
		print(String.format("\n" +
			"  #CSTR#Z #_W%8d#Z    DMG %d - %d\n" +
			"  #YDEX#Z #_W%8d#Z    DOD %d\n" +
			"  #GINT#Z #_W%8d#Z    RES %d\n" +
			"  #MEND#Z #_W%8d#Z    LIF %d\n" +
			"  #BLUC#Z #_W%8d#Z    CRI %.2f\n",
			gd.getStr().getTotal(), 0, 0, gd.getDex().getTotal(), 0, gd.getIntel().getTotal(), 0, gd.getEndur().getTotal(), 0, gd.getLuck().getTotal(), 0.0));
	}
	
	private String getActionStatus() {
		if (gd.getAction() == 0) {
			return "---";
		}
		int deltaT = gd.getActionCountdown() - (int)((System.currentTimeMillis() + gd.getServerDeltaT()) / 1000);
		if (deltaT > 0) {
			return String.format("@%s, #_W%02d:%02d:%02d#Z left", gd.getAction() == 1 ? "#_RWORK#Z" : "#_GQUEST#Z", deltaT / 3600, deltaT % 3600 / 60, deltaT % 60);
		}
		return String.format("@%s, #_Wfinished#Z", gd.getAction() == 1 ? "#_RWORK#Z" : "#_GQUEST#Z");
	}
	
	/**
	 * preprocess color codes (maek them ANSI!)
	 * @param out
	 */
	private void print(String out) {
		for (Ansi a : Ansi.values()) {
			out = out.replaceAll("#" + a.toString(), COLOR ? a.getEsc() : "");
		}
		out += Ansi.Z.getEsc();
		con.printf(out);
	}

	private static class Md5 {
		static String hash(String in) {
			try {
				MessageDigest md = MessageDigest.getInstance("md5");
				byte[] dig = md.digest(in.getBytes());
				StringBuilder sb = new StringBuilder();
				for (byte b : dig) {
					sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
				}
				return sb.toString();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				return "";
			}
		}
	}
}
