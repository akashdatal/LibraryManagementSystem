package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.IssueRecord;

public interface IssueRecordRepository extends JpaRepository<IssueRecord, Long> {

}
