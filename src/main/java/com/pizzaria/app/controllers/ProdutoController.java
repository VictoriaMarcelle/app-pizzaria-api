package com.pizzaria.app.controllers;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.pizzaria.app.dto.ProdutoForm;
import com.pizzaria.app.models.Produto;
import com.pizzaria.app.services.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

  @Autowired
  private ProdutoService produtoService;

  @GetMapping
  public List<Produto> getAll() {
    return produtoService.findAll();
  }

  @PostMapping
  @Transactional
  public ResponseEntity<?> save(@RequestBody @Valid ProdutoForm payload, UriComponentsBuilder uriBuilder) {
    Produto produto = produtoService.save(payload);
    URI uri = uriBuilder.path("/produto/{id}").buildAndExpand(produto.getId()).toUri();

    return ResponseEntity.created(uri).body(produto); 
  }

}
