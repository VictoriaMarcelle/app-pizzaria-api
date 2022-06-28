package com.pizzaria.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pizzaria.app.dto.ProdutoForm;
import com.pizzaria.app.models.Produto;
import com.pizzaria.app.repositories.ProdutoRepository;

@Service
public class ProdutoService {
  @Autowired
  private ProdutoRepository produtoRepository;

  public Produto save(ProdutoForm produtoForm) {
    // usuarioForm.setSenha(encoder.encode(usuarioForm.getSenha()));
    Produto produto = produtoRepository.save(produtoForm.converter());
    return produto;
  }

  public Produto save(Produto produto) {
    // usuarioForm.setSenha(encoder.encode(usuarioForm.getSenha()));
    return produtoRepository.save(produto);
  }

  public List<Produto> findAll() {
    return produtoRepository.findAll();
  }

  public Optional<Produto> findById(Long id) {
    return produtoRepository.findById(id);
  }

  public void deleteById(Produto produto){
    produtoRepository.delete(produto);
  }
}
