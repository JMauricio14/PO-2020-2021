package woo.exceptions; 

public class ServiceQualityNotFoundException extends Exception{


	private String _service; 
	
	public ServiceQualityNotFoundException (String service) {
		_service=service; 
	}
	
	
	  public String getService() {
		    return _service;
      }
	  
}
