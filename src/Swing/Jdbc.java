package Swing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.Scanner;

public class Jdbc {

	public static void main(String[] args) {
		try {
			String url = "jdbc:mysql://localhost/diogodiblasi";
			String user = "root"; 
			String password = ""; 
			Connection conn = DriverManager.getConnection(url, user, password);

			String sql = "select * from farmacia where produtoid=?";

			PreparedStatement ps = conn.prepareStatement(sql);

			Scanner sc = new Scanner(System.in);
			System.out.print("Entre com o número de identificação do medicamento = ");
			int produtoPesquisa = Integer.parseInt(sc.nextLine());
			ps.setInt(1, produtoPesquisa);		

			ResultSet rs = ps.executeQuery();
			if (!rs.isBeforeFirst()) { 
				System.out.println("Não foram encontrados registros!");
			}
			else
			{

				int totalRegistros = 0;
				while (rs.next()) {
				    int produtoid = rs.getInt("produtoid");
				    int quantidade = rs.getInt("Quantidade");
				    String nome = rs.getString("Nome");
				    double preco = rs.getDouble("Preco");

				    System.out.println("Número do registro = " + rs.getRow());
				    System.out.println("Produto id:" + produtoid);
				    System.out.println("Quantidade:" + quantidade);
				    System.out.println("Nome:" + nome);
				    System.out.println("Preco: " + NumberFormat.getCurrencyInstance().format(preco));
				    System.out.println();
				    totalRegistros++;
				}
				System.out.println("Total de registros = " + totalRegistros);
			}
			sc.close();
		} catch (Exception erro) {
			System.out.println("Erro: " + erro.toString());
		}
	}
}