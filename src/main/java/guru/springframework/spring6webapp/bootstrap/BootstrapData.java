package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BootstrapData implements CommandLineRunner {
    private final AuthorRepository authorRepo;
    private final BookRepository bookRepo;
    private final PublisherRepository publisherRepo;

    public BootstrapData(AuthorRepository authorRepo, BookRepository bookRepo, PublisherRepository pubRepo) {
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
        this.publisherRepo = pubRepo;
    }

    @Override
    public void run(String... args) throws Exception{
        Author axel = new Author();
        axel.setFirstname("Axel");
        axel.setLastname("Karlsson");

        Book mittliv = new Book();
        mittliv.setTitle("Mitt Liv");
        mittliv.setIsbn("109202858383");

        mittliv.getAuthors().add(axel);
        axel.getBooks().add(mittliv);

        Publisher pub = new Publisher();
        pub.setPublisherName("Wellred");
        pub.setAdress("Foobargatan 24");
        pub.setCity("Foobaring");
        pub.setPostalcode(1337);
        mittliv.setPublisher( pub);


        Publisher savedPub = publisherRepo.save(pub);
        Author axelSparad = authorRepo.save(axel);
        Book sparadmittLiv = bookRepo.save(mittliv);

        //System.out.println(sparadmittLiv.toString());
        System.out.println("Authors in repo: " + authorRepo.count());
        System.out.println("Books in repo: " + bookRepo.count());
        System.out.println("Publishers in repo: " + publisherRepo.count());



    }
}
