package books.books.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Genre {
    FICTION("Fiction"),
    NON_FICTION("Non-Fiction"),
    MYSTERY("Mystery"),
    FANTASY("Fantasy"),
    ROMANCE("Romance"),
    SCI_FI("Sci-Fi"),
    OTHERS("Others");

    private final String displayName;

    Genre(String displayName) {
        this.displayName = displayName;
    }

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }

    @JsonCreator
    public static Genre fromDisplayName(String displayName) {
        for (Genre genre : Genre.values()) {
            if (genre.getDisplayName().equalsIgnoreCase(displayName)) {
                return genre;
            }
        }
        throw new IllegalArgumentException("Invalid genre: " + displayName);
    }
}
