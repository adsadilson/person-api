package one.digital.innovation.pessoa.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {

	ATIVO("Ativo"), 
	INATIVO("Inativo"); 
	

	private String descricao;
}
