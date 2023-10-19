package com.example.demo;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BD {
    public BD() {
    }

    public static String select_document(String document_identity) throws ClassNotFoundException, SQLException {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/identities";
        String username = "root";
        String password = "";

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, username, password);

        String consultaSQL = "SELECT * FROM identity WHERE document = ?";

        PreparedStatement statement = connection.prepareStatement(consultaSQL);
        statement.setString(1, document_identity);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            document_identity = resultSet.getString("document");
            return document_identity;

        }
        // Cerrar recursos
        resultSet.close();
        statement.close();
        connection.close();
        return "";
    }

    public static int DeleteRelation(String id) throws ClassNotFoundException, SQLException {
       
        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/identities";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        String sentenciaSQL = "DELETE FROM relations WHERE id = ?";
        PreparedStatement prepareStatement = connection2.prepareStatement(sentenciaSQL);
        prepareStatement.setString(1, id);
        int f = prepareStatement.executeUpdate();

        if (f > 0){
            return 1;
        }else {
            return 0;
        }
    }

    public static int DeleteIdentity(String id) throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/identities";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        String sentenciaSQL = "DELETE FROM identity WHERE document = ?";
        PreparedStatement prepareStatement = connection2.prepareStatement(sentenciaSQL);
        prepareStatement.setString(1, id);
        int f = prepareStatement.executeUpdate();

        if (f > 0){
            return 1;
        }else {
            return 0;
        }
    }

    public Identity register(String document, String name, String cellphone, String address) {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/identities";
        String username = "root";
        String password = "";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM identity");

            String sql = "INSERT INTO identity (document , name, cellphone, address) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, document);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, cellphone);
            preparedStatement.setString(4, address);

            int files = preparedStatement.executeUpdate();

            if (files > 0) {
                System.out.println("La identidad se registro de manera exitosa.");
                return new Identity(document, name, cellphone, address);
            } else {
                System.out.println(Errors.error_register);
            }

            preparedStatement.close();
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Identity(null,null,null,null);
    }

    public Relations register_relations(String id, String name, String ally, String document_identity) {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/identities";
        String username = "root";
        String password = "";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM relations");

            String sql = "INSERT INTO relations (id, name , ally, document_identity) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, ally);
            preparedStatement.setString(4, document_identity);

            int files = preparedStatement.executeUpdate();

            if (files > 0) {
                System.out.println("La relacion se registro de manera exitosa.");
                return new Relations(id, name, ally, document_identity);
            } else {
                System.out.println(Errors.error_register_relations);
            }

            preparedStatement.close();
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Relations(null,null,null, null);
    }

    public List<Identity> search_identity() throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/identities";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        ResultSet resultSet2 = statement2.executeQuery("SELECT * FROM identity");
        List<Identity> list = new ArrayList<>();

        while(resultSet2.next()){

            String document = resultSet2.getString("document");
            String name = resultSet2.getString("name");
            String cellphone = resultSet2.getString("cellphone");
            String address = resultSet2.getString("address");

            Identity identity = new Identity(document, name, cellphone, address);
            list.add(identity);
        }
        return list;
    }

    public List<Relations> search_relations() throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/identities";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();

        ResultSet resultSet2 = statement2.executeQuery("SELECT * FROM relations");
        List<Relations> list = new ArrayList<>();

        while(resultSet2.next()){

            String id = resultSet2.getString("id");
            String name = resultSet2.getString("name");
            String ally = resultSet2.getString("ally");
            String document_identity = resultSet2.getString("document_identity");

            Relations relations = new Relations(id, name,ally, document_identity);
            list.add(relations);
        }
        return list;
    }

    public Relations editRelation(String id, String name, String ally, String document_identity) throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/identities";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();
        String consulta = "UPDATE relations SET name = ?, ally = ?, document_identity = ? WHERE id = ?";

        PreparedStatement preparedStatement = connection2.prepareStatement(consulta);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, ally);
        preparedStatement.setString(3, document_identity);
        preparedStatement.setString(4, id);

        int filasActualizadas = preparedStatement.executeUpdate();
        if (filasActualizadas > 0) {
            System.out.println("Relacion actualizada exitosamente");
            return new Relations(id, name, ally, document_identity);
        } else {
            System.out.println(Errors.error_edit);
        }

        preparedStatement.close();
        connection2.close();

        return new Relations(Errors.error_edit,null,null,null);
    }

    public Identity editIdentity(String document, String name, String cellphone, String address) throws ClassNotFoundException, SQLException {

        String driver2 = "com.mysql.cj.jdbc.Driver";
        String url2 = "jdbc:mysql://localhost:3306/identities";
        String username2 = "root";
        String pass2 = "";

        Class.forName(driver2);
        Connection connection2 = DriverManager.getConnection(url2, username2, pass2);

        Statement statement2 = connection2.createStatement();
        String consulta = "UPDATE identity SET name = ?, cellphone = ?, address = ? WHERE document = ?";

        PreparedStatement preparedStatement = connection2.prepareStatement(consulta);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, cellphone);
        preparedStatement.setString(3, address);
        preparedStatement.setString(4, document);

        int filasActualizadas = preparedStatement.executeUpdate();
        if (filasActualizadas > 0) {
            System.out.println("Identidad actualizada exitosamente");
            return new Identity(document, name, cellphone, address);
        } else {
            System.out.println(Errors.error_editIdentity);
        }

        preparedStatement.close();
        connection2.close();

        return new Identity(Errors.error_editIdentity,null,null,null);
    }
    }







