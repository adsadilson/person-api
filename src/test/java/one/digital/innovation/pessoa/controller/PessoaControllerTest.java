package one.digital.innovation.pessoa.controller;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;
import static org.hamcrest.Matchers.hasItems;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.restassured.http.ContentType;
import one.digital.innovation.pessoa.domain.entity.Pessoa;
import one.digital.innovation.pessoa.domain.enums.Sexo;
import one.digital.innovation.pessoa.domain.enums.Status;
import one.digital.innovation.pessoa.domain.repository.PessoaRepository;
import one.digital.innovation.pessoa.util.DatabaseCleaner;
import one.digital.innovation.pessoa.util.ResourceUtils;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@TestPropertySource("/application-test.properties")
@Sql(value = "/load-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class PessoaControllerTest {

	@Autowired
	private DatabaseCleaner databaseCleaner;

	@Autowired
	private PessoaRepository pessoaRepository;


	private static final int PESSOA_ID_INEXISTENTE = 100;

	@LocalServerPort
	private int port2;

	private String jsonCorreto;

	// @formatter:off
	
	@BeforeEach
	public void setUp() {
		// Habilitar o log no console para mostrar o erro apresentado.
		enableLoggingOfRequestAndResponseIfValidationFails();
		port = port2;
		basePath = "/pessoas";
		
		jsonCorreto = ResourceUtils.getContentFromResource("/json/pessoa.json");

		databaseCleaner.clearTables();
		prepararDados();
	}

	@Test
	public void deve_retornar_status200_quando_consultar_pessoas() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deve_conter_na_lista_o_nome_cleick_e_adilson() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("nome", hasItems("Cleick", "Adilson"));
	}
	
	
	@Test
	public void deve_retornar_resposta_status405_quando_consultar_pessoa_inexistente() {
		given()
			.pathParam("id", PESSOA_ID_INEXISTENTE)
			.accept(ContentType.JSON)
		.when()
			.get("/{id}")
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	public void deve_retornar_status201_quando_cadastrar_pessoa() {
		given()
			.body(jsonCorreto)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}

	
	// @formatter:on

	private void prepararDados() {
		Pessoa pessoa1 = new Pessoa();
		pessoa1.setNome("Adilson");
		pessoa1.setSobreNome("Paraguai");
		pessoa1.setSexo(Sexo.MASCULINO);
		pessoa1.setStatus(Status.INATIVO);
		pessoa1.setCpf("433.046.310-54");
		pessoaRepository.save(pessoa1);

		Pessoa pessoa2 = new Pessoa();
		pessoa2.setNome("Cleick");
		pessoa2.setSobreNome("Cristina");
		pessoa2.setSexo(Sexo.FEMININA);
		pessoa2.setStatus(Status.ATIVO);
		pessoa2.setCpf("399.004.550-45");
		pessoaRepository.save(pessoa2);
	}

}
