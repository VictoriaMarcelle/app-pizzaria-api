package com.pizzaria.app.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.pizzaria.app.dto.ProdutoForm;
import com.pizzaria.app.models.Produto;
import com.pizzaria.app.services.ProdutoService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/produto")
public class ProdutoController {

  @Autowired
  private ProdutoService produtoService;

  @GetMapping
  public List<Produto> getAll() {
    return produtoService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getById(@PathVariable Long id) {
    Optional<Produto> produto = produtoService.findById(id);

    if (produto.isPresent()) {
      return ResponseEntity.ok(produto.get());
    }

    return ResponseEntity.notFound().build();
  }

  @PostMapping
  @Transactional
  public ResponseEntity<?> save(@RequestBody @Valid ProdutoForm payload, UriComponentsBuilder uriBuilder) {
    Produto produto = produtoService.save(payload);
    URI uri = uriBuilder.path("/produto/{id}").buildAndExpand(produto.getId()).toUri();

    return ResponseEntity.created(uri).body(produto); 
  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid ProdutoForm form) {
    Optional<Produto> produto = produtoService.findById(id);

    if (produto.isPresent()) {
      Produto prod = form.converter();
      prod.setId(id);

      produtoService.save(prod);
      
      return ResponseEntity.ok(prod);
    }

    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<?> deletar(@PathVariable Long id) {
    Optional<Produto> produto = produtoService.findById(id);

    if (produto.isPresent()) {
      produtoService.deleteById(produto.get());
      return ResponseEntity.ok(produto);
    }
    
    return ResponseEntity.notFound().build();
  }
}
