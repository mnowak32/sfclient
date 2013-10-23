package pl.codebrewery.sfgame.interfaces.commands;

import pl.codebrewery.sfgame.engine.Response;
import pl.codebrewery.sfgame.interfaces.Cli;
import pl.codebrewery.sfgame.model.Const;
import pl.codebrewery.sfgame.model.Game;

public class AlbumCommand implements Command {

	@Override
	public Response makeHappen(Cli cli, Game g, String... params) {
		return g.net.call(Const.ACT_ALBUM, g.getSessionId());
	}

}
