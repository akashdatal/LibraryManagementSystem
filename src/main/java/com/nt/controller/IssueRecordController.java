package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.IssueRecord;
import com.nt.service.IssueRecordService;

@RestController
@RequestMapping("/issuerecords")
public class IssueRecordController {
	@Autowired
	private IssueRecordService issueRecordService;
	
	@PostMapping("/issuethebook/{id}")
	public ResponseEntity<IssueRecord> issueTheBook(@PathVariable Long id){
		return ResponseEntity.ok(issueRecordService.issueTheBook(id));
	}
	
	@PostMapping("/returnthebook/{issuerecordid}")
	public ResponseEntity<IssueRecord> returnTheBook(@PathVariable Long issuerecordid){
		return ResponseEntity.ok(issueRecordService.returnTheBook(issuerecordid));
	}
}
