package woo; 

/**
* Class Store implements the Containers.
*/
public class Container extends Products{
    
    private ServiceLevel _nivelServico; 
	private ServiceQuality _qualidadeServico; 
	
	public Container(String id,String service,String serviceQ, String idF,int price,int criticalValue, int quantity) {
	 	super(id, idF, criticalValue, price,quantity); 
	 	this._nivelServico=_nivelServico.valueOf(service); 
  	    this._qualidadeServico=_qualidadeServico.valueOf(serviceQ); 
     }
	
	/**Method that converts a Container to a String
	 * 
	 * @return the id
	 */
	@Override
	public String toString() {
		return "CONTAINER|" + super.toString() + _nivelServico + "|" +_qualidadeServico; 
	}
}
