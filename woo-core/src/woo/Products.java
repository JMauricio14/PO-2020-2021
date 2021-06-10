package woo;

import java.io.Serializable;

/**
* Class that implements the Products.
*/
public class Products implements Serializable{

	private String _id; 
	private String _idF;        //id of the supplier
	private int _critValue;
	private int _price; 
	private int _quantity; 
	
	
	
	public Products(String id,String idF,int price,int criticalValue, int quantity) {
		this._id=id; 
		this._idF=idF; 
	    this._critValue=criticalValue;
	    this._price=price; 
	    this._quantity=quantity;
	}
	
	/**Method that returns the id of a Product
	 * 
	 * @return the id
	 */
	public String getID() {
		return _id; 
	}
	
	public int getPrice() {
		return _price; 
	}
	
	public void setPrice(int price) {
		_price=price; 
	}
	
	public int getQuantity() {
		return _quantity; 
	}
	
	public void setQuantity(int quantity) {
		_quantity-=quantity; 
	}
	
	public void setQuantitySup(int quantity) {
		_quantity+=quantity; 
	}
	
	/**Method that converts the Products to a String
	 * 
	 */
	public String toString() {
		return _id + "|" + _idF + "|" + _price +"|" + _critValue + "|" + _quantity +"|"; 
	}
	
	
}
