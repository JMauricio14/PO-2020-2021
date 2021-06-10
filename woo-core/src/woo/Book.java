package woo; 

/**
* Class Store implements the Books.
*/
public class Book extends Products{

	private String _titulo; 
	private String _autor; 
	private String _isbn; 
	
	
	public Book(String id,  String titulo,String autor, String isbn,String idF, int price,int criticalValue, int quantity) {
		super(id, idF, criticalValue, price,quantity); 
		this._titulo=titulo;
		this._autor=autor; 
		this._isbn=isbn; 
	}
	
	/**Method that converts a Book to a String
	 * 
	 * @return the id
	 */
	@Override
	public String toString() {
		return "BOOK" + "|" + super.toString() + _titulo + "|" + _autor + "|" + _isbn; 
	}
	
}
