package cf.cvb14795.cart.service;

import java.util.Map;

import cf.cvb14795.cart.model.BookBean;
	

public interface BookService {
	// 依bookId來查詢單筆記錄
	BookBean getBook(Long bookId);
	
	Map<Long, BookBean> getAllBooks();
}
