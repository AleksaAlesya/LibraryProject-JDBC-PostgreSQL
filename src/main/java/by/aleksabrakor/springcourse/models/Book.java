package by.aleksabrakor.springcourse.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Book {
    @NotNull
    private int id;

    @NotEmpty(message = "Поле  не должно быть пустым")
    @Size(min = 2, max = 200, message = "Поле не должно быть меньше 2 и больше 30 символов")
    private String title;

    @Size (max = 50, message = "Поле не должно быть больше 50 символов")
    private String author;

    @NotNull(message = "Поле не доллжен быть пустым")
    private int year;

    public Book() {
    }

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
