package woo.exceptions; 

/** Launched when a Key already exists. */
public class KeyAlreadyExistsException extends Exception{


	private String _id; 
	
	public KeyAlreadyExistsException(String id) {
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
