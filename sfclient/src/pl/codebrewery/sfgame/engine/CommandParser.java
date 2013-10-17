package pl.codebrewery.sfgame.engine;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import pl.codebrewery.sfgame.engine.commands.AlbumCommand;
import pl.codebrewery.sfgame.engine.commands.ChatCommand;
import pl.codebrewery.sfgame.engine.commands.ClearCommand;
import pl.codebrewery.sfgame.engine.commands.ColorCommand;
import pl.codebrewery.sfgame.engine.commands.Command;
import pl.codebrewery.sfgame.engine.commands.QuestCommand;
import pl.codebrewery.sfgame.engine.commands.StatsCommand;
import pl.codebrewery.sfgame.interfaces.Cli;
import pl.codebrewery.sfgame.model.Game;

public class CommandParser {
	
	private Map<String, Command> cmds = new HashMap<>();
	
	public CommandParser() {
		cmds.put("sta", new StatsCommand());
		cmds.put("cha", new ChatCommand());
		cmds.put("que", new QuestCommand());
		cmds.put("alb", new AlbumCommand());
		cmds.put("col", new ColorCommand());
		cmds.put("cle", new ClearCommand());
	}

	public boolean parseCommand(String line, Cli cli) {
		if (line == null || line.startsWith("qui")) {
			cli.print("\nbajo!\n");
			return false;
		} else if (StringUtils.isBlank(line)){
			return true;
		}
		
		for (String com : cmds.keySet()) {
			if (line.startsWith(com)) {
				String[] parts = StringUtils.split(line, " ");
				ArrayUtils.remove(parts, 0); //usuń komendę
				Response resp = cmds.get(com).makeHappen(cli, Game.I, parts);
				if (resp != null) {
					Game.I.rh.handleResponse(resp, cli);
				}
				return true;
			}
		}
		
		cli.print("Unrecognized command!");
		
//		if (line.startsWith("que")) {
//			cli.enterTaverne();
//		} else if (line.startsWith("cha")) {
//			cli.showChat();
//		} else if (line.startsWith("abort ")) {
//			cli.doAbort(line.substring(6));
//		}
		
		return true;
	}
	
	
}
