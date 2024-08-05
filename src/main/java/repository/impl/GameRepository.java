package repository.impl;

import java.util.Collection;
import java.util.Map;

import fileSystem.FileInterpreter;
import fileSystem.FileManagement;
import infra.GameFileConverter;
import model.entities.Game.Game;
import repository.IRepository;

public class GameRepository implements IRepository<Game, Long> {

	private static Long SEQUENCE = 0L;
	
	private final FileManagement fileManagement;
    private final FileInterpreter fileInterpreter;
    private final GameFileConverter gameFileConverter;
    
    public GameRepository() {
    	this.fileManagement = new FileManagement("/Users/caiolopes/Downloads/game.csv");
        this.fileInterpreter = new FileInterpreter();
        this.gameFileConverter = new GameFileConverter();
    }
	
	@Override
	public void save(Game game) {
		if(game.getId() == null) {
			game.setId(++SEQUENCE);
		}
		
		delete(game.getId());
		fileManagement.write(game);
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
		return gameFileConverter.all(fileInterpreter.interpret(fileManagement.read(), Game.class));
	}

	@Override
	public Collection<Game> findAll(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long identifier) {
		Collection<Game> games = findAll();
        fileManagement.clear();
        games.removeIf(game -> game.getId().equals(identifier));
        saveAll(games);
		
	}

	@Override
	public void deleteAll(Map<String, Object> params) {
		// TODO Auto-generated method stub
		
	}

}
