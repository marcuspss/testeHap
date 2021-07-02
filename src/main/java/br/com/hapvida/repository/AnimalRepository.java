package br.com.hapvida.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hapvida.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
	
	  Page<Animal> findByDataNascimentoAfterOrderByDataNascimentoAsc(LocalDateTime date, Pageable pageable);

	  Page<Animal> findByDataNascimentoBeforeOrderByDataNascimentoDesc(LocalDateTime date, Pageable pageable);
}
