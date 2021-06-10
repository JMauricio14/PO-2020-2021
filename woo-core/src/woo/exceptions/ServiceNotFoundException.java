package woo.exceptions; 

public class ServiceNotFoundException extends Exception{


	private String _service; 
	
	public ServiceNotFoundException(String service) {
		_service=service; 
	}
	
	
	  public String getService() {
		    return _service;
      }
	  
}