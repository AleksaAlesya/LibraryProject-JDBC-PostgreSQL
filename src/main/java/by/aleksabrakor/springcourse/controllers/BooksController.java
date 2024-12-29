package by.aleksabrakor.springcourse.controllers;


import by.aleksabrakor.springcourse.dao.BookDao;
import by.aleksabrakor.springcourse.dao.PersonDao;
import by.aleksabrakor.springcourse.models.Book;
import by.aleksabrakor.springcourse.models.Person;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController implements ControllerI<Book> {
    private final BookDao bookDao;
    private final PersonDao personDao;

    @Autowired
    public BooksController(BookDao personDao, PersonDao personDao1) {
        this.bookDao = personDao;
        this.personDao = personDao1;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("books", bookDao.index());
        return "books/index";
    }

        @GetMapping("/{id}")
    public String findById(@NotNull @PathVariable("id") int id,
                           Model model, @ModelAttribute("personNew") Person person) {
        model.addAttribute("people",personDao.index());
        model.addAttribute("book", bookDao.getById(id));
        model.addAttribute("person", bookDao.getPersonByBookId(id));
        return "books/show";
    }

//    //2-й вариант реализации
//    @GetMapping("/{id}")
//    public String findById(@NotNull @PathVariable("id") int id,
//                           Model model, @ModelAttribute("personNew") Person person) {
//        model.addAttribute("book", bookDao.getById(id));
//
//        Optional<Person> bookOwner = bookDao.getBookQwner(id);
//        if (bookOwner.isPresent())
//            model.addAttribute("person", bookOwner.get());
//        else
//            model.addAttribute("people", personDao.index());
//        return "books/show";
//    }

    @GetMapping("/new")
    public String newForCreate(Model model) {
        model.addAttribute("book", new Book());
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "books/new";
        // в модель уже запишется созданный book c полями
        bookDao.save(book);
        return ("redirect:/books");
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model,
                       @PathVariable("id") int id) {
        model.addAttribute("book", bookDao.getById(id));
        return ("books/edit");
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if (bindingResult.hasErrors())
            return "books/edit";
        bookDao.update(book, id);
        return ("redirect:/books");
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDao.deleteById(id);
        return ("redirect:/books");
    }

    /*   На странице книги, если книга свободна, должен быть выпадающий список (<select>)
       со всеми людьми и кнопка "Назначить книгу". Эта кнопка нажимается библиотекарем
       тогда, когда читатель хочет забрать эту книгу домой. После нажатия на эту кнопку, книга
       должна начать принадлежать выбранному человеку и должна появится в его списке
       книг.
       */
    @PatchMapping("/appoint/{id}")
    public String assignBook(@ModelAttribute("person") Person person, @PathVariable("id") int bookId) {
        bookDao.assignBook(person.getId(), bookId);

        return ("redirect:/books/" + bookId);
    }

    /*На странице книги, если книга взята человеком, рядом с его именем должна быть кнопка
"Освободить книгу". Эта кнопка нажимается библиотекарем тогда, когда читатель
возвращает эту книгу обратно в библиотеку. После нажатия на эту кнопку книга снова
становится свободно и пропадает из списка книг человека.*/
    @PatchMapping("/releaseBook{id}")
    public String releaseBook(@PathVariable("id") int bookId) {
        bookDao.releaseBook(bookId);
        return ("redirect:/books/" + bookId);
    }
}
