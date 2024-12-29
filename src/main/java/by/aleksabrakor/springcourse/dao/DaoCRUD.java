package by.aleksabrakor.springcourse.dao;

import java.util.List;

public interface DaoCRUD<E> {

    List<E> index();

    public E getById(int id);

    public void save(E e);

    public void update(E eUpdate, int id);

    public void deleteById(int id);

}
