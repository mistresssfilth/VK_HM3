package factory;

import com.google.inject.Inject;
import controller.Library;

public class LibFactory {
    private static Library library;
    @Inject
    private BooksFactory fileBooksFactory;

    public Library create(int capacity){
        library = new Library(capacity, fileBooksFactory);
        return library;
    }
}
