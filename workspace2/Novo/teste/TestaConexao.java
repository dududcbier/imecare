/* Exemplo adaptado de:  http://www.caelum.com.br/apostila-java-web/bancos-de-dados-e-jdbc/  */

package teste;
import java.sql.Connection;
import java.sql.SQLException;

import bd.FabricaDeConexao;


public class TestaConexao {
	public static void main(String[] args) throws SQLException{
		Connection conexao = FabricaDeConexao.obterInstancia().obterConexao();
		System.out.println("Conectado");
		conexao.close();
	}
}
