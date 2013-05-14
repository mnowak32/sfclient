package pl.codebrewery.sfgame.engine.commands;

import pl.codebrewery.sfgame.Cli;
import pl.codebrewery.sfgame.engine.Response;
import pl.codebrewery.sfgame.model.Game;

public class StatsCommand implements Command {

	@Override
	public Response makeHappen(Cli cli, Game g, String... params) {
		cli.print(String.format("\n" +
			"  #CSTR#Z #_W%8d#Z    DMG %d - %d\n" +
			"  #YDEX#Z #_W%8d#Z    DOD %d\n" +
			"  #GINT#Z #_W%8d#Z    RES %d\n" +
			"  #MEND#Z #_W%8d#Z    LIF %d\n" +
			"  #BLUC#Z #_W%8d#Z    CRI %.2f\n",
			g.getStr().getTotal(), 0, 0, g.getDex().getTotal(), 0, g.getIntel().getTotal(), 0, g.getEndur().getTotal(), 0, g.getLuck().getTotal(), 0.0));

		return null;
	}

}
