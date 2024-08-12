package repository.impl;

import java.util.ArrayList;
import java.util.Collection;

import fileSystem.FileInterpreter;
import fileSystem.FileManagement;
import infra.Rating.RatingFileConverter;
import model.dto.Rating.RatingDto;
import model.entities.Category.Category;
import model.entities.Game.Game;
import model.entities.Rating.Rating;
import model.entities.User.User;
import repository.IRepository;

public class RatingRepository implements IRepository<Rating, Long> {

	private Long SEQUENCE = 0L;

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
		
		Collection<Rating> rating = findAll();
    	if(!rating.isEmpty()) {
    		var aux = 0L;
    		for(Rating g : rating) {
    			if (aux < g.getId()) {
    				aux = g.getId();
    			}
    		}
    		SEQUENCE = aux;    		
    	}
	}

	@Override
	public void save(Rating rating) {
		if(rating.getId() == null) {
			rating.setId(++SEQUENCE);
		}

		deleteById(rating.getId());
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
	public Rating findById(Long identifier) {
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
	public void deleteById(Long identifier) {
		Collection<Rating> ratings = findAll();
		fileManagement.clear(ratingFileName);
		ratings.removeIf( rating -> rating.getId().equals(identifier) );
		ratings.forEach( rating -> fileManagement.write(new RatingDto(rating.getId(), rating.getDescription(), rating.getStars(), rating.getCreatedAt(), rating.getUser().getId(), rating.getGame().getId()), ratingFileName) );
	}
	
	public Collection<Rating> findByGameId(Long gameId){
		Collection<Rating> ratings = findAll();
		
		Collection<Rating> ratingsOfThisGame = new ArrayList<>();
		for(Rating rating : ratings) {
			if(rating.getGame().getId().equals(gameId)) {
				ratingsOfThisGame.add(rating);
			}
		}
		
		return ratingsOfThisGame;
	}

	private Rating generate(RatingDto ratingDto) {
		User user = userRepository.findById(ratingDto.getUserId());
		Game game = gameRepository.findById(ratingDto.getGameId());
		return new Rating(ratingDto.getId(), ratingDto.getDescription(), ratingDto.getStars(), ratingDto.getCreatedAt(), user, game);
	}

}
