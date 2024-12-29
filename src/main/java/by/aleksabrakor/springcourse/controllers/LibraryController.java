package by.aleksabrakor.springcourse.controllers;

import by.aleksabrakor.springcourse.dao.BookDao;
import by.aleksabrakor.springcourse.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping
public class LibraryController {
    private final BookDao bookDao;
    private final PersonDao personDao;

    @Autowired
    public LibraryController(BookDao bookDao, PersonDao personDao) {
        this.bookDao = bookDao;
        this.personDao = personDao;
    }

    @GetMapping
    public  String index(Model model){
        model.addAttribute("books",bookDao.index());
        model.addAttribute(("people"), personDao.index());
        return ("library/index");
    }
}