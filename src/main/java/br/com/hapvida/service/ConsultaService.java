package br.com.hapvida.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.hapvida.model.Consulta;
import br.com.hapvida.repository.ConsultaRepository;

@Service
public class ConsultaService {


    @Autowired
    ConsultaRepository consultaRepository;

    public Page<Consulta> findAll(Pageable pageable, String flag){
        if(flag != null && flag.equals("next")){
            return consultaRepository.findByDataConsultaAfterOrderByDataConsultaAsc(LocalDateTime.now(), pageable);
        }else if(flag != null && flag.equals("previous")){
            return consultaRepository.findByDataConsultaBeforeOrderByDataConsultaDesc(LocalDateTime.now(), pageable);
        }else{
            return consultaRepository.findAll(pageable);
        }
    }

    public Optional<Consulta> findById(Long id){
        return consultaRepository.findById(id);
    }

    public Consulta save(Consulta consulta){
        return consultaRepository.save(consulta);
    }

    public void delete(Consulta consulta){
    	consultaRepository.delete(consulta);
    }
    
    public void delete(Long id){
    	consultaRepository.deleteById(id);
    }
}
