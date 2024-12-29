package by.aleksabrakor.springcourse.models;

import jakarta.validation.constraints.*;

public class Person {
    private int id;

    @NotEmpty(message = "ФИО не должно быть пустым")
    @Size(min = 2, max = 100, message = "Поле не должно быть меньше 2 и больше 30 символов")
//    @Pattern(regexp = "[A-Z, А-Я]\\w+, [A-Z, А-Я]\\w+, [A-Z, А-Я]\\w+", message = " Фио должно быть в формате: Иванов Иван Иванович")
    private String fio;

    @NotNull(message = "Год рождения не доллжен быть пустым")
    @Min(value = 1900, message = "Год рождения не должен быть больше 1900")
    @Max(value = 2100, message = "WOW!!!")
    private int yearOfBirth;

    public Person(String fio, int age) {
        this.fio = fio;
        this.yearOfBirth = age;
    }

    public Person() {
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
