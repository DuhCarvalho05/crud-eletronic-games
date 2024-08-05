package fileSystem;

import java.util.Collection;

public interface FileConvert<T> {

	public T generate(String ...args);

    public Collection<T> all(Collection<String[]> dataset);
	
}
