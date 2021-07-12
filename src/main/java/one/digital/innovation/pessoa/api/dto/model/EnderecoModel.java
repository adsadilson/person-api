package one.digital.innovation.pessoa.api.dto.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnderecoModel {

	private Long id;

	private String logradouro;

	private String numero;

	private String bairro;

	private String complemento;

	private CidadeModel cidade;

}
