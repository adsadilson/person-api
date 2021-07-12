package one.digital.innovation.pessoa.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import one.digital.innovation.pessoa.api.dto.input.EstadoInput;
import one.digital.innovation.pessoa.api.dto.model.EstadoModel;
import one.digital.innovation.pessoa.api.mapper.EstadoMapper;
import one.digital.innovation.pessoa.domain.entity.Estado;
import one.digital.innovation.pessoa.domain.service.EstadoService;

@RestController
@RequestMapping("/estados")
@AllArgsConstructor
public class EstadoController {

	private EstadoService estadoService;

	private EstadoMapper mapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoModel adicionar(@Valid @RequestBody EstadoInput input) {
		Estado estadoSalvo = estadoService.adicionar(mapper.toDomain(input));
		return mapper.toModel(estadoSalvo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EstadoModel> atualizar(@Valid @RequestBody EstadoInput input, @PathVariable Long id) {
		Estado estado = estadoService.buscarPorId(id);
		mapper.copyToDomainObject(input, estado);
		estadoService.atualizar(estado);
		return ResponseEntity.ok(mapper.toModel(estado));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> excluir(@PathVariable Long id) {
		estadoService.buscarPorId(id);
		estadoService.excluir(id);
		return new ResponseEntity<String>("Estado de código " + id + " foi excluído com sucesso!",
				HttpStatus.NO_CONTENT);
	}

	@GetMapping
	public ResponseEntity<List<Estado>> listarTodos() {
		return ResponseEntity.ok(estadoService.listarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Estado> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(estadoService.buscarPorId(id));
	}
}
