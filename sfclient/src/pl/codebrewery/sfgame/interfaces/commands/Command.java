package pl.codebrewery.sfgame.interfaces.commands;

import pl.codebrewery.sfgame.engine.Response;
import pl.codebrewery.sfgame.interfaces.Cli;
import pl.codebrewery.sfgame.model.Game;

public interface Command {
	Response makeHappen(Cli cli, Game g, String... params);
}
