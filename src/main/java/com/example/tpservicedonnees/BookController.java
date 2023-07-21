package com.example.tpservicedonnees;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.*;
/*@RestController
@RequestMapping("/api")
public class ControllerBook {

    @GetMapping("/endpoint")
    public String endpointExemple() {
        return "Bonjour depuis l'API Spring Boot !";
    }
}
package com.example.demo.controller;

        import java.util.ArrayList;
        import java.util.List;

        import org.springframework.web.bind.annotation.*;

        import com.example.demo.model.Book;*/

@RestController
@RequestMapping("/api/books")
public class BookController {
    private List<Book> books = new ArrayList<>();

    // Endpoint pour récupérer tous les livres
    @GetMapping
    public List<Book> getAllBooks() {

        return books;
    }

    // Endpoint pour récupérer un livre par son ID
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Endpoint pour ajouter un livre
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        books.add(book);
        return book;
    }

    // Endpoint pour mettre à jour un livre
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Book book = books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (book != null) {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setIsbn(updatedBook.getIsbn());
        }

        return book;
    }

    // Endpoint pour supprimer un livre
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        books.removeIf(book -> book.getId().equals(id));
    }
}
