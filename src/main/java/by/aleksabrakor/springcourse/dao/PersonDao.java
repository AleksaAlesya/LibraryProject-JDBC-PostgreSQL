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
public class PersonDao implements DaoCRUD<Person>{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query(
                "SELECT *FROM person",
                new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getById(int personId) {
//        SELECT *FROM PERSON where id= personId;
        return jdbcTemplate.query(
                "SELECT *FROM person WHERE id=?",
                new Object[]{personId},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update(
                "INSERT INTO Person (fio, yearOfBirth) VALUES(?,?)",
                person.getFio(),
                person.getYearOfBirth()
        );
    }

    public void update(Person personUpdate, int id) {
        jdbcTemplate.update(
                "UPDATE Person SET fio=?, yearOfBirth=? WHERE id=?",
                personUpdate.getFio(),
                personUpdate.getYearOfBirth(),
                id);
    }

    public void deleteById(int id) {
        jdbcTemplate.update(
                "DELETE FROM Person WHERE id = ?",
                id);
    }

    //Для валидации уникальности имени
    public Optional<Person> findByFio(String fio, int id) {
        return jdbcTemplate.query(
                "SELECT *FROM Person WHERE fio = ? and id !=?",
                new Object[]{fio, id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public List<Book> getBooksByPersonId(int personId) {
        return jdbcTemplate.query(
                "SELECT *FROM Book WHERE person_id=?",
                new Object[]{personId},
                new BeanPropertyRowMapper<>(Book.class));
    }
}
