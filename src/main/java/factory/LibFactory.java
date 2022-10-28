package factory;

import controller.Library;

public class LibFactory {
    private static Library library;
    private FileBooksFactory fileBooksFactory;

    public Library create(int capacity){
        fileBooksFactory = new FileBooksFactory("./src/main/resources/books.txt");
        library = new Library(capacity, fileBooksFactory);
        return library;
    }
}
