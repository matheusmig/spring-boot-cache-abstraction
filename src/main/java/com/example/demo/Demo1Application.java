package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Demo1Application implements CommandLineRunner {
	private static Logger LOG = LoggerFactory.getLogger(Demo1Application.class);

	@Autowired
	private BookLibrary bookLibrary;

	@Autowired
	private CacheManager cacheManager;

	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
	}

	@Override
	public void run(String... args) {
		LOG.info("EXECUTING");

		bookLibrary.printAllBooks();

		// Cacheable - miss test
		bookLibrary.getByAuthor("Isaac Asimov");
		bookLibrary.getById(1);

		bookLibrary.getByAuthor("Isaac Asimov");
		bookLibrary.getByAuthor("Isaac Asimov");

		// Cache Put
		var book1 = bookLibrary.getById(1);
		var newBook = new Book(1, "111111111", "MY NEW BOOK", "Isaac Asimov");
		bookLibrary.update(newBook);
		LOG.info(bookLibrary.getById(1).toString());  // cache hit here: getting updated entry

		bookLibrary.printAllBooks();

		// Cache Evict
		bookLibrary.getByAuthor("Isaac Asimov");
		LOG.info("Cleaning all entries from cache");
		bookLibrary.cleanCacheAllEntries();
		bookLibrary.getByAuthor("Isaac Asimov");
		bookLibrary.getByAuthor("Isaac Asimov");
	}

}
