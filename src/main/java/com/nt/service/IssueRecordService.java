package com.nt.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.nt.entity.Book;
import com.nt.entity.IssueRecord;
import com.nt.entity.User;
import com.nt.repository.BookRepository;
import com.nt.repository.IssueRecordRepository;
import com.nt.repository.UserRepository;

@Service
public class IssueRecordService {
	@Autowired
	private IssueRecordRepository issueRecordRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private BookRepository bookRepository;
	
	public IssueRecord issueTheBook(Long bookId) {
		Book book = bookRepository.findById(bookId).orElseThrow(()-> new RuntimeException("Book Not Found"));
		
		if(book.getQuantity() <= 0 || !book.getIsAvailable()) {
			throw new RuntimeException("Book is not Available");	
		}
		
		String username =  SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("User Not Found"));
		
		IssueRecord issueRecord = new IssueRecord();
		issueRecord.setIssueDate(LocalDate.now());
		issueRecord.setDueDate(LocalDate.now().plusDays(14));
		issueRecord.setIsReturned(false);
		issueRecord.setUser(user);
		issueRecord.setBook(book);
		
		book.setQuantity(book.getQuantity()-1);
		if(book.getQuantity() == 0) {
			book.setIsAvailable(false);
		}
		bookRepository.save(book);
		return issueRecordRepository.save(issueRecord);
	}
	
	public IssueRecord returnTheBook(Long id) {
		IssueRecord issueRecord = issueRecordRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("IssueRecordNotFound"));
		
		if(issueRecord.getIsReturned()) {
			throw new RuntimeException("Book is Already returned");
		}
		
		Book book = issueRecord.getBook();
		book.setQuantity(book.getQuantity()+1);
		book.setIsAvailable(true);
		bookRepository.save(book);
		
		issueRecord.setReturnDate(LocalDate.now());
		issueRecord.setIsReturned(true);
		
		return issueRecordRepository.save(issueRecord);
	}
}
