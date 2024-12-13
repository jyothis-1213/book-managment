
package com.example.bookmanagement.repository;

import com.example.bookmanagement.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
}
