package one.digital.innovation.pessoa.domain.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import one.digital.innovation.pessoa.domain.enums.Sexo;
import one.digital.innovation.pessoa.domain.enums.Status;

@Entity
@Table(name = "pessoa")
@SequenceGenerator(name = "PESSOA_ID", sequenceName = "PESSOA_ID_SEQ")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "PESSOA_ID_SEQ")
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "sobrenome")
	private String sobreNome;

	@Column(nullable = false, unique = true)
	private String cpf;

	private LocalDate nascimento;

	@CreationTimestamp
	private LocalDate cadastro;

	@Enumerated(EnumType.STRING)
	private Status status;

	@Enumerated(EnumType.STRING)
	private Sexo sexo;

	@OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Telefone> telefones;

	/*
	 * @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY, cascade =
	 * CascadeType.ALL) private List<Endereco> enderecos;
	 */
	public void isCreate() {
		this.status = this.id == null ? Status.ATIVO : Status.INATIVO;
	}
}
