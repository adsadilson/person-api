package one.digital.innovation.pessoa.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import one.digital.innovation.pessoa.api.dto.input.EstadoInput;
import one.digital.innovation.pessoa.api.dto.model.EstadoModel;
import one.digital.innovation.pessoa.domain.entity.Estado;

@Component
public class EstadoMapper {

	@Autowired
	private ModelMapper modelMapper;

	public EstadoModel toModel(Estado estado) {
		return modelMapper.map(estado, EstadoModel.class);
	}

	public List<EstadoModel> toCollectionModel(List<Estado> estados) {
		return estados.stream().map(estado -> toModel(estado)).collect(Collectors.toList());
	}

	public Estado toDomain(EstadoInput input) {
		return modelMapper.map(input, Estado.class);
	}

	public void copyToDomainObject(EstadoInput input, Estado estado) {
		// Para evitar org.hibernate.HibernateException: identifier of an instance of
		// br.com.apssystem.algafood-api.domain.model.Cidade was altered from 1 to 2
		modelMapper.map(input, estado);
	}
}
