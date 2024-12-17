package books.books.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import books.books.model.Books;
import books.books.repository.BookRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GoogleBooksService {

    @Value("${google.books.api.key}")
    private String apiKey;

    @Autowired
    private BookRepository bookRepository;

    public String fetchAndUpdateDescription(String isbn) {
        String apiUrl = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbn + "&key=" + apiKey;
        RestTemplate restTemplate = new RestTemplate();

        try {
            // Fetch description from Google Books API
            ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.getBody());

            JsonNode items = rootNode.get("items");
            if (items != null && items.isArray() && items.size() > 0) {
                JsonNode volumeInfo = items.get(0).get("volumeInfo");
                if (volumeInfo != null && volumeInfo.has("description")) {
                    String description = volumeInfo.get("description").asText();

                    // Update the book in the database
                    Books book = bookRepository.findByIsbn(isbn);
                    if (book != null) {
                        book.setDescription(description);
                        bookRepository.save(book);
                    }

                    return description;
                }
            }
        } catch (Exception e) {
            System.err.println("Error fetching description: " + e.getMessage());
        }

        // Return a default message or null if description is not found
        return "Description not available";
    }
}
