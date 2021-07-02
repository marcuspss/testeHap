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

import br.com.hapvida.model.Consulta;
import br.com.hapvida.service.ConsultaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api")
@Api(value = "Api Rest Consulta")
@CrossOrigin(origins = "*")
public class ConsultaController {

	@Autowired
	ConsultaService consultaService;

	@GetMapping("/consulta")
	@ApiOperation(value = "Retorna lista de Consulta")
	public ResponseEntity<Page<Consulta>> listaConsultas(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
			@RequestParam(required = false) String flag) {
		Page<Consulta> consultaPage = consultaService.findAll(pageable, flag);
		if (consultaPage.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Page<Consulta>>(consultaPage, HttpStatus.OK);
		}
	}

	@GetMapping("/consulta/{id}")
	@ApiOperation(value = "Retorna um Consulta")
	public ResponseEntity<Consulta> listaConsulta(@PathVariable(value = "id") Long id) {
		Optional<Consulta> consulta = consultaService.findById(id);

		if (!consulta.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Consulta>(consulta.get(), HttpStatus.OK);
		}
	}

	@PostMapping("/consulta")
	@ApiOperation(value = "Cadastra um Consulta")
	public ResponseEntity<Consulta> salvarConsulta(@RequestBody @Validated Consulta consulta) {
		return new ResponseEntity<Consulta>(consultaService.save(consulta), HttpStatus.CREATED);
	}

	@DeleteMapping("/consulta")
	@ApiOperation(value = "Deleta um objeto Consulta")
	public ResponseEntity<?> deletarConsulta(@RequestBody Consulta consulta) {
		Optional<Consulta> consultaObj = consultaService.findById(consulta.getId());
		if (!consultaObj.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			consultaService.delete(consultaObj.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@DeleteMapping("/consulta/{id}")
	@ApiOperation(value = "Deleta Consulta por Id informado")
	public ResponseEntity<?> deletarConsulta(@PathVariable(value = "id") Long id) {
		Optional<Consulta> consulta = consultaService.findById(id);
		if (!consulta.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			consultaService.delete(consulta.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@PutMapping("/consulta")
	@ApiOperation(value = "Atualiza Consulta")
	public ResponseEntity<Consulta> atualizarConsulta(@RequestBody Consulta consulta) {
		Optional<Consulta> consultaObj = consultaService.findById(consulta.getId());
		if (!consultaObj.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			consulta.setId(consultaObj.get().getId());
			return new ResponseEntity<Consulta>(consultaService.save(consulta), HttpStatus.OK);
		}
	}
}
