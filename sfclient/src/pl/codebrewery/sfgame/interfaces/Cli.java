package pl.codebrewery.sfgame.interfaces;

import java.io.IOException;

import jline.ConsoleReader;
import pl.codebrewery.sfgame.engine.Net;
import pl.codebrewery.sfgame.engine.Request;
import pl.codebrewery.sfgame.engine.Response;
import pl.codebrewery.sfgame.engine.ResponseHandler;
import pl.codebrewery.sfgame.model.Const;
import pl.codebrewery.sfgame.model.Game;

public class Cli implements Commander {
	
	private static final boolean COLOR = true;
//	private static final boolean COLOR = false;
	
	private Game gd = Game.I;
	private Net net = gd.net;
	private ResponseHandler rh = gd.rh;
	private CommandParser cp = new CommandParser();
	private boolean color = COLOR;
	
	private ConsoleReader con;

	private boolean keepGoing;
	private long sysTime;

	public static void main(String[] args) throws IOException {
		if (args.length < 3) {
			System.err.println("za mało params. daj serwer, username i pass");
			return;
		}
		
		Cli cli = new Cli();
		cli.start(args[0], args[1], args[2]);
	}
	
	public void start(final String server, final String uname, final String pass) throws IOException {
		con = new ConsoleReader();
		if (con == null) {
			System.err.println("No console could be allocated. Giving up.");
			return;
		}
		
		net.setServer(server);
		
		keepGoing = true;
		gd.setLogin(uname);
		gd.setPassword(pass);

		relogin();
		
		while (keepGoing) {
			sysTime = gd.getSysTime();
			print(getPrompt());
			String line = con.readLine();
			keepGoing = cp.parseCommand(line, this); 
		}
	}
	
	@Override
	public void relogin() {
//		Response resp = net.call(new Request(Const.ACT_LOGOUT, gd.getSessionId()));
//		if (rh.handleResponse(resp, this) == false || resp.isError()) { //zwrot false oznacza: błąd kytyczny, spierdalamy!
//			print("#_RCritical error, aborting!");
//			keepGoing = false;
//			return;
//		}
		String hash = gd.getPassword().matches("^[0-9a-f]{32}$") ? gd.getPassword() : Md5.hash(gd.getPassword());
		
		Response resp = net.call(new Request(Const.ACT_LOGIN, null, gd.getLogin(), hash, Game.VERSION));
		if (rh.handleResponse(resp, this) == false || resp.isError()) { //zwrot false oznacza: błąd kytyczny, spierdalamy!
			print("#_RCritical error, aborting!");
			keepGoing = false;
			return;
		}
		
        if (gd.getGuildId() > 0) {
        	resp = gd.net.call(Const.ACT_REQUEST_GUILD, gd.getSessionId(), rh.toString(gd.getGuildId()));
        	rh.handleResponse(resp, this);
        }

	}
	
	/**
	 * abort work or quest
	 * @param substring
	 */
	void doAbort(String substring) {
		// TODO Auto-generated method stub
		
	}

	private String getFlags() {
		StringBuilder sb = new StringBuilder();
		if (gd.isNewChat()) {
			sb.append('C');
		}
		//TODO więcej flag
		return sb.toString();
	}

	
	private String getPrompt() {
		return String.format("\n%s[#_G%s#Z] [LVL #_B%d#Z EXP #_W%d%%#Z] (G #_Y%d#Z,#W%d#Z M #Y%d#Z) " +
			"%s%s{%s} > ",
			gd.getLogin(), gd.getGuildName(), gd.level, (gd.exp * 100 / gd.expNext), gd.gold / 100, gd.gold % 100, gd.shroom,
			getActionStatus(), getDungeonStatus(), getFlags());
	}
	
	private String getDungeonStatus() {
		long deltaT = gd.dungeonWait - sysTime;
		if (deltaT > 0) {
			return String.format("(#BDUN wait#Z: #_W%s#Z) ", formatTime(deltaT));
		}
		return "";
	}

	private String getActionStatus() {
		if (gd.action == 0) {
			return "";
		}
		long deltaT = gd.actionCountdown - sysTime;
		if (deltaT > 0) {
			return String.format("[@%s, #_W%s#Z left] ", gd.action == 1 ? "#_RWORK#Z" : "#_GQUEST#Z", formatTime(deltaT));
		}
		return String.format("[@%s, #_Wfinished#Z] ", gd.action == 1 ? "#_RWORK#Z" : "#_GQUEST#Z");
	}
	
	private String formatTime(long time) {
		return String.format("%02d:%02d:%02d", time / 3600, time % 3600 / 60, time % 60);
	}
	
	/**
	 * preprocess color codes (maek them ANSI!)
	 * @param out
	 */
	@Override
	public void print(String out) {
		for (Ansi a : Ansi.values()) {
			out = out.replaceAll("#" + a.toString(), color ? a.esc : "");
		}
		if (color) {
			out += Ansi.Z.esc;
		}
		
		try {
			con.printString(out);
			con.flushConsole();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void nullResponse() {
		// TODO Auto-generated method stub
		
	}

	public void cls() {
		try {
			con.clearScreen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void switchColor() {
		color = !color;
	}
}
