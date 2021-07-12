package one.digital.innovation.pessoa.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import one.digital.innovation.pessoa.api.execption.NegocioException;
import one.digital.innovation.pessoa.domain.entity.Cidade;
import one.digital.innovation.pessoa.domain.entity.Estado;
import one.digital.innovation.pessoa.domain.repository.CidadeRepository;

@Service
@AllArgsConstructor
public class CidadeService {

	private CidadeRepository cidadeRepository;

	private EstadoService estadoService;

	public List<Cidade> listarTodos() {
		return cidadeRepository.findAll();
	}

	public Cidade buscarPorId(Long id) {
		return cidadeRepository.findById(id)
				.orElseThrow(() -> new NegocioException("Cidade não encontrado com esse código " + id));
	}

	public List<Cidade> consultarPorNome(String nome) {
		return cidadeRepository.findByNome(nome);
	}

	@Transactional
	public Cidade adicionar(Cidade cidade) {
		Estado estadoSalvo = estadoService.buscarPorId(cidade.getEstado().getId());
		cidadeExistente(cidade);
		cidade.setEstado(estadoSalvo);
		return cidadeRepository.save(cidade);
	}

	@Transactional
	public Cidade atualizar(Cidade cidade) {
		return adicionar(cidade);
	}

	@Transactional
	public void excluir(Long id) {
		try {
			cidadeRepository.deleteById(id);
			cidadeRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new NegocioException(String.format("Não existe nenhum cadastro de %s com código %d", "Cidade", id));
		} catch (DataIntegrityViolationException e) {
			throw new NegocioException(
					String.format("%s de código %d não pode ser excluido pois está em uso", "Cidade", id));
		}
	}

	/**
	 * Método para validar se a cidade já esta cadastro para o mesmo estado.
	 * 
	 * @param cidade Objeto a ser consultado.
	 */
	public void cidadeExistente(Cidade cidade) {
		Cidade result = cidadeRepository.consultarPorNomeESilga(cidade);
		if (result != null) {
			throw new NegocioException(String.format("Cidade [%s] já cadastrada para estado [%s].", cidade.getNome(),
					result.getEstado().getSigla()));
		}
	}

	public List<Cidade> listarCidadesPorEstado(String sigla) {
		return cidadeRepository.listarCidadesPorEstado(sigla);
	}

}
