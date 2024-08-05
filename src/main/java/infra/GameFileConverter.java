package infra;

import java.util.ArrayList;
import java.util.Collection;

import fileSystem.FileConvert;
import model.entities.Game.Game;

public class GameFileConverter implements FileConvert<Game> {

	@Override
	public Game generate(String... args) {
		return new Game().fromString(args);
	}

	@Override
	public Collection<Game> all(Collection<String[]> dataset) {
		Collection<Game> games = new ArrayList<>();
		dataset.forEach(game -> games.add(generate(game)));
		return games;
	}

}
