package com.thiago.digitalbank.repository;

import com.thiago.digitalbank.Model.DocumentClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<DocumentClient, Long> {
}