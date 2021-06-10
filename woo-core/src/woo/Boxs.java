package woo; 

/**
* Class Store implements the Boxs.
*/
public class Boxs extends Products {
	 private ServiceLevel _nivelServico; 
	    
	    public Boxs(String id,String service, String idF,int price,int criticalValue, int quantity) {
	    	super(id, idF, criticalValue, price,quantity); 
	 	   _nivelServico=_nivelServico.valueOf(service); 
	    }
	    
	    /**Method that converts a Box to a String
	     * 
	     * @return the id
	     */
	    @Override
	    public String toString() {
	    	return "BOX|" + super.toString() + _nivelServico;
	    }
	    
}