import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import controller.Library;
import exception.LibrarySizeException;
import factory.BooksFactory;
import injection.LibraryModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class InjectionTest {
    @Inject
    private BooksFactory booksFactory;

    @BeforeEach
    public void beforeEachTesting(){
        final Injector injector = Guice.createInjector(new LibraryModule("./src/main/resources/books.txt"));
        injector.injectMembers(this);
    }
    @Test
    public void createLibraryTest() throws LibrarySizeException {
        assertThrows(LibrarySizeException.class, () -> new Library(1, booksFactory));
    }
}
