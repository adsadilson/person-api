package one.digital.innovation.pessoa.api.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
public class CidadeModel {

	private Long id;

	private String nome;

	@JsonIgnoreProperties(value = { "nome", "capital", "regiao" }, allowGetters = false)
	private EstadoModel estado;
}
