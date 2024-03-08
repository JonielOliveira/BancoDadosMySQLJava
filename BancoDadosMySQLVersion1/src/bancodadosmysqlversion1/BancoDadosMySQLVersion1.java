
package bancodadosmysqlversion1;

import java.sql.*;

public class BancoDadosMySQLVersion1 {
    
    // Configurações de conexão com o banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASSWORD = "a1b2c3d4";
    private static final String DATABASE_NAME = "ClinicaVeterinaria";
    
    public static Connection connection = null;
    public static Statement statement = null;

    
    public static void main(String[] args) {
        //Connection connection = null;
        //public static Statement statement = null;

        try {
            // Conectar ao banco de dados
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();

            // Verificar se o banco de dados já existe
            ResultSet resultSet = connection.getMetaData().getCatalogs();
            boolean databaseExists = false;
            while (resultSet.next()) {
                String databaseName = resultSet.getString(1);
                if (databaseName.equalsIgnoreCase(DATABASE_NAME)) {
                    databaseExists = true;
                    break;
                }
            }
            resultSet.close();

            // Se o banco de dados não existir, criá-lo e popular com algumas tuplas
            if (!databaseExists) {
                statement.executeUpdate("CREATE DATABASE " + DATABASE_NAME);
                statement.executeUpdate("USE " + DATABASE_NAME);

                // Criar tabela e inserir algumas tuplas
                createTabelaCliente();

                System.out.println("Banco de dados criado e populado com sucesso.");
            } else {
                System.out.println("Banco de dados já existe.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechar conexão
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void createTabelaCliente(){
        
        try{
            
            String comandoSQL;
            
            comandoSQL = """
                         CREATE TABLE Cliente (
                           ID INT PRIMARY KEY AUTO_INCREMENT,
                           Nome VARCHAR(255) NOT NULL,
                           Telefone VARCHAR(20) NOT NULL,
                           Email VARCHAR(255) NOT NULL,
                           Endereco_ID INT,
                           DataNascimento DATE,
                           CPF VARCHAR(14) NOT NULL
                         )
                         """;
         
            statement.executeUpdate(comandoSQL);
            
            comandoSQL = """
                         INSERT INTO Cliente (Nome, Telefone, Email, Endereco_ID, DataNascimento, CPF) 
                         VALUES ('Joseph', '(12) 98181-7878', 'joseph@gmail.com', 1, '1991-03-08', '898.475.158-77')
                         """;
            
            statement.executeUpdate(comandoSQL);
            
            comandoSQL = """
                         INSERT INTO Cliente (Nome, Telefone, Email, Endereco_ID, DataNascimento, CPF) 
                         VALUES ('Maria', '(12) 96565-1212', 'maria@hotmail.com', 2, '2001-05-15', '148.462.872-21')
                         """;
            
            statement.executeUpdate(comandoSQL);
            
            comandoSQL = """
                         INSERT INTO Cliente (Nome, Telefone, Email, Endereco_ID, DataNascimento, CPF) 
                         VALUES ('Joao', '(12) 94878-5962', 'joao@yahoo.com.br', 3, '1992-07-23', '145.145.124-33')
                         """;
            
            statement.executeUpdate(comandoSQL);
            
            comandoSQL = """
                         INSERT INTO Cliente (Nome, Telefone, Email, Endereco_ID, DataNascimento, CPF) 
                         VALUES ('Luiza', '(12) 94141-6363', 'lulu@outlook.com', 4, '1980-09-20', '478.246.195-72')
                         """;
            
            statement.executeUpdate(comandoSQL);
             
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }
    
}
