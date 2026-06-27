package br.com.petcare.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConexaoDB {

    private static final String URL_PADRAO = "jdbc:postgresql://localhost:5432/petCare";
    private static final String USUARIO_PADRAO = "postgres";

    private ConexaoDB() {
    }

    public static Connection getConexao() throws SQLException {
        String url = obterConfiguracao("petcare.db.url", "PETCARE_DB_URL", URL_PADRAO);
        String usuario = obterConfiguracao("petcare.db.user", "PETCARE_DB_USER", USUARIO_PADRAO);
        String senha = obterConfiguracao("petcare.db.password", "PETCARE_DB_PASSWORD", "");

        return DriverManager.getConnection(url, usuario, senha);
    }

    private static String obterConfiguracao(String propriedade, String variavelAmbiente, String valorPadrao) {
        String valor = System.getProperty(propriedade);

        if (valor == null || valor.isBlank()) {
            valor = System.getenv(variavelAmbiente);
        }

        return valor == null || valor.isBlank() ? valorPadrao : valor;
    }
}
