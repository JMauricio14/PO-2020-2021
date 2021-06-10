
package woo; 

import java.util.List; 
import java.util.ArrayList;
import java.io.Serializable;

/**
* Class Store implements the Suppliers.
*/
public class Suppliers implements Serializable{
	private String _name; 
	private String _morada; 
	private String _id; 
	private String _active="SIM"; 
	private List<Transactions> _trans=new ArrayList<>(); 
	
	public Suppliers(String id, String name, String morada){
		this._id=id; 
		this._morada=morada; 
		this._name=name; 
	}
	
	/** Method that returns the id of a Supplier
	 * 
	 * @return the id
	 */
	public String getID(){
		return _id; 
	}
	
	public String getActive() {
		return _active; 
	}
	
	public void setActive(String str) {
		_active=str; 
	}
	
	public List<Transactions> getTransactions(){
		return _trans; 
	}
	/**Method that converts a Supplier to a String
	 * 
	 */
	public String toString() {
		return _id + "|" +_name+ "|" + _morada+ "|" + _active; 
	}
}
