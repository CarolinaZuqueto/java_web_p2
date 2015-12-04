package model.dto;

/**
 * 
 * Informações de um Produto
 *
 * @author Carolina Zuqueto
 */
public class Produto {
	
    Integer codigo;
    String descricao; 
    String nome;
    Integer quantidade;
    Double valor;   
    
    public Produto() {
	}

	public Produto(Integer codigo, String descricao, String nome,
			Integer quantidade, Double valor) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.nome = nome;
		this.quantidade = quantidade;
		this.valor = valor;
	}

	public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
		return descricao;
	}	
    
    public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
    
    public String getNome() {
		return nome;
	}
    
    public void setNome(String nome) {
		this.nome = nome;
	}
    
    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    
    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

}
