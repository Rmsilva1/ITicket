package service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.logging.Logger;

public final class Conexao {

	Logger log;

	private static Connection myConnection;

	public static void main(String[] args) {
		System.out.println("\nObtendo a conexao...");
		connect();
		System.out.println("\nFechando a conexao...\n");
		disconnect();
	}

	public static Connection connect() {

		try {

			if (myConnection != null) {
				if (!myConnection.isClosed())
					return myConnection;
			}

			InputStream arqEntrada = Conexao.class.getResourceAsStream("jdbc.properties");
			if (arqEntrada == null)
				throw new IOException("Arquivo de configuracao 'jdbc.properties' nao existe no diretorio do pacote!");
			Properties propriedades = new Properties();
			propriedades.load(arqEntrada);
			arqEntrada.close();

			String driver = propriedades.getProperty("driver");
			if (driver == null || driver.isEmpty())
				throw new InvalidPropertiesFormatException(
						"Propriedade 'driver' nao existe ou esta em branco no arquivo 'jdbc.properties'!");
			String url = propriedades.getProperty("url");
			if (url == null || url.isEmpty())
				throw new InvalidPropertiesFormatException(
						"Propriedade 'url' nao existe ou esta em branco no arquivo 'jdbc.properties'!");
			String nomeLog = propriedades.getProperty("log");
			String user = propriedades.getProperty("user");
			String password = propriedades.getProperty("password");

			if (nomeLog != null && !nomeLog.isEmpty()) {
				PrintWriter log = new PrintWriter(new FileWriter(nomeLog, true));
				DriverManager.setLogWriter(log);
				DriverManager.println("|-------> Iniciando log.....");
			}

			DriverManager.println("|-------> Registrando o driver.....");
			Class.forName(driver);
			DriverManager.println("|-------> Realizando a conexao.....");
			myConnection = DriverManager.getConnection(url, user, password);
			DriverManager.println("|-------> Conexao estabelecida.....");

		} catch (ClassNotFoundException ex) {
			System.out.println("Classe do Driver nao encontrada!");
			System.exit(9);
		} catch (IOException ex) {
			System.out.println("Problemas na criacao do arquivo de log!");
			System.exit(9);
		} catch (SQLException ex) {
			System.out.println("Problemas na conexao com o banco de dados!");
			System.exit(9);
		}

		return myConnection;

	}

	public static void disconnect() {

		try {

			DriverManager.println("|-------> Fechando a conexao.....");
			myConnection.close();
			DriverManager.println("|-------> Verificando a conexao apos fechamento.....");

			if (myConnection.isClosed()) {
				DriverManager.println("|-------> Conexao fechada.....");
			} else {
				DriverManager.println("|-------> Conexao aberta.....");
			}

			myConnection = null;

		} catch (SQLException ex) {
			System.out.println("Problemas no fechamento da conexao com o banco de dados!");
			System.exit(9);
		}

	}

}