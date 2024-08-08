package repository.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import fileSystem.FileInterpreter;
import fileSystem.FileManagement;
import infra.Rating.RatingFileConverter;
import model.dto.Rating.RatingDto;
import model.entities.Game.Game;
import model.entities.Rating.Rating;
import model.entities.User.User;
import repository.IRepository;

public class RatingRepository implements IRepository<Rating, Long> {

	private static Long SEQUENCE = 0L;
	
	private final String ratingFileName = "rating.csv";
	
	private final FileManagement fileManagement;
    private final FileInterpreter fileInterpreter;
    private final RatingFileConverter ratingFileConverter;
    
    private final UserRepository userRepository;
    private final GameRepository gameRepository;
    
	public RatingRepository() {
		this.fileManagement = new FileManagement();
		this.fileInterpreter = new FileInterpreter();
		this.ratingFileConverter = new RatingFileConverter();
		
		this.userRepository = new UserRepository();
		this.gameRepository = new GameRepository();
	}

	@Override
	public void save(Rating rating) {
		if(rating.getId() == null) {
			rating.setId(++SEQUENCE);
		}
		
		delete(rating.getId());
		RatingDto ratingDto = new RatingDto(rating.getId(), rating.getDescription(), rating.getStars(), rating.getCreatedAt(), rating.getUser().getId(), rating.getGame().getId());
		fileManagement.write(ratingDto, ratingFileName);
	}

	@Override
	public void saveAll(Collection<Rating> collection) {
		for(Rating rating : collection) {
			save(rating);
		}
		
	}

	@Override
	public Rating find(Long identifier) {
		Collection<Rating> ratings = findAll();
		for(Rating rating : ratings) {
			if(rating.getId().equals(identifier)) {
				return rating;
			}
		}
		return null;
	}

	@Override
	public Collection<Rating> findAll() {
		Collection<RatingDto> ratingsDto = ratingFileConverter.all(fileInterpreter.interpret(fileManagement.read(ratingFileName), RatingDto.class));
		
		Collection<Rating> ratings = new ArrayList<>();
		ratingsDto.forEach( dto -> ratings.add(generate(dto)));
		return ratings;
	}

	@Override
	public Collection<Rating> findAll(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long identifier) {
		Collection<Rating> ratings = findAll();
		fileManagement.clear(ratingFileName);
		ratings.removeIf( rating -> rating.getId().equals(identifier) );
		saveAll(ratings);
		
	}

	private Rating generate(RatingDto ratingDto) {
		User user = userRepository.find(ratingDto.getUserId());
		Game game = gameRepository.find(ratingDto.getGameId());
		return new Rating(ratingDto.getId(), ratingDto.getDescription(), ratingDto.getStars(), ratingDto.getCreatedAt(), user, game);
	}

}
