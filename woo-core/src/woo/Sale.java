package woo; 

import java.util.List; 
import java.util.ArrayList;

public class Sale extends Transactions{
  
	private String _idC; 
	private String _idP; 
	private int _quantity; 
	private int _toPay; 
	private int _limitDate; 
	private List<String> _products= new ArrayList<>(); 
	private boolean _isPayed=false; 
	
	public Sale(int id, int date,int cost, String idC,String idP,int quantity,int toPay,int limitDate) {
		super(id,cost,date); 
		this._idC=idC;
		this._idP=idP; 
		this._quantity=quantity; 
		this._toPay=toPay; 
		this._limitDate=limitDate; 
	}
	
	public void getProducts(String str){
		_products.add(str); 
	}
	
	public int getToPay() {
		return _toPay; 
	}
	    
	    public String getIDC() {
	    	return _idC; 
	    }
	    
	    public String getIDP() {
	    	return _idP; 
	    }
	    
	    public int getLimitDate() {
	    	return _limitDate; 
	    }
	    
	    public String TypeTransaction() {
	    	return "SALE"; 
	    }
	    
	    public int getQuantity() {
	    	return _quantity; 
	    }
	
	    public void setIsPayed(){
	    	_isPayed=true; 
	    }
	    
	@Override
	public String toString() {
		String result=""; 
		if(_isPayed==false) {
		result= super.getID() + "|" + _idC.toUpperCase() + "|" + _idP.toUpperCase() + "|" + _quantity + "|" + super.getCost() + "|" + _toPay + "|" + _limitDate ;  
		}else {
			result = super.getID() + "|" + _idC.toUpperCase() + "|" + _idP.toUpperCase() + "|" + _quantity + "|" + super.getCost() + "|" + _toPay + "|" + _limitDate + "|" + super.getDate(); 
		}
		return result; 
		}
}
