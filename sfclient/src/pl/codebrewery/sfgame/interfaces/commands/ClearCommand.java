package pl.codebrewery.sfgame.interfaces.commands;

import pl.codebrewery.sfgame.engine.Response;
import pl.codebrewery.sfgame.interfaces.Cli;
import pl.codebrewery.sfgame.model.Game;

public class ClearCommand implements Command {

	@Override
	public Response makeHappen(Cli cli, Game g, String... params) {
		cli.cls();
		return null;
	}

}
