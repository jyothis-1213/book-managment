package books.books.service;

import books.books.model.Books;
import books.books.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {
	
	
	@Autowired
	private final BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public Books createBook(Books book) {
		return bookRepository.save(book);
	}

	public List<Books> getAllBooks() {
		return bookRepository.findAll();
	}

	public Optional<Books> getBookById(String id) {
		return bookRepository.findById(id);
	}

	public Books updateBook(String id, Books bookDetails) {
		if (bookRepository.existsById(id)) {
			bookDetails.setId(id);
			return bookRepository.save(bookDetails);
		}
		return null;
	}

	public void deleteBook(String id) {
		bookRepository.deleteById(id);
	}

	
    
}
