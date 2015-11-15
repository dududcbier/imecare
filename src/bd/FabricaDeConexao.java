/* Exemplo adaptado de:  http://www.caelum.com.br/apostila-java-web/bancos-de-dados-e-jdbc/  */

package bd;

import java.sql.*;
import java.util.Properties;

public class FabricaDeConexao {

	public static final String NOME_DRIVER = "org.postgresql.Driver";
	public static final String URL_BD = "jdbc:postgresql://localhost:5432/imecare";
	public static final String USUARIO_BD = "postgres";
	public static final String SENHA_BD = "";

	private static FabricaDeConexao fabricaDeConexao = null;

	private FabricaDeConexao() {
		try {
			Class.forName(NOME_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection obterConexao() {

		Properties props = new Properties();
		props.setProperty("user", USUARIO_BD);
		props.setProperty("password", SENHA_BD);

		try {
			return DriverManager.getConnection(URL_BD, props);
		} catch (SQLException e) {
			// A SQLException é "encapsulada" em uma RuntimeException
			// para desacoplar o código da API de JDBC
			throw new RuntimeException(e);
		}
	}
	
	public static FabricaDeConexao obterInstancia() {
		// A fábrica é um singleton
		if (fabricaDeConexao == null) {
			fabricaDeConexao = new FabricaDeConexao();
		}
		return fabricaDeConexao;
	}

}
