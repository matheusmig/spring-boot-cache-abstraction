package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class TestBookGenerator {
    public static List<Book> getAllBooks(){
        var listBook = new ArrayList<Book>();
        listBook.add(new Book(1,"9780194230698","Eu, Robô", "Isaac Asimov"));
        listBook.add(new Book(2,"8576574837", "Fundação", "Isaac Asimov"));
        listBook.add(new Book(3,"9788535909555", "A revolução dos bichos", "George Orwell"));
        listBook.add(new Book(4,"8599296604", "Até mais, e obrigado pelos peixes! (O mochileiro das galáxias – Livro 4", "Douglas Adams"));
        listBook.add(new Book(5, "8550804606", "Arquitetura limpa: O guia do artesão para estrutura e design de software", "Robert C. Martin"));
        return listBook;
    }
}
