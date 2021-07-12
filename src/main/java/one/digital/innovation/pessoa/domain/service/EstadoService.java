package one.digital.innovation.pessoa.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import one.digital.innovation.pessoa.api.execption.NegocioException;
import one.digital.innovation.pessoa.domain.entity.Estado;
import one.digital.innovation.pessoa.domain.repository.EstadoRepository;

@Service
@AllArgsConstructor
public class EstadoService {

	private EstadoRepository estadoRepository;

	public List<Estado> listarTodos() {
		return estadoRepository.findAll();
	}

	public Estado buscarPorId(Long id) {
		return estadoRepository.findById(id)
				.orElseThrow(() -> new NegocioException("Estado não encontrado com esse código " + id));
	}

	/*
	 * public Estado consultarPorSigla(String sigla) { return
	 * estadoRepository.findBySigla(sigla) .orElseThrow(() -> new
	 * NegocioException("Nenhum estado encontrado com essa silga" + sigla)); }
	 */

	@Transactional
	public Estado adicionar(Estado estado) {
		estadoExistente(estado);
		return estadoRepository.save(estado);
	}

	public Estado atualizar(Estado estado) {
		return adicionar(estado);
	}

	@Transactional
	public void excluir(Long id) {
		try {
			estadoRepository.deleteById(id);
			estadoRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new NegocioException(String.format("Não existe nenhum cadastro de %s com código %d", "Estado", id));
		} catch (DataIntegrityViolationException e) {
			throw new NegocioException(
					String.format("%s de código %d não pode ser excluido pois está em uso", "Estado", id));
		}
	}

	public void estadoExistente(Estado estado) {
		boolean result = estadoRepository.findBySigla(estado.getSigla()).stream().anyMatch(uf -> !uf.equals(estado));
		if (result) {
			throw new NegocioException(
					String.format("Já existe um cadastro de estado com silga %s!", estado.getSigla()));
		}
	}

}
