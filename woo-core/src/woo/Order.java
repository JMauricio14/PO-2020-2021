package woo; 


import java.util.List; 
import java.util.ArrayList;

public class Order extends Transactions {

	private String _idF; 
	private String _idP; 
	private int _quantity; 
	private List<String> _products= new ArrayList<>(); 
	
	public Order(int id, int cost, int date, String idF, String idP, int quantity) {
		super(id,cost,date); 
		this._idF=idF; 
		this._idP=idP; 
		this._quantity=quantity; 
	}
	
	public void getProducts(String str){
		_products.add(str);  
	}
	
	
	public int getToPay(){
		return super.getCost(); 
	}
	
	    public String getIDC() {
	    	return""; 
	    }
	    
	    public String getIDP() {
	    	return _idP; 
	    }
	    
	    public int getLimitDate() {
	    	return 0; 
	    }
	
	    public String TypeTransaction() {
	    	return "ORDER"; 
	    }
	    
	    public int getQuantity() {
	    	return _quantity; 
	    }
	    
	    public void setIsPayed() {
	    	//EMPTY
	    }
	
	
	@Override
	public String toString() {
		String order= super.getID() + "|" + _idF.toUpperCase() + "|" + super.getCost() + "|" + super.getDate() + "\n" ; 
		for(String s : _products){
			order+=s; 
		}
		return order; 
	}
}
