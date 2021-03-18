package com.course.bootstrap;

import com.course.domain.Author;
import com.course.domain.Book;
import com.course.domain.Publisher;
import com.course.repositories.AuthorRepository;
import com.course.repositories.BookRepository;
import com.course.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author mihai = new Author("mihai", "coman");
        Book cartemihai = new Book("cartemihai","1234");

        Publisher altmihai = new Publisher("altmihai","Aliorului",
                "Bucharest", "Romania", "0300");

        publisherRepository.save(altmihai);

        mihai.getBooks().add(cartemihai);
        cartemihai.getAuthors().add(mihai);

        cartemihai.setPublisher(altmihai);
        altmihai.getBooks().add(cartemihai);

        authorRepository.save(mihai);
        bookRepository.save(cartemihai);
       publisherRepository.save(altmihai);

        Author burcea = new Author("adrian", "burcea");
        Book carteburcea = new Book("carteburcea","4321");

        burcea.getBooks().add(carteburcea);
        carteburcea.getAuthors().add(burcea);

        carteburcea.setPublisher(altmihai);
        altmihai.getBooks().add(carteburcea);

        authorRepository.save(burcea);
        bookRepository.save(carteburcea);
        publisherRepository.save(altmihai);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of books: " + bookRepository.count());

        System.out.println("Number of publishers: " +publisherRepository.count());

        System.out.println("Publisher number of books: " + altmihai.getBooks().size());


    }
}
