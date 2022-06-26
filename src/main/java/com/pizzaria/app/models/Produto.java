package com.pizzaria.app.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.pizzaria.app.enums.TamanhaProduto;

import javax.persistence.GenerationType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
  
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 50)
  private String nome;

  private BigDecimal valor;

  private String descricao;

  @Column(length = 50)
  private int qtdFatias;

  @Enumerated(EnumType.STRING)
  private TamanhaProduto tamanho;
}
