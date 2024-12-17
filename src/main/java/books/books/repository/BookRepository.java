package books.books.repository;

import books.books.model.Books;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Books, String> {
	  Books findByIsbn(String isbn);
}
