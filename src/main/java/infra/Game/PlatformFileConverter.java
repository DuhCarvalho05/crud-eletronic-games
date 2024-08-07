package infra.Game;

import java.util.ArrayList;
import java.util.Collection;

import fileSystem.FileConvert;
import model.dto.Game.PlatformDto;

public class PlatformFileConverter implements FileConvert<PlatformDto>{

	@Override
	public PlatformDto generate(String... args) {
		return new PlatformDto().fromString(args);
	}

	@Override
	public Collection<PlatformDto> all(Collection<String[]> dataset) {
		Collection<PlatformDto> platformsDto = new ArrayList<>();
		dataset.forEach( platform -> platformsDto.add(generate(platform)) );
		return platformsDto;
	}

}
