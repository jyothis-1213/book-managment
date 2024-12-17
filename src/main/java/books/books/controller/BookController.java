package books.books.controller;

import books.books.model.Books;
import books.books.repository.BookRepository;
import books.books.service.BookService;
import books.books.service.GoogleBooksService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/books")
public class BookController {
	 @Autowired
	  private BookRepository bookRepository;
    
	@Autowired
    private BookService bookService;

   
    @PostMapping
    public ResponseEntity<Books> createBook(@RequestBody Books book) {
        Books createdBook = bookService.createBook(book);
        //book.setId("B-" + UUID.randomUUID().toString().substring(0, 8));
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Books>> getAllBooks() {
        List<Books> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    //Object reviews = GoogleBooksService.fetchReviewsForBook(book.getIsbn());

    @GetMapping("/{id}")
    public ResponseEntity<Books> getBookById(@PathVariable String id) {
        Optional<Books> book = bookService.getBookById(id);
        if (book.isPresent()) {
            return new ResponseEntity<>(book.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

  
    @PutMapping("/{id}")
    public ResponseEntity<Books> updateBook(@PathVariable String id, @RequestBody Books bookDetails) {
        Books updatedBook = bookService.updateBook(id, bookDetails);
        if (updatedBook != null) {
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        Optional<Books> book = bookService.getBookById(id);
        if (book.isPresent()) {
            bookService.deleteBook(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
   }
