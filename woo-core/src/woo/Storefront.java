package woo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.List;

import woo.exceptions.NotActiveSupplierException; 
import woo.exceptions.UnavailableQuantityException; 
import woo.exceptions.NonKeyFoundException; 
import woo.exceptions.ServiceQualityNotFoundException;
import woo.exceptions.BadEntryException;
import woo.exceptions.ImportFileException;
import woo.exceptions.MissingFileAssociationException;
import woo.exceptions.UnavailableFileException;
import woo.exceptions.NonClientIdFoundException; 
import woo.exceptions.KeyAlreadyExistsException;
import woo.exceptions.ServiceNotFoundException; 
//FIXME import classes (cannot import from pt.tecnico or woo.app)

/**
 * Storefront: façade for the core classes.
 */
public class Storefront {

  /** Current filename. */
  private String _filename;

  /** The actual store. */
  private Store _store = new Store();
  
  /** Boolean that verifies if the file is save**/
  private boolean _save=false; 
  //FIXME define other attributes
  //FIXME define constructor(s)
  //FIXME define other methods

  /**
   * Default constructor
   */
  public Storefront() {
	  //empty on propose
  }
  
  
  /**
   * @throws IOException
   * @throws FileNotFoundException
   * @throws MissingFileAssociationException
   */
  public void save() throws IOException, FileNotFoundException, MissingFileAssociationException {
	    ObjectOutputStream toSave= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(_filename))); 
	    toSave.writeObject(_store); 
	    toSave.close();
	    _save=false; 
  }

  /**
   * @param filename
   * @throws MissingFileAssociationException
   * @throws IOException
   * @throws FileNotFoundException
   */
  public void saveAs(String filename) throws MissingFileAssociationException, FileNotFoundException, IOException {
    _filename = filename;
    save();
  }

  /**
   * @param filename
   * @throws UnavailableFileException
   */
  public void load(String filename) throws UnavailableFileException, FileNotFoundException, IOException, ClassNotFoundException{
    ObjectInputStream toLoad= new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename))); 
    _store = (Store) toLoad.readObject(); 
     toLoad.close();
    _filename= filename;
  }

  /**
   * @param textfile
   * @throws ImportFileException
   */
  public void importFile(String textfile) throws ImportFileException {
    try {
      _store.importFile(textfile);
    } catch (IOException | BadEntryException  e) {
      throw new ImportFileException(textfile);
    }
    _save=true; 
  }
  
  /** Files **/
  
  public String getFilename() {
	    return _filename;
  }
  
  public void setFilename(String name) {
	    _filename = name;
 }
	  
  /** Data **/
  
  public int currentDate(){
	  return _store.getDate(); 
  }
  
  public void addDate(int data){
	  _store.advanceDate(data); 
	  _save=true; 
  }
  
  /** Fornecedores **/
  
  public Collection<Suppliers> showSupplier(){
	  return _store.showSupplier(); 
  }
  
  public void registerSupplier(String id, String name, String morada) throws KeyAlreadyExistsException{
	  _store.registerSupplier(id,name,morada); 
  }
  
  public boolean ToggleTransactions(String id, String str) throws NonKeyFoundException{
	  return _store.ToggleTransactions(id,str);
  }
  
  public String showSupplierTransactions(String idF) throws NonKeyFoundException{
	  return _store.showSupplierTransactions(idF); 
  }
  
  /** Clientes **/
  
  public Collection<Clients> showAllClients() {
	  return _store.allClients(); 
   }
  
  public void registerClient(String id, String name, String morada) throws KeyAlreadyExistsException{
	  _store.registerClient(id,name,morada); 
  }
  
  public String getClient(String id) throws NonClientIdFoundException {
	  return  _store.getClient(id); 
  }
  
  
  public String showClientTransactions(String idC) throws NonClientIdFoundException{
	  return _store.showClientTransactions(idC); 
  }
  
  /** Produtos **/
  
  public Collection<Products> showAllProducts(){
	  return _store.showAllProducts(); 
  }
  
  public void registerBox(String id,int price, int criticalValue, String idF,String service) throws NonKeyFoundException, ServiceNotFoundException{
	  _store.registerBox(id,price,criticalValue,idF, service); 
  }
  
  public void registerContainer(String id,int price, int criticalValue, String idF,String service, String serviceQ)throws NonKeyFoundException, ServiceNotFoundException, ServiceQualityNotFoundException{
	  _store.registerContainer(id,price,criticalValue,idF,service,serviceQ); 
  }
  
  public void registerBook(String id,  String titulo,String autor, String isbn,String idF, int price,int criticalValue) throws NonKeyFoundException{
	  _store.registerBook(id,titulo,autor,isbn,idF,price,criticalValue); 
  }
  
  public void changePrice(int price, String id) {
	  _store.changePrice(price, id); 
	  _save=true; 
  }
  
  public int getProductQuantity(String id) {
	  return _store.getProductQuantity(id); 
  }
  
  public String getMaxTransactions(){
	  return _store.getMaxTransactions(); 
  }
  /** Transacoes**/
  
  public String showTransaction(int id)throws NonKeyFoundException {
	 return _store.showTransaction(id); 
  }
  
  public void registSale(String idC,String idP,int quantity, int limitDate)throws NonKeyFoundException, NonClientIdFoundException, UnavailableQuantityException{
	  _store.registSale(idC,idP,quantity,limitDate); 
  }
  
  public void registOrder(String idF, String idP, int quantity,String show)throws NonKeyFoundException, NonClientIdFoundException, NotActiveSupplierException {
	  _store.registOrder(idF,idP,quantity,show); 
  }
  
  public void advanceID() {
	   _store.advanceID(); 
  }
  
  public void Pay(int id) throws NonKeyFoundException{
	  _store.Pay(id); 
  }

  /** Consultas **/
  
  public String productsPriceLimit(int priceLimit){
	  return _store.productsPriceLimit(priceLimit); 
  }
  
  public String getPayedTrans(String id) throws NonClientIdFoundException{
	  return _store.getPayedTrans(id); 
  }
  
}
