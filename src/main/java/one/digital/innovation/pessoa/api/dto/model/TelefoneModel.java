package one.digital.innovation.pessoa.api.dto.model;

import lombok.Data;
import one.digital.innovation.pessoa.domain.enums.TipoTelefone;

@Data
public class TelefoneModel {

	private Long id;

	private TipoTelefone tipo;

	private String ddd;

	private String numero;

}
