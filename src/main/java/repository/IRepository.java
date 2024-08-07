package repository;

import java.util.Collection;
import java.util.Map;

public interface IRepository<T, I> {

	public void save(T t);

	public void saveAll(Collection<T> collection);

	public T find(I identifier);

	public Collection<T> findAll();

	public Collection<T> findAll(Map<String, Object> params);

	public void delete(I identifier);

}
