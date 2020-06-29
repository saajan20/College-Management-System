package memberlist;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class memberData {
    private final StringProperty name;
    private final StringProperty id;
    private final StringProperty mobile;
    private final StringProperty email;

    public memberData(String id, String name, String mobile, String email) {
        this.name = new SimpleStringProperty(name);
        this.id = new SimpleStringProperty(id);
        this.mobile= new SimpleStringProperty(mobile);
        this.email = new SimpleStringProperty(email);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getMobile() {
        return mobile.get();
    }

    public StringProperty mobileProperty() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile.set(mobile);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
}
