package one.digital.innovation.pessoa.api.dto.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digital.innovation.pessoa.domain.enums.TipoTelefone;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneInput {

	@NotNull
	private TipoTelefone tipo;

	@NotBlank
	@Size(max = 2)
	private String ddd;

	@NotBlank
	@Size(min = 9, max = 14)
	private String numero;
		
	private PessoaIdInput pessoa;
	
}
