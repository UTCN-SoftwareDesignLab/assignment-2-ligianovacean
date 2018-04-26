package bookStore.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class BookDto {
    @Size(min = 1)
    public String title;
    @Size(min = 1)
    public String genre;
    @Min(value = 0, message = "Price cannot be a negative number!")
    public int quantity;
    @Min(0)
    public int price;
    public int authorId;
}
