package com.nt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.dto.BookDTO;
import com.nt.entity.Book;
import com.nt.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	public BookRepository bookRepository;
	
	public List<Book> getAllBooks(){
		return bookRepository.findAll();
	}
	
	public Book getBookById(Long id) {
		Book book = bookRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Book Not found"));
		return book;
	}
	
	public Book addBook(BookDTO bookDTO) {
		Book book = new Book();
		book.setTitle(bookDTO.getTitle());
		book.setAuthor(bookDTO.getAuthor());
		book.setIsbn(bookDTO.getIsbn());
		book.setIsAvailable(bookDTO.getIsAvailable());
		book.setQuantity(bookDTO.getQuantity());
		
		return bookRepository.save(book);
	}
	
	public Book updateBook(Long id, BookDTO bookDTO) {
		Book oldBook = bookRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Book not Found"));
		oldBook.setTitle(bookDTO.getTitle());
		oldBook.setAuthor(bookDTO.getAuthor());
		oldBook.setIsbn(bookDTO.getIsbn());
		oldBook.setIsAvailable(bookDTO.getIsAvailable());
		oldBook.setQuantity(bookDTO.getQuantity());
		return bookRepository.save(oldBook);
	}
	
	public void deleteBookById(Long id) {
		 bookRepository.deleteById(id);
	}
}
