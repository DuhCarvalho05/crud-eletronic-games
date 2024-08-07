package infra.Game;

import java.util.ArrayList;
import java.util.Collection;

import fileSystem.FileConvert;
import model.dto.Game.GameDto;

public class GameFileConverter implements FileConvert<GameDto> {

	@Override
	public GameDto generate(String... args) {
		return new GameDto().fromString(args);
	}

	@Override
	public Collection<GameDto> all(Collection<String[]> dataset) {
		Collection<GameDto> gamesDto = new ArrayList<>();
		dataset.forEach(game -> gamesDto.add(generate(game)));
		return gamesDto;
	}

}
