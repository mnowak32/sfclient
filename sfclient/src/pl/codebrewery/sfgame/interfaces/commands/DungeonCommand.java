package pl.codebrewery.sfgame.interfaces.commands;

import java.util.Arrays;

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
			cli.print("  #_YDungeons#Z\n" +
				"\t#\tEn\tFin?\n");
			Arrays.stream(g.dungeons).forEach(d -> {
				cli.print(d.toString() + "\n");
			});
			
			cli.print(String.format("  #_YTower level:#Z    %3d\n", g.towerLevel));
			return null;
		} else if ("go".equals(cmd)) {
			if (g.dungeonEnterLevel == 0) { //no dungeon to enter
				cli.print("#_BCannot enter any dungeon#Z\n");
				return null;
			}
			if (g.isDungeonWait()) {
				cli.print("#_BHave to wait, or use shroom [dun go!]...#Z\n");
				return null;
			} else {
//				System.out.println("to call" + Const.ACT_MAINQUEST + g.dungeonEnterLevel);
				return g.net.call(new Request(Const.ACT_MAINQUEST, g.getSessionId(), Integer.toString(g.dungeonEnterLevel)), true); //debug
			}
		} else if ("go!".equals(cmd)) { //force entry to the dungeon using shroom
			if (g.dungeonEnterLevel == 0) { //no dungeon to enter
				cli.print("#_BCannot enter any dungeon#Z\n");
				return null;
			}
			if (g.isDungeonWait()) {
				return g.net.call(new Request(Const.ACT_MAINQUEST, g.getSessionId(), Integer.toString(g.dungeonEnterLevel)), true); //debug
			} else {
				cli.print("#_BNo need to use shroom. Try [dun go] instead...#Z\n");
				return null;
			}
		}
		
		cli.print("#RBad dungeon command#Z");
		return null;
		
	}

}
