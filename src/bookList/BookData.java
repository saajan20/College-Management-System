package bookList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BookData {
    private final StringProperty title;
    private final StringProperty id;
    private final StringProperty author;
    private final StringProperty publisher;
    private final BooleanProperty availability;


    public BookData(String id, String title, String author, String publisher, Boolean availability) {
        this.title = new SimpleStringProperty(title);
        this.id = new SimpleStringProperty(id);
        this.author= new SimpleStringProperty(author);
        this.publisher = new SimpleStringProperty(publisher);
        this.availability = new SimpleBooleanProperty(availability);
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public void setPublisher(String publisher) {
        this.publisher.set(publisher);
    }

    public void setAvailability(boolean availability) {
        this.availability.set(availability);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public String getAuthor() {
        return author.get();
    }

    public StringProperty authorProperty() {
        return author;
    }

    public String getPublisher() {
        return publisher.get();
    }

    public StringProperty publisherProperty() {
        return publisher;
    }

    public boolean getAvailability() {
        return availability.get();
    }

    public BooleanProperty availabilityProperty() {
        return availability;
    }
}
