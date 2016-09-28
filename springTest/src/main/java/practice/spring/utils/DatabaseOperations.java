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

	public void deleteRepo(Integer id);

	public void createRepo(Integer id, Object value);

	public void updateRepo(Integer id, Object value);

	public Object getRepo(Integer id);

	public Object getRepoList(Integer id);
}
