package repository.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import fileSystem.FileInterpreter;
import fileSystem.FileManagement;
import infra.Game.GameFileConverter;
import infra.Game.PlatformFileConverter;
import infra.Game.RequirementFileConverter;
import model.dto.Game.GameDto;
import model.dto.Game.PlatformDto;
import model.dto.Game.RequirementDto;
import model.entities.Category.Category;
import model.entities.Game.Game;
import repository.IRepository;

public class GameRepository implements IRepository<Game, Long> {

	private static Long SEQUENCE = 0L;

    private final FileInterpreter fileInterpreter;
    
    private final FileManagement fileManagement;
    private final GameFileConverter gameFileConverter;

    private final CategoryRepository categoryRepository;
    
    private final FileManagement requirementManagement;
    private final RequirementFileConverter requirementFileConverter;
    
    private final FileManagement platformManagement;
    private final PlatformFileConverter platformFileConverter;
    
    public GameRepository() {
        this.fileInterpreter = new FileInterpreter();
        
        this.fileManagement = new FileManagement("C:\\jsp_dados\\game.csv");
        this.gameFileConverter = new GameFileConverter();

        this.categoryRepository = new CategoryRepository();
        
        this.requirementManagement = new FileManagement("C:\\jsp_dados\\requirement.csv");
        this.requirementFileConverter = new RequirementFileConverter();
        
        this.platformManagement = new FileManagement("C:\\jsp_dados\\platform.csv");
        this.platformFileConverter = new PlatformFileConverter();
        
    }

	@Override
	public void save(Game game) {
		if(game.getId() == null) {
			game.setId(++SEQUENCE);
		}

		delete(game.getId());
		
		GameDto gameDto = new GameDto(game.getId(), game.getTitle(), game.getPublisher(), game.getRelease(), game.getSynopsis(), game.getCategory().getId());
		
		for(Map.Entry<String, String> entry : game.getRequirement().entrySet()) {
			RequirementDto requirementDto = new RequirementDto(entry.getKey(), entry.getValue(), game.getId());
			requirementManagement.write(requirementDto);
		}
		
		for(String plat : game.getPlatform()) {
			PlatformDto platformDto = new PlatformDto(plat, game.getId());
			platformManagement.write(platformDto);
		}
		
		fileManagement.write(gameDto);
	}

	@Override
	public void saveAll(Collection<Game> collection) {
		for(Game game : collection) {
			save(game);
		}

	}

	@Override
	public Game find(Long identifier) {
		Collection<Game> games = findAll();
        for (Game game : games) {
            if(game.getId().equals(identifier)){
                return game;
            }
        }
        return null;
	}

	@Override
	public Collection<Game> findAll() {
		Collection<GameDto> gamesDto = gameFileConverter.all(fileInterpreter.interpret(fileManagement.read(), GameDto.class));
		
		Collection<RequirementDto> requirementsDto = requirementFileConverter.all(fileInterpreter.interpret(requirementManagement.read(), RequirementDto.class));
		
		Collection<PlatformDto> platformsDto = platformFileConverter.all(fileInterpreter.interpret(platformManagement.read(), PlatformDto.class));
		
		Collection<Game> games = new ArrayList<>();
		
		gamesDto.forEach( dto -> games.add(this.generate(dto, requirementsDto, platformsDto)) );

		return games;
	}

	@Override
	public Collection<Game> findAll(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long identifier) {
		Collection<Game> games = findAll();
		Collection<RequirementDto> requirementsDto = requirementFileConverter.all(fileInterpreter.interpret(requirementManagement.read(), RequirementDto.class));
		Collection<PlatformDto> platformsDto = platformFileConverter.all(fileInterpreter.interpret(platformManagement.read(), PlatformDto.class));	
		
        fileManagement.clear();
		requirementManagement.clear();
		platformManagement.clear();
		
		platformsDto.removeIf( plat -> plat.getGameId().equals(identifier) );
		requirementsDto.removeIf( req -> req.getGameId().equals(identifier) );
        games.removeIf( game -> game.getId().equals(identifier) );
        
        games.forEach( game -> fileManagement.write(new GameDto(game.getId(), game.getTitle(), game.getPublisher(), game.getRelease(), game.getSynopsis(), game.getCategory().getId())));
        requirementsDto.forEach(requirementManagement::write);
        platformsDto.forEach(platformManagement::write);
	}

	private Game generate(GameDto gameDto, Collection<RequirementDto> requirementsDto, Collection<PlatformDto> platformsDto) {
		Category category = categoryRepository.find(gameDto.getCategoryId());
		
		Map<String, String> requirement = new HashMap<>();
		for(RequirementDto req : requirementsDto) {
			if(req.getGameId().equals(gameDto.getId())) {
				requirement.put(req.getComponent(), req.getDescription());
			}
		}
		
		Collection<String> platform = new ArrayList<>();
		for(PlatformDto plat : platformsDto) {
			if(plat.getGameId().equals(gameDto.getId())) {
				platform.add(plat.getPlatform());
			}
		}
		
		return new Game(gameDto.getId(), gameDto.getTitle(), gameDto.getPublisher(), gameDto.getRelease(), gameDto.getSynopsis(), category, requirement, platform);
	}

}
