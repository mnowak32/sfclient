package pl.codebrewery.sfgame.interfaces;

import java.io.IOException;

import pl.codebrewery.sfgame.engine.Net;
import pl.codebrewery.sfgame.engine.Response;
import pl.codebrewery.sfgame.engine.ResponseHandler;
import pl.codebrewery.sfgame.model.Const;
import pl.codebrewery.sfgame.model.Game;

public class WarBot implements Commander {

//	private static final long BOT_INTERVAL = 21600000l; //6 godzin
	private Game gd = Game.I;
	private Net net = gd.net;
	private ResponseHandler rh = gd.rh;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		if (args.length < 3) {
			System.err.println("za mało params. daj serwer, username i pass");
			return;
		}
		
		WarBot bot = new WarBot();
		bot.start(args[0], args[1], args[2]);
	}
	
	public void start(final String server, final String uname, final String pass) throws IOException, InterruptedException {
		
		net.setServer(server);
		
		gd.setLogin(uname);
		gd.setPassword(pass);
		
		botTask();
	}

	private void botTask() {
		relogin();
		getGuildData();
		if (gd.getGuild().isAttacking() && !gd.getGuild().isAttackJoined()) {
			print("Guild is attacking, joining!\n");
			joinAttack();
		}
		if (gd.getGuild().isDefending() && !gd.getGuild().isDefenseJoined()) {
			print("Guild is defending, joining!\n");
			joinDefense();
		}
		if (gd.getGuild().isPortalOpen(gd.getPlayerId())) {
			print("Guild portal is open, entering!\n");
			enterGuildPortal();
		}
		logout();
	}
	
	
	private void enterGuildPortal() {
		System.out.println("Entering portal....\n");
		Response resp = net.call(Const.ACT_PORTAL_FIGHT, gd.getSessionId());
		rh.handleResponse(resp, this);
		
	}

	private void joinAttack() {
		Response resp = net.call(Const.ACT_GUILD_JOIN_ATTACK, gd.getSessionId());
		rh.handleResponse(resp, this);
	}

	private void joinDefense() {
		Response resp = net.call(Const.ACT_GUILD_JOIN_DEFENSE, gd.getSessionId());
		rh.handleResponse(resp, this);
	}
	
	private void getGuildData() {
		Response resp = net.call(Const.ACT_SCREEN_GILDEN, gd.getSessionId());
		rh.handleResponse(resp, this);
	}

	@Override
	public void relogin() {
		//można przekazać hasło jako gotowy hash. nie jest to do końca fool-proof, ale rzadko kto ma 32 znakowe hasło hex...
		String hash = gd.getPassword().matches("^[0-9a-f]{32}$") ? gd.getPassword() : Md5.hash(gd.getPassword());
		
		Response resp = net.call(Const.ACT_LOGIN, null, gd.getLogin(), hash, Game.VERSION);
		if (rh.handleResponse(resp, this) == false || resp.isError()) { //zwrot false oznacza: błąd kytyczny, spierdalamy!
			print("#_RCritical error, aborting!\n");
			return;
		}
		
		if (gd.getGuildId() > 0) {
			resp = net.call(Const.ACT_REQUEST_GUILD, gd.getSessionId(), rh.toString(gd.getGuildId()));
			rh.handleResponse(resp, this);
		}
	}
	
	public void logout() {
		Response resp = net.call(Const.ACT_LOGOUT, gd.getSessionId());
		if (rh.handleResponse(resp, this) == false || resp.isError()) { //zwrot false oznacza: błąd kytyczny, spierdalamy!
			print("#_RCritical error, aborting!\n");
			return;
		}
	}

	@Override
	public void nullResponse() {
		print("NULL response\n");
	}

	@Override
	public void print(String out) {
		for (Ansi a : Ansi.values()) {
			out = out.replaceAll("#" + a.toString(), "");
		}
		System.out.print(out);
	}
	
}
