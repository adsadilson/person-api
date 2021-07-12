package one.digital.innovation.pessoa.api.dto.model;

import lombok.Data;

@Data
public class EstadoModel {

	private Long id;

	private String nome;

	private String sigla;

	private String capital;

	private String regiao;
}
