package br.com.hapvida.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hapvida.model.Tutor;
import br.com.hapvida.model.Veterinario;
import br.com.hapvida.repository.VeterinarioRepository;
import br.com.hapvida.service.VeterinarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value = "Api Rest Veterinario")
@CrossOrigin(origins = "*")
public class VeterinarioController {
	
	@Autowired
	VeterinarioService veterinarioService;
	
	@GetMapping("/veterinario")
	@ApiOperation(value = "Retorna lista de Veterinario")
	public ResponseEntity<List<Veterinario>> listaVeterinarios(){
		List<Veterinario> veterinarioLista = veterinarioService.findAll();
		
		if (!veterinarioLista.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Veterinario>>(veterinarioLista, HttpStatus.OK);
		}
	}
	
	@GetMapping("/veterinario/{id}")
	@ApiOperation(value = "Retorna um Veterinario")
	public ResponseEntity<Veterinario> listaVeterinario(@PathVariable(value="id") Long id){
		Optional<Veterinario> veterinario = veterinarioService.findById(id);

		if (!veterinario.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Veterinario>(veterinario.get(), HttpStatus.OK);
		}
	}
	
	@PostMapping("/veterinario")
	@ApiOperation(value = "Cadastra um Veterinario")
	public ResponseEntity<Veterinario> salvarVeterinario(@RequestBody @Validated Veterinario veterinario) {
		return new ResponseEntity<Veterinario>(veterinarioService.save(veterinario), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/veterinario")
	@ApiOperation(value = "Deleta um objeto Veterinario")
	public  ResponseEntity<?> deletarVeterinario(@RequestBody Veterinario veterinario) {
		Optional<Veterinario> veterinarioObj = veterinarioService.findById(veterinario.getId());
		if (!veterinarioObj.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			veterinarioService.delete(veterinarioObj.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/veterinario/{id}")
	@ApiOperation(value = "Deleta Veterinario por Id informado")
	public  ResponseEntity<?> deletarVeterinario(@PathVariable(value="id") Long id) {
		Optional<Veterinario> veterinarioObj = veterinarioService.findById(id);
		if (!veterinarioObj.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			veterinarioService.delete(veterinarioObj.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	@PutMapping("/veterinario")
	@ApiOperation(value = "Atualiza Veterinario")
	public ResponseEntity<Veterinario> atualizarVeterinario(@RequestBody Veterinario veterinario) {
		Optional<Veterinario> veterinarioObj = veterinarioService.findById(veterinario.getId());
		if (!veterinarioObj.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			veterinario.setId(veterinarioObj.get().getId());
			return new ResponseEntity<Veterinario>(veterinarioService.save(veterinario), HttpStatus.OK);
		}
	}

}
