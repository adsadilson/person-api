package one.digital.innovation.pessoa.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import one.digital.innovation.pessoa.api.dto.input.CidadeInput;
import one.digital.innovation.pessoa.api.dto.model.CidadeModel;
import one.digital.innovation.pessoa.domain.entity.Cidade;
import one.digital.innovation.pessoa.domain.entity.Estado;

@Component
public class CidadeMapper {

	@Autowired
	private ModelMapper modelMapper;

	public CidadeModel toModel(Cidade cidade) {
		return modelMapper.map(cidade, CidadeModel.class);
	}

	public List<CidadeModel> toCollectionModel(List<Cidade> cidades) {
		return cidades.stream().map(cidade -> toModel(cidade)).collect(Collectors.toList());
	}

	public Cidade toDomain(CidadeInput input) {
		return modelMapper.map(input, Cidade.class);
	}

	public void copyToDomainObject(CidadeInput input, Cidade cidade) {
		// Para evitar org.hibernate.HibernateException: identifier of an instance of
		// br.com.apssystem.algafood-api.domain.model.Cidade was altered from 1 to 2
		cidade.setEstado(new Estado());
		modelMapper.map(input, cidade);
	}
}
