import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import controller.Library;
import entity.Author;
import entity.Book;
import exception.EmptyCellException;
import exception.LibrarySizeException;
import exception.NoEmptyPlaceException;
import factory.BooksFactory;
import injection.LibraryModule;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public final class LibraryTest {
    private @NotNull Library library;
    @Inject
    private @NotNull static BooksFactory booksFactory;
    private static List<Book> books = new ArrayList<>();
    @BeforeAll
    static void init(){
        booksFactory = Mockito.mock(BooksFactory.class);

        books.add(new Book("book 0", new Author("author 0")));
        books.add(new Book("book 1", new Author("author 1")));
        books.add(new Book("book 2", new Author("author 2")));
        books.add(new Book("book 3", new Author("author 3")));
        books.add(new Book("book 4", new Author("author 4")));

        Mockito.when(booksFactory.books()).thenReturn(books);
    }

    @BeforeEach
    public void beforeEachTesting() throws LibrarySizeException {
        final Injector injector = Guice.createInjector(new LibraryModule("./src/main/resources/books.txt"));
        injector.injectMembers(this);

        library = new Library(books.size() + 1, booksFactory);
    }
    @Test
    public void createLibraryTest() throws LibrarySizeException {
        assertThrows(LibrarySizeException.class, () -> new Library(1, booksFactory));
    }
    @Test
    public void booksInOrderTest() {
        List<Book> booksFromLibrary = library.getBooks();

        for (int i = 0; i < books.size(); i++){
            assertEquals(booksFromLibrary.get(i), books.get(i));
        }
        for (int i = books.size(); i < library.getCount(); i++){
            assertNull(booksFromLibrary.get(i));
        }
    }
    @Test
    public void getBookTest() throws EmptyCellException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        Book book = library.getBook(0);

        String out = "Cell: 0\n" + book.getName() + "\n" + book.getAuthor().getName();
        assertEquals(out, outputStream.toString().trim());
    }
    @Test
    public void takeFromEmptyCellTest() throws EmptyCellException {
        assertThrows(EmptyCellException.class, () -> library.getBook(books.size() + 1));
    }

    @Test
    public void takeSameBookTest() throws EmptyCellException {
        Book book = library.getBooks().get(0);
        assertEquals(book, library.getBook(0));
    }
    @Test
    public void addBookTest() throws NoEmptyPlaceException, EmptyCellException {
        library.getBook(1);
        library.getBook(4);
        Book newBook = new Book("new book", new Author("new author"));
        library.addBook(newBook);
        assertNotNull(library.getBook(1));
    }
    @Test
    public void noEmptyPlacesTest() throws NoEmptyPlaceException {
        library.addBook(new Book("book 5", new Author("author 5")));
        assertThrows(NoEmptyPlaceException.class, () -> library.addBook(new Book("book 6", new Author("author 6"))));
    }
    @Test
    public void printTest(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        library.print();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Book> booksFromLibrary = library.getBooks();
        String out = gson.toJson(booksFromLibrary);
        assertEquals(out.trim(), outputStream.toString().trim());
    }
}
