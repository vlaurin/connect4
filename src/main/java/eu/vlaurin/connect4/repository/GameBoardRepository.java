package eu.vlaurin.connect4.repository;

import eu.vlaurin.connect4.model.GameBoard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author vlaurin
 * @since 0.0.0
 */
@Repository
public class GameBoardRepository implements CrudRepository<GameBoard, Long> {
    private AtomicLong counter = new AtomicLong(0);
    private Map<Long, GameBoard> registry = new HashMap<>();

    @Override
    public <S extends GameBoard> S save(S gameBoard) {
        final Long id = counter.incrementAndGet();
        gameBoard.setId(id);
        registry.put(id, gameBoard);

        return gameBoard;
    }

    @Override
    public <S extends GameBoard> Iterable<S> save(Iterable<S> iterable) {
        throw new NotImplementedException();
    }

    @Override
    public GameBoard findOne(Long id) {
        return registry.get(id);
    }

    @Override
    public boolean exists(Long aLong) {
        throw new NotImplementedException();
    }

    @Override
    public Iterable<GameBoard> findAll() {
        throw new NotImplementedException();
    }

    @Override
    public Iterable<GameBoard> findAll(Iterable<Long> iterable) {
        throw new NotImplementedException();
    }

    @Override
    public long count() {
        throw new NotImplementedException();
    }

    @Override
    public void delete(Long aLong) {
        throw new NotImplementedException();
    }

    @Override
    public void delete(GameBoard gameBoard) {
        throw new NotImplementedException();
    }

    @Override
    public void delete(Iterable<? extends GameBoard> iterable) {
        throw new NotImplementedException();
    }

    @Override
    public void deleteAll() {
        throw new NotImplementedException();
    }
}
