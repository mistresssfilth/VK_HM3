import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import controller.Library;
import exception.LibrarySizeException;
import factory.LibFactory;
import injection.LibraryModule;

import java.util.Scanner;

public class AppMain {
    public static void main(String[] args) throws LibrarySizeException {
        Scanner scanner = new Scanner(System.in);
        int capacity = scanner.nextInt();

        Injector injector = Guice.createInjector(new LibraryModule("./src/main/resources/books.txt"));
        Library library = injector.getInstance(LibFactory.class).create(capacity);
        library.print();
    }
}
