package pl.codebrewery.sfgame.engine.commands;

import pl.codebrewery.sfgame.engine.Response;
import pl.codebrewery.sfgame.interfaces.Cli;
import pl.codebrewery.sfgame.model.Game;

public class ColorCommand implements Command {

	@Override
	public Response makeHappen(Cli cli, Game g, String... params) {
		cli.switchColor();
		return null;
	}

}
