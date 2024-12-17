package books.books.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.UUID;

@Document(collection = "books")
public class Books {

    @Id
    private String id;

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    private String title;

    @NotBlank(message = "Author is required")
    @Size(max = 50, message = "Author cannot exceed 50 characters")
    private String author;

    @NotBlank(message = "Publication date is required")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Publication date must be in the format YYYY-MM-DD")
    private String publicationDate;

    @NotBlank(message = "ISBN is required")
    @Pattern(regexp = "^(\\d{10}|\\d{13})(,\\s*(\\d{10}|\\d{13}))*$", message = "ISBN must be a comma-separated list of 10 or 13-digit numbers")
    private String isbn;

    @NotNull(message = "Genre is required")
    private Genre genre;

    @Min(1)
    @Max(5)
    private Double rating;

   
    
    private String Description;

    public Books() {
        this.id = "B-" + UUID.randomUUID().toString().substring(0, 8); 
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

   

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		this.Description = description;
	}
}
