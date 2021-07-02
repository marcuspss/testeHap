package br.com.hapvida.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hapvida.model.Veterinario;

public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
	

}
