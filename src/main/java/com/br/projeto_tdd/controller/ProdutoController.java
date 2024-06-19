package com.br.projeto_tdd.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.projeto_tdd.model.Produto;
import com.br.projeto_tdd.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping
	public ResponseEntity<List<Produto>> obterTodos(){
		
		List<Produto> produtos = produtoService.obterTodos();
					
		return new  ResponseEntity<> (produtos, HttpStatusCode.valueOf(200)) ;
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Produto>> obterPorId(@PathVariable Long id){
		
		Optional<Produto> produto = produtoService.obterPorId(id);
		
		return new  ResponseEntity<> (produto, HttpStatusCode.valueOf(200)) ;
		
	}
	
	@PostMapping()
	public ResponseEntity<Produto> adicionar(@RequestBody Produto produto ){
		
		Produto produtoAdd = produtoService.adicionar(produto);
			
		return new ResponseEntity<>(produtoAdd, HttpStatusCode.valueOf(200)) ;
		
	}
	
	
	

}
