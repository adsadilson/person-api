package one.digital.innovation.pessoa.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import one.digital.innovation.pessoa.api.dto.input.TelefoneInput;
import one.digital.innovation.pessoa.api.dto.model.TelefoneModel;
import one.digital.innovation.pessoa.domain.entity.Telefone;

@Component
public class TelefoneMapper {

	@Autowired
	private ModelMapper modelMapper;

	public TelefoneModel toModel(Telefone telefone) {
		return modelMapper.map(telefone, TelefoneModel.class);
	}

	public List<TelefoneModel> toCollectionModel(List<Telefone> cidades) {
		return cidades.stream().map(telefone -> toModel(telefone)).collect(Collectors.toList());
	}

	public Telefone toDomain(TelefoneInput input) {
		return modelMapper.map(input, Telefone.class);
	}

	public void copyToDomainObject(TelefoneInput input, Telefone telefone) {
		// Para evitar org.hibernate.HibernateException: identifier of an instance of
		// br.com.apssystem.algafood-api.domain.model.Telefone was altered from 1 to 2
		modelMapper.map(input, telefone);
	}
}
