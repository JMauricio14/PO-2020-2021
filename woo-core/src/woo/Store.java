package woo;

import java.io.IOException;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;

import java.util.Collections;
import java.util.Collection;
import java.util.List; 
import java.util.ArrayList;

import woo.exceptions.NotActiveSupplierException; 
import woo.exceptions.UnavailableQuantityException; 
import woo.exceptions.NonKeyFoundException; 
import woo.exceptions.BadEntryException;
import woo.exceptions.KeyAlreadyExistsException; 
import woo.exceptions.ServiceQualityNotFoundException; 
import woo.exceptions.NonClientIdFoundException; 
import woo.exceptions.ServiceNotFoundException; 

//FIXME import classes (cannot import from pt.tecnico or woo.app)

/**
 * Class Store implements a store.
 */
public class Store implements Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 202009192006L;
  private int _date=0;
  private int _idTrans=0; 
  private Map<String,Products> _products = new TreeMap<String,Products>(String.CASE_INSENSITIVE_ORDER); 
  private Map<String,Clients> _clients = new TreeMap<String,Clients>(String.CASE_INSENSITIVE_ORDER); 
  private Map<String,Suppliers> _supplier = new TreeMap<String,Suppliers>(String.CASE_INSENSITIVE_ORDER); 
  private Map<Integer,Transactions> _transactions= new TreeMap<Integer,Transactions>(); 
  private Map<String,String> _typeProduct = new TreeMap<String,String>(String.CASE_INSENSITIVE_ORDER); 
  private Map<String,Integer> _nrTransactions = new TreeMap<String,Integer>(String.CASE_INSENSITIVE_ORDER); 
  // FIXME define attributes
  // FIXME define contructor(s)
  // FIXME define methods
  
  

  
  /**Function that imports the file
   * 
   * @param txtfile filename to be loaded.
   * @throws IOException
   * @throws BadEntryException
   */
  void importFile(String txtfile) throws IOException, BadEntryException /* FIXME maybe other exceptions */ {
	  BufferedReader reader = new BufferedReader(new FileReader(txtfile));
      String line;
      while ((line = reader.readLine()) != null) {
              String[] fields = line.split("\\|");
              registFields(fields); 
      }
      reader.close();
  }
  
  /**Method that regist the fields from a certain file, in this case there is not fields with wrong entries
   * 
   * @param fields
   */
  void registFields(String [] fields) {
	  switch(fields[0]) {
	  case "SUPPLIER":
		  Suppliers supplier= new Suppliers(fields[1],fields[2], fields[3]); 
		  _supplier.put(supplier.getID(),supplier); 
		  break; 
	  case "CLIENT":
		  Clients clients= new Clients(fields[1],fields[2], fields[3]); 
		  _clients.put(clients.getID(), clients);  
		  break; 
	  case "BOOK": 
		 Book book = new Book(fields[1],fields[2],fields[3],fields[4],fields[5], Integer.parseInt(fields[7]),Integer.parseInt(fields[6]),Integer.parseInt(fields[8]));
		 _products.put(book.getID(), book); 
		 _typeProduct.put(book.getID(),"BOOK"); 
		  break; 
	  case "CONTAINER":
		  Container container= new Container(fields[1],fields[2],fields[3],fields[4], Integer.parseInt(fields[6]),Integer.parseInt(fields[5]),Integer.parseInt(fields[7])); 
	      _products.put(container.getID(), container); 
	      _typeProduct.put(container.getID(),"CONTAINER"); 
		  break; 
	  case "BOX":
		  Boxs box= new Boxs(fields[1],fields[2],fields[3],Integer.parseInt(fields[5]),Integer.parseInt(fields[4]),Integer.parseInt(fields[6])); 
		  _products.put(box.getID(), box);
		  _typeProduct.put(box.getID(),"BOX"); 
	      break; 
	  }
  }
  
  /** Data **/
  
  /**Method that advances the current date
   * 
   * @param date
   */
  public void advanceDate(int date) {
	  _date+=date;
  }
  
  /**Method that return the current date
   * 
   * @return currentDate
   */
  public int getDate(){
	  return _date; 
  }
  
  /** Clientes **/
  
  /**Method that returns all of the clients
   * 
   * @return all clients
   */
  public Collection<Clients> allClients(){
	  return Collections.unmodifiableCollection(_clients.values()); 
  }
  
  
  /**Method that returns a certain client, given a certain key or id
   * 
   * @param id
   * @return
   * @throws NonClientIdFoundException
   */
  public String getClient(String id) throws NonClientIdFoundException{
	   Clients c= _clients.get(id); 
	   if(c == null) { 
			   throw new NonClientIdFoundException(id); 
	   }
	   return c.toString(); 
  }
  
  /**Method that regists a client given a certain id, name and address
   * 
   * @param id
   * @param name
   * @param morada
   * @throws KeyAlreadyExistsException
   */
  public void registerClient(String id, String name, String morada) throws KeyAlreadyExistsException{ 
	  if(_clients.get(id)!=null) {
		  throw new KeyAlreadyExistsException(id); 
	  }
	  Clients c= new Clients(id,name,morada); 
	  _clients.put(c.getID(), c);
  }
  
  
  

  
  public String showClientTransactions(String idC) throws NonClientIdFoundException{
	  String result=""; 
	  Clients c =_clients.get(idC);
	  if(c== null) {
		  throw new NonClientIdFoundException(idC); 
	  } 
	  for(int i=0; i<c.getTransactions().size(); i++){
		  result=  c.getTransactions().get(i).toString(); 
	   }
	  return result; 
  }
  
  /** Fornecedores **/
 
  /**Method that returns all the suppliers
   * 
   * @return all the suppliers
   */
  public Collection<Suppliers> showSupplier(){
	  return Collections.unmodifiableCollection(_supplier.values()); 
  }
  
  public void registerSupplier(String id, String name, String morada) throws KeyAlreadyExistsException{
	  if(_supplier.get(id)!=null) {
		  throw new KeyAlreadyExistsException(id); 
	  }
	  Suppliers s= new Suppliers(id,name,morada); 
	  _supplier.put(s.getID(),s); 
  }
  
  public boolean ToggleTransactions(String id, String str)throws NonKeyFoundException{
	  Boolean active=true; 
	  Suppliers s= _supplier.get(id); 
	  if(s==null) {
		  throw new NonKeyFoundException(id); 
	  }
	  if(s.getActive().equals(str)){
		  active=true;
	  }else{
		  s.setActive(str); 
		  active =false;
	  }
	  return active; 
  }
  
  public String showSupplierTransactions(String idF) throws NonKeyFoundException{
	  String result=""; 
	  if(_supplier.get(idF) == null) {
		  throw new NonKeyFoundException(idF); 
	  }
	  Suppliers s =_supplier.get(idF); 
	  for(int i=0; i<s.getTransactions().size(); i++){
		  result=  s.getTransactions().get(i).toString(); 
		  _nrTransactions.put(idF,s.getTransactions().size());
	   }
	  return result; 
  }
  
  public int getProductQuantity(String id) {
	  return _products.get(id).getQuantity(); 
  }
  
  public String getMaxTransactions(){
	  Map.Entry<String, Integer> maxEntry = null;
	  for (Map.Entry<String, Integer> entry : _nrTransactions.entrySet()) {
	        if(maxEntry == null || entry.getValue() > maxEntry.getValue() ) {
	        	return _supplier.get(entry.getKey()).toString(); 
	        }
	    }
	  return""; 
  }

  /** Produtos **/
  
  /**Method that return all the products
   * 
   * @return all the products
   */
  public Collection<Products> showAllProducts(){
	  return Collections.unmodifiableCollection(_products.values()); 
  }

  
  
   public void registerBox(String id,int price, int criticalValue, String idF,String service) throws NonKeyFoundException, ServiceNotFoundException{
	  if(_supplier.get(idF) == null) {
		  throw new NonKeyFoundException(idF); 
	  }
      if(!checkType(service)){  
    	  throw new ServiceNotFoundException(service); 
      }
	  Boxs box= new Boxs(id,service, idF,price,criticalValue, 0); 
	  _products.put(box.getID(),box);
	  _typeProduct.put(box.getID(),"BOX"); 
   }
   
   public void registerContainer(String id,int price, int criticalValue, String idF,String service, String serviceQ)throws NonKeyFoundException, ServiceNotFoundException, ServiceQualityNotFoundException{
	   
	   if(_supplier.get(idF) == null) {
			  throw new NonKeyFoundException(idF); 
	   } 
	   if(!checkType(service)){  
	    	  throw new ServiceNotFoundException(service); 
	   }
	   if(!checkQuality(serviceQ)) {
		   throw new ServiceQualityNotFoundException(service); 
	   }
	   Container container = new Container(id,service,serviceQ,idF,price,criticalValue, 0);
	   _products.put(container.getID(),container); 
	   _typeProduct.put(container.getID(),"CONTAINER");  
   }
   
   public void registerBook(String id,  String titulo,String autor, String isbn,String idF, int price,int criticalValue) throws NonKeyFoundException{
	   if(_supplier.get(idF) == null) {
			  throw new NonKeyFoundException(idF); 
	   } 
	   Book book= new Book(id,titulo,autor,isbn,idF,price,criticalValue,0); 
	   _products.put(book.getID(), book);
	   _typeProduct.put(book.getID(),"BOOK"); 
	}
   
   public void changePrice(int price, String id){
	   Products p= _products.get(id);
	   if(price < 0) {
		   p.getPrice(); 
	   }else {
	   p.setPrice(price);
	   }
   }
   
   /** Transacoes **/
   
   public String showTransaction(int id)throws NonKeyFoundException {
	   Transactions tran= _transactions.get(id); 
	   if(tran == null) {
		   throw new NonKeyFoundException(String.valueOf(id)); 
	   }
	   return tran.toString(); 
   }
   
   public void registSale(String idC,String idP,int quantity, int limitDate)throws NonKeyFoundException, NonClientIdFoundException,UnavailableQuantityException {
	   Clients c=  _clients.get(idC);
	   Products p=_products.get(idP);  
	   if(p == null) {
			  throw new NonKeyFoundException(idP); 
	   }  
	   if(c == null) { 
		   throw new NonClientIdFoundException(idC); 
       } 
	   if(p.getQuantity() < quantity) {
		   throw new UnavailableQuantityException(); 
	   }
	   int pay=_products.get(idP).getPrice() * quantity; 
	   int value=valueToPay(_idTrans,idC,idP,limitDate,pay); 
	   Sale sale= new Sale(_idTrans,_date,pay , idC, idP, quantity , value ,limitDate); 
	   _transactions.put(_idTrans,sale);
	   c.getTransactions().add(sale); 
	   c.setCompraEfec(pay); 
	   p.setQuantity(quantity);
	   _idTrans++; 
   }
   
   public int valueToPay(int idT,String idC,String idP,int limitDate, int pay){
	   double value=0; 
	   Clients c=  _clients.get(idC);
	   Products p= _products.get(idP); 
	   int N=0; 
	   int prazo= (limitDate - _date);  
	   if( _typeProduct.get(idP).equals("BOX")){
		   N=5; 
	      if( prazo >= N ){
		         if(c.getState().equals(ClientClassification.NORMAL.name())){
		        	 value=pay*0.9; 
		         }else if(c.getState().equals(ClientClassification.SELECTION.name())){
		        	 value= pay*0.9; 
		         }else if(c.getState().equals(ClientClassification.ELITE.name())){
		        	 value=pay*0.9; 
		         }
	       }else if( prazo >= 0  && prazo <N) {
	    	   if(c.getState().equals(ClientClassification.NORMAL.name())){
		        	 value=pay;  
		         }else if(c.getState().equals(ClientClassification.SELECTION.name())){
		        	 if(limitDate>=2) {
		        		 value=pay*0.95; 
		        	 }else {
		        		 value=pay; 
		        	 }  
		         }else if(c.getState().equals(ClientClassification.ELITE.name())){
		        	 value=pay*0.9; 
		         }
	       }
	      
	   }else if( _typeProduct.get(idP).equals("CONTAINER") ){ 
		   N=8; 
		   if( prazo >= N){
			   if(c.getState().equals(ClientClassification.NORMAL.name())){
		        	 value=pay*0.9; 
		         }else if(c.getState().equals(ClientClassification.SELECTION.name())){
		        	 value= pay*0.9; 
		         }else if(c.getState().equals(ClientClassification.ELITE.name())){
		        	 value=pay*0.9; 
		         }
		   }else if( prazo >= 0  &&  prazo<N) {
	    	   if(c.getState().equals(ClientClassification.NORMAL.name())){
		        	 value=pay;  
		         }else if(c.getState().equals(ClientClassification.SELECTION.name())){
		        	 if(limitDate>=2) {
		        		 value=pay*0.95; 
		        	 }else {
		        		 value=pay; 
		        	 }  
		         }else if(c.getState().equals(ClientClassification.ELITE.name())){
		        	 value=pay*0.9; 
		         }
		   }
	   }else if( _typeProduct.get(idP).equals("BOOK") ){ 
		   N=3; 
		   if( prazo>= N){
			   if(c.getState().equals(ClientClassification.NORMAL.name())){
		        	 value=pay*0.9; 
		         }else if(c.getState().equals(ClientClassification.SELECTION.name())){
		        	 value= pay*0.9; 
		         }else if(c.getState().equals(ClientClassification.ELITE.name())){
		        	 value=pay*0.9; 
		         }
		   }else if( prazo>= 0  && prazo <N) {
	    	   if(c.getState().equals(ClientClassification.NORMAL.name())){
		        	 value=pay;  
		         }else if(c.getState().equals(ClientClassification.SELECTION.name())){
		        	 if(limitDate>=2) {
		        		 value=pay*0.95; 
		        	 }else {
		        		 value=pay; 
		        	 }  
		         }else if(c.getState().equals(ClientClassification.ELITE.name())){
		        	 value=pay*0.9; 
		         }
		   }
	   }
	   return (int) value; 
   }
   
   public void registOrder(String idF, String idP, int quantity, String show)throws NonKeyFoundException, NonClientIdFoundException, NotActiveSupplierException {
	   Products p= _products.get(idP);
	   Suppliers s= _supplier.get(idF); 
	   if(p == null) {
			  throw new NonKeyFoundException(idP); 
	   }
	   if(s == null) { 
		   throw new NonClientIdFoundException(idF); 
       }
	   if(!s.getActive().equals("SIM")) {
		  throw new NotActiveSupplierException(); 
	   }
	   int pay = p.getPrice()*quantity;  
	   if(_transactions.get(_idTrans)==null) {
	 	   Order order= new Order(_idTrans, pay, _date, idF, idP, quantity); 
	 	  _transactions.put(_idTrans, order);
		   order.getProducts(show); 
		   p.setQuantitySup(quantity);
		   s.getTransactions().add(order); 
	   }else{
	    Transactions order=_transactions.get(_idTrans);
	    order.setCost(pay); 
	    order.getProducts(show); 
	    p.setQuantitySup(quantity);
	    s.getTransactions().add(order); 
	  }
   }
   
   public void advanceID() {
	   _idTrans++; 
   }
   
   public void Pay(int id) throws NonKeyFoundException{
	   Transactions tran= _transactions.get(id); 
	   if(tran == null) {
		   throw new NonKeyFoundException(String.valueOf(id)); 
	   }
	   if(tran.TypeTransaction().equals("SALE")) {
		   Clients c= _clients.get(tran.getIDC()); 
		   String idP = tran.getIDP();  
		   int pay = _products.get(idP).getPrice()*tran.getQuantity(); 
		   int valueToPay= valueToPay(id, tran.getIDC(), idP, tran.getLimitDate(), pay); 
		   c.setValuePay(valueToPay);
		   tran.setIsPayed(); 
		   if( tran.getLimitDate() >= _date){
			   c.setPoints(valueToPay*10); 
			    if(c.getPoints() > 25000) {
				   c.setState(ClientClassification.ELITE.name()); 
			   }else if(c.getPoints() > 2000) {
				   c.setState(ClientClassification.SELECTION.name()); 
			   }
		   }else if( tran.getLimitDate() - _date <=15) {
			   if(c.getState().equals(ClientClassification.ELITE.name())) {
				   c.setState(ClientClassification.SELECTION.name()); 
				   c.setPoints((int) (c.getPoints()*0.25)); 
			   }
		   }else if (tran.getLimitDate() - _date <=2) {
			   if(c.getState().equals(ClientClassification.SELECTION.name())) {
				   c.setState(ClientClassification.NORMAL.name()); 
				   c.setPoints((int) (c.getPoints()*0.1)); 
		   }
	   }
   }
   }
   
   
   /** Consultas **/
   
   public String productsPriceLimit(int priceLimit){
	   String result=""; 
	   for(Products p : _products.values()) {
		   if(p.getPrice() <priceLimit && priceLimit>0) {
			   result+=p.toString() + "\n"; 
		   }
	   }
	   return result; 
   }
   
   public String getPayedTrans(String id) throws NonClientIdFoundException{
	   Clients c= _clients.get(id); 
	   String result=""; 
	   if(c == null) { 
			   throw new NonClientIdFoundException(id); 
	   }
	   if(c.getValuePay()!=0) {
	   for(int i=0; i<c.getTransactions().size(); i++){
			  result=  c.getTransactions().get(i).toString(); 
		   }
	   }
		  return result; 
  }
  
  
   
   /**Auxiliares **/
    private boolean checkType(String str){
   	   boolean result=false; 
    	ServiceLevel [] check  = ServiceLevel.values();
      	if(check[0].toString().equals(str)) {
   		   result=true; 
    	}else if(check[1].toString().equals(str)){
   		   result=true; 
        }else if(check[2].toString().equals(str)){
   		   result=true; 
        }else if(check[3].toString().equals(str)){
   		   result=true; 
       }
   	return result; 
   }
    
   private boolean checkQuality(String str) {
	   boolean result=false; 
   	ServiceQuality [] check  = ServiceQuality.values();
     	if(check[0].toString().equals(str)) {
  		   result=true; 
   	    }else if(check[1].toString().equals(str)){
  		   result=true; 
        }else if(check[2].toString().equals(str)){
  		   result=true; 
        }else if(check[3].toString().equals(str)){
  		   result=true; 		   
        }
  	return result; 
   }
   
   
   
}
