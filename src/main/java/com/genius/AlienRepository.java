package com.genius;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
public class AlienRepository {
    Connection connection = null;
    public AlienRepository(){
        String url = "jdbc:mysql://localhost:3306/RESTAPI";
        String user = "root";
        String password = "Ayomide20?";
        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            connection = DriverManager.getConnection(url,user,password);
            Class.forName("com.mysql.cj.jdbc.Driver"); // or your specific driver
            this.connection = DriverManager.getConnection(url,user,password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public List<Alien> getAliens() {
        List<Alien> aliens = new ArrayList<>();
        String sql = "SELECT * FROM aliens";
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Alien alien = new Alien();
                alien.setId(resultSet.getInt(1));
                alien.setName(resultSet.getString(2));
                alien.setPoints(resultSet.getInt(3));
                aliens.add(alien);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return aliens;
    }

    public Alien getAlien(int id) {
        Alien alien = new Alien();
        String sql = "SELECT * FROM aliens WHERE id="+id;
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                alien.setId(resultSet.getInt(1));
                alien.setName(resultSet.getString(2));
                alien.setPoints(resultSet.getInt(3));

            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return alien;
    }

    public void create(Alien a1){
        String sql = "INSERT INTO aliens values (?,?,?) ";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,a1.getId());
            preparedStatement.setString(2,a1.getName());
            preparedStatement.setInt(3,a1.getPoints());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void update(Alien a1){
        String sql = "UPDATE aliens SET name = ?,points=? WHERE id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,a1.getName());
            preparedStatement.setInt(2,a1.getPoints());
            preparedStatement.setInt(3,a1.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id){
        String sql = "DELETE FROM aliens WHERE id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}