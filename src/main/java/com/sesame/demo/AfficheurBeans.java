package com.sesame.demo;

import com.sesame.demo.repository.BookRepository;
import com.sesame.demo.service.BookService;
import java.util.Arrays;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Component
public class AfficheurBeans implements CommandLineRunner {
    private static final String PAQUET = "com.sesame.demo";
    private final ApplicationContext ctx;

    public AfficheurBeans(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void run(String... args) {
        String[] controllers = ctx.getBeanNamesForAnnotation(RestController.class);
        String[] services = ctx.getBeanNamesForAnnotation(Service.class);
        String[] repositories = ctx.getBeanNamesForType(BookRepository.class);

        System.out.println("=== Beans de la librairie ===");
        System.out.println("@RestController : " + deLaLibrairie(controllers));
        System.out.println("@Service        : " + deLaLibrairie(services));
        System.out.println("LivreRepository : " + Arrays.toString(repositories));

        boolean singleton = ctx.isSingleton("bookService");
        System.out.println("livreService est un singleton : " + singleton);
        BookService s1 = ctx.getBean(BookService.class);
        BookService s2 = ctx.getBean(BookService.class);
        System.out.println("Meme instance a chaque appel : " + (s1 == s2));
    }

    private List<String> deLaLibrairie(String[] noms) {
        return Arrays.stream(noms)
                .filter(nom -> ctx.getType(nom).getName().startsWith(PAQUET))
                .sorted()
                .toList();
    }
}
