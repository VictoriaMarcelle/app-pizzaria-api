package com.pizzaria.app.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.pizzaria.app.enums.TamanhoProduto;
import com.pizzaria.app.models.Produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoForm {
  @NotBlank(message = "Campo nome é obrigatório")
  @Length(min = 12, max = 50)
  private String nome;

  @NotBlank(message = "Campo descrição é obrigatório")
  private String descricao;

  @NotNull(message = "Campo valor é obrigatório")
  private BigDecimal valor;

  @NotNull(message = "Campo tamanho é obrigatório")
  private TamanhoProduto tamanho;

  @NotNull(message = "Campo quantidade de fatias é obrigatório")
  private int qtdFatias;

  public Produto converter() {
		return new Produto(null, this.getNome(), this.getValor(), this.getDescricao(), this.getQtdFatias(), this.getTamanho());
	}
}
