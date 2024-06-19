package com.br.projeto_tdd.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.br.projeto_tdd.model.Produto;
import com.br.projeto_tdd.service.ProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;


@WebMvcTest
public class ProdutoControllerTest {
	
	@Autowired
	private ProdutoController produtoController;

	@Autowired
	private MockMvc mockMvc ;
	
	@MockBean
	private ProdutoService produtoService;
	
	@BeforeEach
	public void setup() {
		
		// Aqui sera execultado antes de qualquer caso de teste.
		this.mockMvc = MockMvcBuilders.standaloneSetup(produtoController).build();
		
	}
	
	@Test
	public void deve_retornar_adicionar_o_produto() throws Exception {
		
		// Arrange - Preparação	
		// Criando um produto para ser add
		Produto produto = new Produto() ;
		produto.setId(2l);
		produto.setNome("martelo");
		produto.setQuantidade(2);
		
		// Convertendo o produto para um bory json
		String json = new ObjectMapper().writeValueAsString(produto);
		
		// Criando um requisição tipo post
		var resquestBuilder = MockMvcRequestBuilders.post("/api/produtos")
			.content(json)
			.contentType(MediaType.APPLICATION_JSON);
			
		//add o id ao produto que iremos retornar
		produto.setId(1l);
		when(this.produtoService.adicionar(produto)).thenReturn(produto);
		
		// Act - Ação 			
		this.mockMvc.perform(resquestBuilder)
				
		// Assert -Confirmação
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	/*
	@Test
	public void deve_retornar_o_produto_por_id() throws Exception {
		
		// Arrange - Preparação	
		Produto produto = new Produto() ;
		produto.setId(2l);
		produto.setNome("martelo");
		produto.setQuantidade(2);
		
		Optional<Produto> optProduto = Optional.of(produto);
		var resquestBuilder = MockMvcRequestBuilders.get("/api/produtos/2");
		when(this.produtoService.obterPorId(2l)).thenReturn(optProduto);
		
		// Act - Ação 			
		this.mockMvc.perform(resquestBuilder)
				
		// Assert -Confirmação
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2l));
	}
	
	@Test
	public void deve_retornar_status_200_ok_ao_obter_todos_os_produtos() throws Exception {
		
		// Arrange - Preparação	
		List<Produto> produtos = new ArrayList<Produto>() ;
		var resquestBuilder = MockMvcRequestBuilders.get("/api/produtos");
		when(this.produtoService.obterTodos()).thenReturn(produtos);
		
		// Act - Ação 			
		this.mockMvc.perform(resquestBuilder)
				
		// Assert -Confirmação
		.andExpect(MockMvcResultMatchers.status().isOk());	
	}*/
	
	
	
}
