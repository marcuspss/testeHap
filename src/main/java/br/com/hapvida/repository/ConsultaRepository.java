package br.com.hapvida.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hapvida.model.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
	  Page<Consulta> findByDataConsultaAfterOrderByDataConsultaAsc(LocalDateTime date, Pageable pageable);

	  Page<Consulta> findByDataConsultaBeforeOrderByDataConsultaDesc(LocalDateTime date, Pageable pageable);

}
