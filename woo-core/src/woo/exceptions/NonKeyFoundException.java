package woo.exceptions; 

public class NonKeyFoundException extends Exception{

	private String _id; 
	
	public NonKeyFoundException(String id) {
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
