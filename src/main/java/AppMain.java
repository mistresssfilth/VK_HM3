import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import controller.Library;
import factory.LibFactory;
import injection.LibraryModule;

public class AppMain {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new LibraryModule("./src/main/resources/books.txt"));
        Library library = injector.getInstance(LibFactory.class).create(1000);
        library.print();
    }
}
