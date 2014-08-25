package pl.codebrewery.sfgame.engine;

import org.h2.jdbcx.JdbcDataSource;

import pl.codebrewery.sfgame.engine.JdbcUtils.StringParam;
import pl.codebrewery.sfgame.model.Game;

public class Store {

	private JdbcUtils ju;

	public Store() {
		JdbcDataSource ds = new JdbcDataSource();
		ds.setUrl("jdbc:h2:./sfclient_h2");
		ds.setUser("sf");
		ds.setPassword("");
		
		ju = new JdbcUtils(ds);
		checkTables();
	}
	
	private void checkTables() {
		ju.executeQuery("CREATE TABLE IF NOT EXISTS fights "
			+ "(id INT NOT NULL AUTO_INCREMENT, server VARCHAR(32), player VARCHAR(64), when TIMESTAMP, fighter_data VARCHAR(4096), fight_data VARCHAR(4096))");
	}

	public boolean saveFight(Game g, String rawFighter, String rawFight) {
		System.out.println("saving fight...");
		return ju.executeQuery("INSERT INTO fights (server, player, when, fighter_data, fight_data) VALUES (?, ?, CURRENT_TIMESTAMP(), ?, ?)",
			new StringParam(g.net.getServer()),
			new StringParam(g.login),
			new StringParam(rawFighter),
			new StringParam(rawFight)
		);
	}
}
