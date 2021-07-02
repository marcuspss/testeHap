package br.com.hapvida.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hapvida.model.Tutor;
import br.com.hapvida.repository.TutorRepository;

@Service
public class TutorService {

	@Autowired
	TutorRepository tutorRepository;

	public List<Tutor> findAll() {
			return tutorRepository.findAll();
	}

	public Optional<Tutor> findById(Long id) {
		return tutorRepository.findById(id);
	}

	public Tutor save(Tutor tutor) {
		return tutorRepository.save(tutor);
	}

	public void delete(Tutor tutor) {
		tutorRepository.delete(tutor);
	}

	public void delete(Long id) {
		tutorRepository.deleteById(id);
	}
}
