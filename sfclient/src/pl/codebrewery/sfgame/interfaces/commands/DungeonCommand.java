package pl.codebrewery.sfgame.interfaces.commands;

import pl.codebrewery.sfgame.engine.Request;
import pl.codebrewery.sfgame.engine.Response;
import pl.codebrewery.sfgame.interfaces.Cli;
import pl.codebrewery.sfgame.model.Const;
import pl.codebrewery.sfgame.model.Game;

public class DungeonCommand implements Command {

	@Override
	public Response makeHappen(Cli cli, Game g, String... params) {
//		System.err.println(Arrays.asList(params));
		final String cmd = params.length < 2 ? "show" : params[1].toLowerCase();
		
		if ("show".equals(cmd)) {
			cli.print(String.format(
				"   #_YDungeon level:#Z  %3d\n" +
				"   #_YDungeon 13:#Z     %3d\n" +
				"   #_YTower level:#Z    %3d\n" +
				"", g.getDungeonLevel(), g.getDungeon13(), g.getTowerLevel()));
			return null;
		} else if ("go".equals(cmd)) {
			return g.net.call(new Request(Const.ACT_MAINQUEST, g.getSessionId(), Long.toString(g.getDungeonLevel())), true); //debug
		}
		
		cli.print("#RBad dungeon command#Z");
		return null;
		
	}

}
