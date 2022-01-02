package com.wildcodeschool.librarywildcodeschool.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wildcodeschool.librarywildcodeschool.entity.Book;

@Repository
public interface BookRepository extends JpaRepository <Book, Integer> {

	// custom query to search to blog post by title or content
    List<Book> findByTitleContainingOrDescriptionContaining(String text, String textAgain);
    
}
