package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.source.tree.LambdaExpressionTree;
import entity.Book;
import exception.EmptyCellException;
import exception.LibrarySizeException;
import exception.NoEmptyPlaceException;
import factory.BooksFactory;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private int count;
    private BooksFactory booksFactory;
    private List<Book> books = new ArrayList<>();

    public Library(int count, BooksFactory booksFactory) throws LibrarySizeException {
        this.count = count;
        this.booksFactory = booksFactory;
        if (count >= booksFactory.books().size()) {
            for (int i = 0; i < booksFactory.books().size(); i++){
                books.add(booksFactory.books().stream().toList().get(i));
            }
            for (int i = booksFactory.books().size(); i < count; i++){
                books.add(null);
            }
        }
        else
            throw new LibrarySizeException("Library size is small");
    }

    public Book getBook(int id) throws EmptyCellException {
        Book book = null;
        try {
            book = books.get(id);
            System.out.println("Cell: " + id + "\n" + book.getName() + "\n" + book.getAuthor().getName());
            books.set(id, null);
            return book;
        } catch (Exception e) {
            throw new EmptyCellException("This cell is empty");
        }
    }
    public void addBook(Book book) throws NoEmptyPlaceException {
        if (isEmptyPlace())
            for(int i = 0; i < books.size(); i++){
                if (books.get(i) == null) {
                    books.set(i, book);
                    break;
                }
            }
        else
            throw new NoEmptyPlaceException("No empty places");
    }
    public void print(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(books));
    }
    private boolean isEmptyPlace(){
        for (Book book : books)
        {
            if (book == null)
                return true;
        }
        return false;
    }

    public int getCount() {
        return count;
    }
    public List<Book> getBooks(){
        return books;
    }
}
