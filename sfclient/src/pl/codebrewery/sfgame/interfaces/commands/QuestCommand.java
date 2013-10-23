package pl.codebrewery.sfgame.interfaces.commands;

import pl.codebrewery.sfgame.engine.Request;
import pl.codebrewery.sfgame.engine.Response;
import pl.codebrewery.sfgame.interfaces.Cli;
import pl.codebrewery.sfgame.model.Const;
import pl.codebrewery.sfgame.model.Game;

public class QuestCommand implements Command {

	@Override
	public Response makeHappen(Cli cli, Game g, String... params) {
		return g.net.call(new Request(Const.ACT_SCREEN_TAVERNE, g.getSessionId()));
	}

}
