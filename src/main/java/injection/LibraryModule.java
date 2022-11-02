package injection;

import com.google.inject.AbstractModule;
import factory.BooksFactory;
import factory.FileBooksFactory;
import org.jetbrains.annotations.NotNull;

public class LibraryModule extends AbstractModule {
    private static String path;

    public LibraryModule(@NotNull String path) {
        this.path = path;
    }

    @Override
    protected void configure() {
        bind(BooksFactory.class).toInstance(new FileBooksFactory(path));

    }
}
