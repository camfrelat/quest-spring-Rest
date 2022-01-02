package com.wildcodeschool.librarywildcodeschool.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.wildcodeschool.librarywildcodeschool.entity.Book;
import com.wildcodeschool.librarywildcodeschool.repository.BookRepository;

import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

	
	@Autowired
    BookRepository bookRepository;

	//find all books
    @GetMapping("/books")
    public List<Book> index(){
        return bookRepository.findAll();
    }

    //find one book by its id
    @GetMapping("/books/{id}")
    public Book show(@PathVariable int id){
        return bookRepository.findById(id).get();
    }

    
    @PostMapping("/books/search")
    public List<Book> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return bookRepository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }

    //create a new book and save in database
    @PostMapping("/books")
    public Book create(@RequestBody Book book){
        return bookRepository.save(book);
    }

    //update elements of a book and save them in database
    @PutMapping("/books/{id}")
    public Book update(@PathVariable int id, @RequestBody Book book){
        // getting book
        Book bookToUpdate = bookRepository.findById(id).get();
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setDescription(book.getDescription());
        return bookRepository.save(bookToUpdate);
    }

    // delete a book from database
    @DeleteMapping("books/{id}")
    public boolean delete(@PathVariable int id){
        bookRepository.deleteById(id);
        return true;
    }
}
