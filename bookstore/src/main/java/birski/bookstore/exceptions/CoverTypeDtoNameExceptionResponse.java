package birski.bookstore.exceptions;

public class CoverTypeDtoNameExceptionResponse {

    private String name;

    public CoverTypeDtoNameExceptionResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
