package com.example.demo;



import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnector {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/libreria?useSSL=false";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "password";



    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }


    public static ArrayList<String> buscarRol() {
        String selectSql = "SELECT nombre FROM roles";
        ArrayList<String> roles = new ArrayList<String>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");

                // Imprimir los detalles del usuario encontrado en la consola
                System.out.println("Nombre: " + nombre);

                //Arraylist
                roles.add(nombre);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return roles;
    }

    public static String insertUsuario(
            String idUsuario,
            String nombre,
            String estado,
            String telefono,
            String direccion,
            String email,
            String cedula,
            String password
    ) {
        String insertSql = "INSERT INTO usuarios (id_usuario, nombre, estado, telefono, direccion, email, cedula, password) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSql)) {
            preparedStatement.setString(1, idUsuario);
            preparedStatement.setString(2, nombre);
            preparedStatement.setString(3, estado);
            preparedStatement.setString(4, telefono);
            preparedStatement.setString(5, direccion);
            preparedStatement.setString(6, email);
            preparedStatement.setString(7, cedula);
            preparedStatement.setString(8, password);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return ("La sentencia se ejecutó con éxito y afectó " + rowsAffected + " filas.");
            } else {
                return("La sentencia se ejecutó, pero no afectó ninguna fila.");
            }
        } catch (SQLException e) {
            return(e.toString());
        }
    }

    public static String editUsuario(
            String idUsuario,
            String nombre,
            String estado,
            String telefono,
            String direccion,
            String email,
            String cedula,
            String password
    ) {
        String updateSql = "UPDATE usuarios " +
                "SET nombre = ?, estado = ?, telefono = ?, direccion = ?, email = ?, cedula = ?, password = ? " +
                "WHERE id_usuario = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, estado);
            preparedStatement.setString(3, telefono);
            preparedStatement.setString(4, direccion);
            preparedStatement.setString(5, email);
            preparedStatement.setString(6, cedula);
            preparedStatement.setString(7, password);
            preparedStatement.setString(8, idUsuario);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return ("La sentencia se ejecutó con éxito y afectó " + rowsAffected + " filas.");
            } else {
                return("La sentencia se ejecutó, pero no afectó ninguna fila.");
            }
        } catch (SQLException e) {
            return(e.toString());
        }
    }

    public static String[] buscarUsuario(String idUsuario) {
        String selectSql = "SELECT * FROM usuarios WHERE id_usuario = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setString(1, idUsuario);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id_usuario");
                String nombre = resultSet.getString("nombre");
                String estado = resultSet.getString("estado");
                String telefono = resultSet.getString("telefono");
                String direccion = resultSet.getString("direccion");
                String email = resultSet.getString("email");
                String cedula = resultSet.getString("cedula");
                String password = resultSet.getString("password");

                // Imprimir los detalles del usuario encontrado en la consola
                System.out.println("ID de Usuario: " + id);
                System.out.println("Nombre: " + nombre);
                System.out.println("Estado: " + estado);
                System.out.println("Teléfono: " + telefono);
                System.out.println("Dirección: " + direccion);
                System.out.println("Email: " + email);
                System.out.println("Cédula: " + cedula);
                System.out.println("Password: " + password);

                String[] user = { nombre, estado, telefono, direccion, email, cedula, password };
                return user;

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            return null;
        }
        return null;
    }

    public static void eliminarUsuario(int idUsuario) {
        String deleteSql = "DELETE FROM usuarios WHERE id_usuario = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {
            preparedStatement.setInt(1, idUsuario);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Usuario eliminado con éxito.");
            } else {
                System.out.println("No se encontró un usuario con ese ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean autenticarUsuario(String cedula, String password) {
        String selectSql = "SELECT * FROM usuarios WHERE cedula = ? AND password = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setString(1, cedula);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next(); // Si se encuentra una coincidencia, la autenticación es exitosa
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // En caso de error, se considera una autenticación fallida
        }
    }
}
