package br.com.hapvida.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hapvida.model.Tutor;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
	
}
