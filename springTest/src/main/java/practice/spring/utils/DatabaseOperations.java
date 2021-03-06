package practice.spring.utils;

import javax.sql.DataSource;

/**
 * 
 * This interface provides methods for basic database operations
 * 
 * @author Bhaskar
 */
public interface DatabaseOperations {

	public void setDataSource(DataSource ds);

	public void deleteRepo(int id);

	public long createRepo(int id, Object value);

	public Object getByProperty(String property, Object value);

	public void updateRepo(int id, Object value);

	public Object getRepo(int id);

	public Object getRepoList(int id);
}
