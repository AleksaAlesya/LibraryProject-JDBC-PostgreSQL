package by.aleksabrakor.springcourse.dao;

import by.aleksabrakor.springcourse.models.Book;
import by.aleksabrakor.springcourse.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDao implements DaoCRUD<Book> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query(
                "SELECT *FROM Book",
                new BeanPropertyRowMapper<>(Book.class));
    }

    public Book getById(int bookId) {
        return jdbcTemplate.query(
                "SELECT *FROM Book  WHERE id=?",
                new Object[]{bookId},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public Person getPersonByBookId(int id) {
        return jdbcTemplate.query(
                "SELECT * FROM Book JOIN Person ON Book.person_id = Person.id where Book.id =?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

//    //2-вариант реализации
//    public Optional<Person> getBookQwner(int id) {
//        return jdbcTemplate.query(
//                "SELECT * FROM Book JOIN Person ON Book.person_id = Person.id where Book.id =?",
//                new Object[]{id},
//                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
//    }

    public void save(Book book) {

        jdbcTemplate.update(
                "INSERT INTO Book (title, author, year) VALUES(?,?,?)",
                book.getTitle(),
                book.getAuthor(),
                book.getYear()
        );
    }

    public void update(Book bookUpdate, int id) {
        jdbcTemplate.update(
                "UPDATE Book SET title=?, author =?, year=? WHERE id=?",
                bookUpdate.getTitle(),
                bookUpdate.getAuthor(),
                bookUpdate.getYear(),
                id);
    }

    public void deleteById(int id) {
        jdbcTemplate.update(
                "DELETE FROM Book WHERE id = ?",
                id);
    }

    public void assignBook(int personId, int bookId) {
        jdbcTemplate.update(
                "UPDATE Book SET person_id = ? WHERE id=?",
                personId,
                bookId);
    }

    public void releaseBook(int bookId) {
        jdbcTemplate.update(
                "UPDATE Book SET person_id = ? WHERE id=?",
               null,
                bookId);
    }
}
