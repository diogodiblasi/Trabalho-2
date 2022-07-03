package Swing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public abstract class Produtos {
	private int produtoid;
	private int quantidade;
	private String nome;
	protected double preco;

	Produtos() {
		this.nome = "";
	}

	Produtos(int numProdutoid, int numQuantidade) {
		this();
		this.produtoid = numProdutoid;
		this.quantidade = numQuantidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getProdutoid() {
		return produtoid;
	}

	public void setProdutoid(int produtoid) {
		this.produtoid = produtoid;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(int preco) {
		this.preco = preco;
	}


	public boolean cadastrarProduto(int numProdutoid, int numQuantidade, String nome, double preco) {

		Connection conexao = null;
		try {
			conexao = Conexao.conectaDiogodiblasi();
			String sql = "insert into farmacia set produtoid=?, nome=?, preco=?, quantidade=?";
			PreparedStatement ps = conexao.prepareStatement(sql);

			ps.setInt(1, numProdutoid); 
			ps.setString(2, nome); 
			ps.setDouble(3, preco);  
			ps.setInt(4, numQuantidade);
			int totalRegistrosAfetados = ps.executeUpdate();
			if (totalRegistrosAfetados == 0) {
				System.out.println("Não foi feito o cadastro!!");
				return false;
			}
			System.out.println("Cadastro realizado!");
			return true;
		} catch (SQLException erro) {
			System.out.println("Erro ao cadastrar Medicamento: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}

	public void deletarProduto(int numProdutoid) {
		Connection conexao = null;
		try {
			conexao = Conexao.conectaDiogodiblasi();
			String sql = "delete from farmacia where produtoid=?" ;
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, numProdutoid);
			ps.execute();
		} catch (SQLException erro) {
			System.out.println("Erro ao deletar Medicamento: " + erro.toString());
		} finally {
			Conexao.fechaConexao(conexao);
		}
	}
	public boolean consultarProduto(int numProdutoid) {
		Connection conexao = null;
		try {
			conexao = Conexao.conectaDiogodiblasi();
			String sql = "select * from farmacia where produtoid=? ";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setInt(1, numProdutoid); 
			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) { 
				return false; 
			} else {
				while (rs.next()) {
					this.produtoid = rs.getInt("Produtoid");
					this.quantidade = rs.getInt("Quantidade");
					this.nome = rs.getString("Nome");
					this.preco = rs.getDouble("Preco");
				}
				return true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar Medicamentos: " + erro.toString());
			return false;
		} finally {
			Conexao.fechaConexao(conexao);
		}
}


	public boolean atualizarMedicamento(int numProdutoid, String nome, int quantidade, double preco) {

		if (!consultarProduto(numProdutoid))
			return false;
		else {
			Connection conexao = null;
			try {
				conexao = Conexao.conectaDiogodiblasi();
				
				String sql = "update farmacia set nome=?, quantidade=?, preco=? where produtoid=?";
				PreparedStatement ps = conexao.prepareStatement(sql);
				ps.setString(1, nome);
				ps.setInt(2, quantidade);
				ps.setDouble(3, preco);
				ps.setInt(4, numProdutoid);
				int totalRegistrosAfetados = ps.executeUpdate();
				if (totalRegistrosAfetados == 0)
					System.out.println("Não foi feita a atualização!");
				else
					System.out.println("Atualização realizada!");
				return true;
			} catch (SQLException erro) {
				System.out.println("Erro ao atualizar Medicamentos: " + erro.toString());
				return false;
			} finally {
				Conexao.fechaConexao(conexao);
			}
		}
	}
}