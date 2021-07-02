package br.com.hapvida.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.hapvida.model.Animal;
import br.com.hapvida.service.AnimalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api")
@Api(value = "Api Rest Animal")
@CrossOrigin(origins = "*")
public class AnimalController {

	@Autowired
	AnimalService animalService;

	@GetMapping("/animal")
	@ApiOperation(value = "Retorna lista de Animal")
	public ResponseEntity<Page<Animal>> listaAnimais(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
			@RequestParam(required = false) String flag) {
		Page<Animal> animalPage = animalService.findAll(pageable, flag);
		if (animalPage.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Page<Animal>>(animalPage, HttpStatus.OK);
		}
	}

	@GetMapping("/animal/{id}")
	@ApiOperation(value = "Retorna um Animal")
	public ResponseEntity<Animal> listaAnimal(@PathVariable(value = "id") Long id) {
		Optional<Animal> animal = animalService.findById(id);

		if (!animal.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Animal>(animal.get(), HttpStatus.OK);
		}
	}

	@PostMapping("/animal")
	@ApiOperation(value = "Cadastra um Animal")
	public ResponseEntity<Animal> salvarAnimal(@RequestBody @Validated Animal animal) {
		return new ResponseEntity<Animal>(animalService.save(animal), HttpStatus.CREATED);
	}

	@DeleteMapping("/animal")
	@ApiOperation(value = "Deleta um objeto Animal")
	public ResponseEntity<?> deletarAnimal(@RequestBody Animal animal) {
		Optional<Animal> animalObj = animalService.findById(animal.getId());
		if (!animalObj.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			animalService.delete(animalObj.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@DeleteMapping("/animal/{id}")
	@ApiOperation(value = "Deleta Animal por Id informado")
	public ResponseEntity<?> deletarAnimal(@PathVariable(value = "id") Long id) {
		Optional<Animal> animal = animalService.findById(id);
		if (!animal.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			animalService.delete(animal.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@PutMapping("/animal")
	@ApiOperation(value = "Atualiza Animal")
	public ResponseEntity<Animal> atualizarAnimal(@RequestBody Animal animal) {
		Optional<Animal> animalObj = animalService.findById(animal.getId());
		if (!animalObj.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			animal.setId(animalObj.get().getId());
			return new ResponseEntity<Animal>(animalService.save(animal), HttpStatus.OK);
		}
	}
}
