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
import br.com.hapvida.service.TutorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value = "Api Rest Tutor")
@CrossOrigin(origins = "*")
public class TutorController {
	
	@Autowired
	TutorService tutorService;
	
	@GetMapping("/tutor")
	@ApiOperation(value = "Retorna lista de Tutor")
	public ResponseEntity<List<Tutor>> listaTutores(){
		List<Tutor> tutorLista = tutorService.findAll();
		
		if (!tutorLista.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Tutor>>(tutorLista, HttpStatus.OK);
		}
	}
	
	@GetMapping("/tutor/{id}")
	@ApiOperation(value = "Retorna um Tutor")
	public ResponseEntity<Tutor> listaTutor(@PathVariable(value="id") Long id){
		Optional<Tutor> tutor = tutorService.findById(id);

		if (!tutor.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Tutor>(tutor.get(), HttpStatus.OK);
		}
	}
	
	@PostMapping("/tutor")
	@ApiOperation(value = "Cadastra um Tutor")
	public ResponseEntity<Tutor> salvarTutor(@RequestBody @Validated Tutor tutor) {
		return new ResponseEntity<Tutor>(tutorService.save(tutor), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/tutor")
	@ApiOperation(value = "Deleta um objeto Tutor")
	public ResponseEntity<?> deletarTutor(@RequestBody Tutor tutor) {
		Optional<Tutor> tutorObj = tutorService.findById(tutor.getId());
		if (!tutorObj.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			tutorService.delete(tutorObj.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/tutor/{id}")
	@ApiOperation(value = "Deleta Tutor por Id informado")
	public ResponseEntity<?> deletarTutor(@PathVariable(value="id") Long id) {
		Optional<Tutor> tutorObj = tutorService.findById(id);
		if (!tutorObj.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			tutorService.delete(tutorObj.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
	@PutMapping("/tutor")
	@ApiOperation(value = "Atualiza Tutor")
	public ResponseEntity<Tutor> atualizarTutor(@RequestBody Tutor tutor) {
		Optional<Tutor> tutorObj = tutorService.findById(tutor.getId());
		if (!tutorObj.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			tutor.setId(tutorObj.get().getId());
			return new ResponseEntity<Tutor>(tutorService.save(tutor), HttpStatus.OK);
		}
	}

}
