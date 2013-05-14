package pl.codebrewery.sfgame.engine.commands;

import pl.codebrewery.sfgame.Cli;
import pl.codebrewery.sfgame.engine.Request;
import pl.codebrewery.sfgame.engine.Response;
import pl.codebrewery.sfgame.model.Const;
import pl.codebrewery.sfgame.model.Game;

public class ChatCommand implements Command {

	@Override
	public Response makeHappen(Cli cli, Game g, String... params) {
		return g.net.call(new Request(Const.ACT_GET_CHAT_HISTORY, g.getSessionId(), Integer.toString(g.getGuildId())));
	}

}
