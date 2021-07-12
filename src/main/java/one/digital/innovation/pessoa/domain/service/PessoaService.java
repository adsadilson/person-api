package one.digital.innovation.pessoa.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import one.digital.innovation.pessoa.api.execption.NegocioException;
import one.digital.innovation.pessoa.domain.entity.Pessoa;
import one.digital.innovation.pessoa.domain.entity.Telefone;
import one.digital.innovation.pessoa.domain.repository.PessoaRepository;

@Service
@AllArgsConstructor
public class PessoaService {

	private PessoaRepository pessoaRepository;

	public List<Pessoa> listarTodos() {
		return pessoaRepository.findAll();
	}

	public Pessoa buscarPorId(Long id) {
		return pessoaRepository.findById(id)
				.orElseThrow(() -> new NegocioException("Pessoa não encontrado com esse código " + id));
	}

	public List<Pessoa> consultarPorNome(String nome) {
		return pessoaRepository.findByNome(nome);
	}

	public Pessoa buscarPorTelefone(Telefone telefone) {
		return pessoaRepository.findByTelefoneDddAndTelefoneNumero(telefone.getDdd(), telefone.getNumero())
				.orElseThrow(() -> new NegocioException(
						"Não existe pessoa com o telefone (" + telefone.getDdd() + ")" + telefone.getNumero()));
	}

	@Transactional
	public Pessoa adicionar(Pessoa pessoa) {
		pessoa.isCreate();
		if (pessoa.getTelefones() != null) {
			pessoa.getTelefones().forEach(f -> f.setPessoa(pessoa));
		}
		cpfExistente(pessoa);
		return pessoaRepository.save(pessoa);
	}

	@Transactional
	public Pessoa atualizar(Pessoa pessoa) {
		return adicionar(pessoa);
	}

	@Transactional
	public void excluir(Long id) {
		try {
			pessoaRepository.deleteById(id);
			pessoaRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new NegocioException(String.format("Não existe nenhum cadastro de %s com código %d", "Pessoa", id));
		} catch (DataIntegrityViolationException e) {
			throw new NegocioException(
					String.format("%s de código %d não pode ser excluido pois está em uso", "Pessoa", id));
		}
	}

	public Pessoa findByCpf(String string) {
		return pessoaRepository.findByCpf(string)
				.orElseThrow(() -> new NegocioException("Não existe nenhum cadastro de pessoa com esse cpf " + string));
	}

	public Pessoa findByTelefoneDddAndTelefoneNumero(String ddd, String numero) {
		return pessoaRepository.findByTelefoneDddAndTelefoneNumero(ddd, numero)
				.orElseThrow(() -> new NegocioException("Não existe pessoa com o telefone (" + ddd + ")" + numero));
	}

	public void cpfExistente(Pessoa pessoa) {
		boolean result = pessoaRepository.findByCpf(pessoa.getCpf()).stream().anyMatch(cpf -> !cpf.equals(pessoa));
		if (result) {
			throw new NegocioException(String.format("Já existe um cadastro de pessoa com cpf %s!", pessoa.getCpf()));
		}
	}
}
