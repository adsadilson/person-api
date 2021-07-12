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
import one.digital.innovation.pessoa.api.dto.input.CidadeInput;
import one.digital.innovation.pessoa.api.dto.model.CidadeModel;
import one.digital.innovation.pessoa.api.mapper.CidadeMapper;
import one.digital.innovation.pessoa.domain.entity.Cidade;
import one.digital.innovation.pessoa.domain.service.CidadeService;

@RestController
@RequestMapping("/cidades")
@AllArgsConstructor
public class CidadeController {

	private CidadeService cidadeService;

	private CidadeMapper mapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModel adicionar(@Valid @RequestBody CidadeInput input) {
		Cidade cidadeSalvo = cidadeService.adicionar(mapper.toDomain(input));
		return mapper.toModel(cidadeSalvo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CidadeModel> atualizar(@Valid @RequestBody CidadeInput input, @PathVariable Long id) {
		Cidade cidade = cidadeService.buscarPorId(id);
		mapper.copyToDomainObject(input, cidade);
		cidadeService.atualizar(cidade);
		return ResponseEntity.ok(mapper.toModel(cidade));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		cidadeService.buscarPorId(id);
		cidadeService.excluir(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<List<Cidade>> listarTodos() {
		return ResponseEntity.ok(cidadeService.listarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cidade> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(cidadeService.buscarPorId(id));
	}

	@GetMapping("/uf/{sigla}")
	public ResponseEntity<List<Cidade>> listarCidadesPorEstado(String sigla) {
		return ResponseEntity.ok(cidadeService.listarCidadesPorEstado(sigla));
	}
}
