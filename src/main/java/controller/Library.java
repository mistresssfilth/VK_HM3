package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Book;
import factory.BooksFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Library {
    private int count;
    private BooksFactory booksFactory;
    private List<Book> books;
    public Library(int count, BooksFactory booksFactory) {
        this.count = count;
        this.booksFactory = booksFactory;
        books = new ArrayList<>(booksFactory.books());
    }

    public Book getBook(int id){
        return books.get(id);
    }
    public void addBook(Book book){
        for(int i = 0; i < books.size(); i++){
            if (books.get(i) == null)
                books.set(i, book);
        }
    }
    public void print(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(books));
    }
}
