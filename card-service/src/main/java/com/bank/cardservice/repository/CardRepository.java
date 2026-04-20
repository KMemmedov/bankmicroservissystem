package com.bank.cardservice.repository;

import com.bank.cardservice.dto.CardResponseDto;
import com.bank.cardservice.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

public List<Card> findByCustomerId(Long id);
public Long countByCustomerId(Long customerId);
}
