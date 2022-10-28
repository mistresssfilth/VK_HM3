import controller.Library;
import factory.LibFactory;

public class AppMain {
    public static void main(String[] args) {
        LibFactory libFactory = new LibFactory();
        Library library = libFactory.create(3);
        library.print();
    }
}
