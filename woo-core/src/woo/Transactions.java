package woo; 

import java.util.List; 
import java.util.ArrayList;
import java.io.Serializable;

public abstract class Transactions implements Serializable{
     
	private int _id;  
	private int _cost; 
	private int _date; 
	
	
	
	public Transactions(int id, int cost, int date){
         this._id=id; 
         this._cost=cost; 
         this._date=date; 
	}
	
	public int getID() {
		return _id; 
	}

    public int getCost() {
        return _cost; 	
    }
    
    public void setCost(int cost){
    	_cost+=cost;
    }
   
    public abstract int getToPay();
    
    public abstract String getIDC(); 
    
    public abstract String getIDP();  
    
    public abstract int getLimitDate(); 
    
    public abstract String TypeTransaction(); 
    
    public abstract int getQuantity(); 
    
    public int getDate() {
       return _date; 
    }
    
    
    public void setData(int data) {
    	_date=data; 
    }
    
    public abstract void setIsPayed(); 
    

    
    public abstract void getProducts(String str); 
	
	public abstract String toString(); 
	
}
