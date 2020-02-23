package birski.bookstore.exceptions;

public class NameExceptionResponse {

    String name;

    public NameExceptionResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
