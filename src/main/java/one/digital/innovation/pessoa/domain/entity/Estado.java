package one.digital.innovation.pessoa.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "estado")
@SequenceGenerator(name = "ESTADO_ID", sequenceName = "ESTADO_ID_SEQ")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Estado {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ESTADO_ID_SEQ")
	@EqualsAndHashCode.Include
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false, unique = true, length = 2)
	private String sigla;

	private String capital;

	private String regiao;

}
