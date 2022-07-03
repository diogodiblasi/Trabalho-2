package Swing;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * Projeto desenvolvido para Cadastrar medicamentos em uma farmacia.
 * @author Diogo Di Blasi
 * @see Swing.Produtos
 * @see Swing.Conexao
 * 
 */

public class Swing {
	public static void main(String[] args) {

		JFrame janela = new JFrame("Medicamentos"); 
		janela.setResizable(false); 
		janela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		janela.setSize(400, 300); 
		
		Container caixa = janela.getContentPane();
		caixa.setLayout(null);
		
		JLabel labelProdutoid = new JLabel("Produto id: ");
		JLabel labelQuantidade = new JLabel("Quantidade: ");
		JLabel labelNome = new JLabel("Nome: ");
		JLabel labelPreco = new JLabel("Preco ");


		labelProdutoid.setBounds(50, 40, 100, 20); 
		labelQuantidade.setBounds(50, 80, 150, 20);
		labelNome.setBounds(50, 120, 100, 20); 
		labelPreco.setBounds(50, 140, 150, 20); 

		JTextField jTextProdutoid = new JTextField();
		JTextField jTextQuantidade = new JTextField();
		JTextField jTextNome = new JTextField();
		JTextField jTextPreco = new JTextField();

		jTextProdutoid.setEnabled(true);
		jTextQuantidade.setEnabled(true);
		jTextNome.setEnabled(true);
		jTextPreco.setEnabled(true);

		jTextProdutoid.setBounds(180, 40, 50, 20);
		jTextQuantidade.setBounds(180, 80, 50, 20);
		jTextNome.setBounds(180, 120, 150, 20);
		jTextPreco.setBounds(180, 140, 50, 20);

		janela.add(labelProdutoid);
		janela.add(labelQuantidade);
		janela.add(labelNome);
		janela.add(labelPreco);
		janela.add(jTextProdutoid);
		janela.add(jTextQuantidade);
		janela.add(jTextNome);
		janela.add(jTextPreco);
		
		JButton botaoConsultar = new JButton("Consultar");
		botaoConsultar.setBounds(250, 40, 100, 20);
		janela.add(botaoConsultar);
		JButton botaoGravar = new JButton("Gravar");
		botaoGravar.setBounds(50, 200, 100, 20);
		botaoGravar.setEnabled(true);
		janela.add(botaoGravar);
		JButton botaoLimpar = new JButton("Limpar");
		botaoLimpar.setBounds(250, 200, 100, 20);
		janela.add(botaoLimpar);
		JButton botaoDeletar = new JButton("Deletar");
		botaoDeletar.setBounds(50, 170, 100, 20);
		janela.add(botaoDeletar);
		botaoDeletar.setEnabled(false);

		Cadastros Produtos = new Cadastros();

		botaoConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int produtoid = Integer.parseInt(jTextProdutoid.getText());
					int quantidade;
					double preco;
					
					botaoGravar.setEnabled(true);
					String nome;

					if (!Produtos.consultarProduto(produtoid))
						nome = "";
					else {
						nome = Produtos.getNome();
						jTextNome.setText(nome);
						quantidade = Produtos.getQuantidade();
						jTextQuantidade.setText(String.valueOf(quantidade) );
						preco= Produtos.getPreco();
						jTextPreco.setText(String.valueOf(preco));
						
					}
					jTextProdutoid.setEnabled(false);
					jTextQuantidade.setEnabled(true);
					botaoConsultar.setEnabled(false);
					jTextNome.setEnabled(true);
					botaoGravar.setEnabled(true);
					botaoDeletar.setEnabled(true);
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(janela,
							"Preencha o id do Medicamento");
				}
			}
		});
	
		botaoGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
				int produtoid = Integer.parseInt(jTextProdutoid.getText());
				int quantidade = Integer.parseInt(jTextQuantidade.getText());
				String nome = jTextNome.getText().trim(); 
				 double preco = Double.parseDouble(jTextPreco.getText());
				 
				if (nome.length()==0) {
					JOptionPane.showMessageDialog(janela, "Preencha o nome do produto");
					jTextNome.requestFocus();
				}
				else {
					if (!Produtos.consultarProduto(produtoid)) {
						if (!Produtos.cadastrarProduto(produtoid, quantidade, nome, preco))
							JOptionPane.showMessageDialog(janela, "Erro na inclusão do produto!");
						else
							JOptionPane.showMessageDialog(janela, "Inclusão realizada!");
					} else {
						if (!Produtos.atualizarMedicamento(produtoid, nome, quantidade, preco))
							JOptionPane.showMessageDialog(janela, "Erro na atualização do produto!");
						else
							JOptionPane.showMessageDialog(janela, "Alteração realizada!");
					}

				}
				
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(janela,
						"Preencha todos os campos");			}
			
			}
		});
		botaoLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextProdutoid.setText(""); 
				jTextQuantidade.setText(""); // 
				jTextNome.setText("");
				jTextPreco.setText("");

				jTextProdutoid.setEnabled(true);
				jTextQuantidade.setEnabled(true);
				jTextNome.setEnabled(true);
				botaoConsultar.setEnabled(true);
				botaoGravar.setEnabled(true);
				jTextProdutoid.requestFocus(); 
			}
		});
			
		botaoDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numprodutoid = Integer.parseInt(jTextProdutoid.getText());
				Produtos.deletarProduto(numprodutoid);
				jTextProdutoid.setText(""); 
				jTextQuantidade.setText(""); 
				jTextNome.setText(""); 
				jTextPreco.setText("");
				jTextProdutoid.setEnabled(true);
				jTextQuantidade.setEnabled(true);
				jTextNome.setEnabled(true);
				botaoConsultar.setEnabled(true);
				botaoGravar.setEnabled(true);
				jTextProdutoid.requestFocus(); 
				JOptionPane.showMessageDialog(janela,"Produto deletado com sucesso!");	

			}
		});


		janela.setVisible(true);
	}

}
