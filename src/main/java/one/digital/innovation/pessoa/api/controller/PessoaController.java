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
import one.digital.innovation.pessoa.api.dto.input.PessoaInput;
import one.digital.innovation.pessoa.api.dto.model.PessoaModel;
import one.digital.innovation.pessoa.api.mapper.PessoaMapper;
import one.digital.innovation.pessoa.domain.entity.Pessoa;
import one.digital.innovation.pessoa.domain.entity.Telefone;
import one.digital.innovation.pessoa.domain.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
@AllArgsConstructor
public class PessoaController {

	private PessoaService pessoaService;

	private PessoaMapper mapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PessoaModel adicionar(@Valid @RequestBody PessoaInput pessoaInput) {
		Pessoa pessoaSalva = pessoaService.adicionar(mapper.toDomain(pessoaInput));
		return mapper.toModel(pessoaSalva);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PessoaModel> atualizar(@Valid @RequestBody PessoaInput pessoaInput, @PathVariable Long id) {
		Pessoa pessoa = pessoaService.buscarPorId(id);
		mapper.copyToDomainObject(pessoaInput, pessoa);
		pessoaService.atualizar(pessoa);
		return ResponseEntity.ok(mapper.toModel(pessoa));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		pessoaService.buscarPorId(id);
		pessoaService.excluir(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<List<PessoaModel>> listarTodos() {
		return ResponseEntity.ok(mapper.toCollectionModel(pessoaService.listarTodos()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<PessoaModel> buscarPorId(@PathVariable Long id) {
		Pessoa pessoa = pessoaService.buscarPorId(id);
		return ResponseEntity.ok(mapper.toModel(pessoa));
	}

	@GetMapping("/{ddd}/{numero}")
	public ResponseEntity<PessoaModel> buscarPorDddENumeroDoTelefone(@PathVariable("ddd") String ddd,
			@PathVariable("numero") String numero) {
		final Telefone telefone = new Telefone();
		telefone.setDdd(ddd);
		telefone.setNumero(numero);
		Pessoa pessoa = pessoaService.buscarPorTelefone(telefone);
		return ResponseEntity.ok(mapper.toModel(pessoa));
	}

}
