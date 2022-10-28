package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Book;
import factory.BooksFactory;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private int count;
    private BooksFactory booksFactory;
    private List<Book> books;

    public Library(int count, BooksFactory booksFactory) {
        this.count = count;
        this.booksFactory = booksFactory;
        if (booksFactory.books().size() <= count)
            books = new ArrayList<>(booksFactory.books());
        else
            throw new RuntimeException("Not enough cells to fill");
    }

    public void getBook(int id){
        Book book = books.get(id);
        if (book != null) {
            System.out.println("Cell: " + id + "\n" + book.getName() + "\n" + book.getAuthor().getName());
            books.remove(id);
        }
        else
            throw new RuntimeException("Book is not exist");
    }
    public void addBook(Book book){
        if (isEmptyPlace())
            for(int i = 0; i < books.size(); i++){
                if (books.get(i) == null) {
                    books.set(i, book);
                    break;
                }
            }
        else
            throw new RuntimeException("No empty place");
    }
    public void print(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(books));
    }
    private boolean isEmptyPlace(){
        for (Book book : books)
            return (book == null);
        return false;
    }
}
