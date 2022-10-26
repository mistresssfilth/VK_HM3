package factory;

import entity.Book;

import java.util.Collection;
import java.util.Collections;

public interface BooksFactory {
    Collection<Book> books();
}
