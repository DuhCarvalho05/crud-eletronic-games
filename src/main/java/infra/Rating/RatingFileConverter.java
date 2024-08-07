package infra.Rating;

import java.util.ArrayList;
import java.util.Collection;

import fileSystem.FileConvert;
import model.dto.Rating.RatingDto;

public class RatingFileConverter implements FileConvert<RatingDto>{

	@Override
	public RatingDto generate(String... args) {
		return new RatingDto().fromString(args);
	}

	@Override
	public Collection<RatingDto> all(Collection<String[]> dataset) {
		Collection<RatingDto> ratingsDto = new ArrayList<>();
		dataset.forEach( rating -> ratingsDto.add(generate(rating)) );
		return ratingsDto;
	}

}
