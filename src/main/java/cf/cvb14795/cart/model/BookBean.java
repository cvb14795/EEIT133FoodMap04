package cf.cvb14795.cart.model;

public class BookBean {
	Integer bookId;
	String author;
	String title;
	Double price;
	
	public BookBean() {
	}
	
	public BookBean(Integer bookId, String author, String title, Double price) {
		this.bookId = bookId;
		this.author = author;
		this.title = title;
		this.price = price;
	}
	
	public BookBean(String author, String title, Double price) {
		this.author = author;
		this.title = title;
		this.price = price;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	@Override
	public String toString() {
		return "BookBean [author=" + author + ", title=" + title + ", price=" + price + "]";
	}
}
