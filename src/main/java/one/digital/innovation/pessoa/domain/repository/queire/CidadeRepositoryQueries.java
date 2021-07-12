package one.digital.innovation.pessoa.domain.repository.queire;

import java.util.List;

import one.digital.innovation.pessoa.domain.entity.Cidade;

public interface CidadeRepositoryQueries {

	Cidade consultarPorNomeESilga(Cidade cidade);
	
	/**
	 * Método para listar todas cidades pelo o estado.
	 * @param sigla Receber uma string referente a sigla do estado.
	 * @return Retornar uma lista de todas as cidades daquele estado consultado.
	 */
	List<Cidade> listarCidadesPorEstado(String sigla);

}
