package factory;

import com.google.inject.Inject;
import controller.Library;
import exception.LibrarySizeException;

public class LibFactory {
    private static Library library;
    @Inject
    private BooksFactory fileBooksFactory;

    public Library create(int capacity) throws LibrarySizeException {
        library = new Library(capacity, fileBooksFactory);
        return library;
    }
}
