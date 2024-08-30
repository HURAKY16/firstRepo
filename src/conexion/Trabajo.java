/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class Trabajo {

   static final String URL = "jdbc:mysql://localhost:3306/java";
   static final String USUARIO = "root";
   static final String CLAVE = "";
   
   public static Connection obtenerConexion() {
   Connection conexion = null;
   try {
       Class.forName("com.mysql.cj.jdbc.Driver");
       conexion = DriverManager.getConnection(URL,USUARIO,CLAVE);
       System.out.print("¡conexion exitosa!");
       } catch(SQLException e){
       System.out.print("Error al encontrar a la base de datos: "+ e.getMessage());
       } catch(ClassNotFoundException e){
       System.out.println("no se encontro la clase del controlador: "+ e.getMessage());
       }    
    return conexion;
   }
   
   public static void cerrarConexion (Connection conexion){
   if (conexion != null ){
        try {
            conexion.close();
            System.out.println(" Conexion cerrada correctamente.");
            }catch (SQLException e) {
            System.out.println("Error al cerrar la conexion "+ e.getMessage());
            }
        }
   }
 
   
   
   public static void main(String[] args) throws SQLException {
   Connection conexion = obtenerConexion();
   if (conexion != null){
   Statement statement = null;
   ResultSet resultSet = null;
   try {
       //Crear la setencia SQL  para seleccionar todos los registros de la tabla NetBeans
   String consulta ="SELECT * FROM java1";
   statement = conexion.createStatement();
   //Ejecutar la consulta 
   resultSet = statement.executeQuery(consulta);
   //Recorrer los resultados y mostrarlos
   while(resultSet.next()){
  String carrera = resultSet.getString("Carrera");
  String nombre = resultSet.getString("nombre");
   String genero = resultSet.getString("Genero");
  int edad = resultSet.getInt("Edad");
  //Aqui puedes ontener otros campos segun la estructura de tu tabla
  System.out.println("Carrera: " + carrera + ",Nombre: " + nombre +",Genero: "+genero + ", Edad: "+edad );
   } 
   }
   catch(SQLException e){
   System.out.println("Error al ejecutar la consulta: "+ e.getMessage());
   }finally{
   //Cerrar el statement y el resultSet
   if (resultSet != null){
      try{
      resultSet.close();
      } catch(SQLException e){
      System.out.println("Error al cerrar el ResultSet: "+ e.getMessage());
      }
   }
   if (statement != null){
   try{
   statement.close();
   }catch(SQLException e){
   System.out.println("Error al cerrar el Statement: " + e.getMessage());
   }
   }
   }
   }
   cerrarConexion(conexion);
   }
   
}