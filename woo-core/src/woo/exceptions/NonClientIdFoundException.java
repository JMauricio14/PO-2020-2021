package woo.exceptions;

/** Launched when a id is not found*/
public class NonClientIdFoundException extends Exception{

	private String _id; 
	
	public NonClientIdFoundException (String id) {
		_id=id; 
	}
	
	/**Method thet gets the id
	 * 
	 * @return the id
	 */
	  public String getID() {
		    return _id;
		  }
	  
}
