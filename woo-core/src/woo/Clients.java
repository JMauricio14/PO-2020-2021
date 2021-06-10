package woo; 
import java.util.List;

import java.util.ArrayList;

import java.io.Serializable;

/**
* Class that implements the Clients.
*/
public class Clients implements Serializable{

	private String _name;
	private String _id;
	private String _morada; 
	private String _state = ClientClassification.NORMAL.name(); 
	private int compraEfec=0; 
	private int _compraPaga=0; 	
	private int _pontos=0; 
	private List<Transactions> _trans= new ArrayList<>();
	
	public Clients(String id, String name, String morada) {
		this._name=name; 
		this._id=id; 
		this._morada=morada;  
	}
	
	
	/** Method that returns the id of a Client
	 * 
	 * @return the id
	 */
	public String getID(){
		return _id; 
	}
	
	public void setCompraEfec(int efec) {
		compraEfec+=efec; 
	}
	
	public String getState() {
		return _state; 
	}

	public List<Transactions> getTransactions(){
		return _trans; 
	}
	
	public int getValuePay() {
		return _compraPaga; 
	}
	    
	public void setValuePay(int compraPaga) {
		_compraPaga+=compraPaga; 
	}
	
	public int getPoints() {
		return _pontos;
	}
	
	public void setPoints(int pontos) {
		_pontos=pontos; 
	}
	
	public void setState(String state) {
		_state=state; 
	}
	/**Method that converts a Client to a String
	 * 
	 */
	public String toString() {
		return _id+ "|" +_name+ "|" + _morada + "|" + _state + "|" + compraEfec + "|" + _compraPaga; 
	}
	
}
