package br.com.hapvida.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hapvida.model.Veterinario;
import br.com.hapvida.repository.VeterinarioRepository;

@Service
public class VeterinarioService {

	@Autowired
	VeterinarioRepository veterinarioRepository;

	public List<Veterinario> findAll() {
			return veterinarioRepository.findAll();
	}

	public Optional<Veterinario> findById(Long id) {
		return veterinarioRepository.findById(id);
	}

	public Veterinario save(Veterinario veterinario) {
		return veterinarioRepository.save(veterinario);
	}

	public void delete(Veterinario veterinario) {
		veterinarioRepository.delete(veterinario);
	}

	public void delete(Long id) {
		veterinarioRepository.deleteById(id);
	}
}
