import java.sql.*;
import java.util.Scanner;

public class MuleSoftAssesment {
    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys","root","aaaa@123A");
        Statement stmt = con.createStatement();
        String create_database = "CREATE DATABASE MOVIES";
        stmt.executeUpdate(create_database);
        String use_database = "USE MOVIES";
        stmt.executeUpdate(use_database);
        String create_table = "CREATE TABLE MOVIES (lead_actor VARCHAR(225),actress VARCHAR(225),year int,director VARCHAR(225))";
        stmt.executeUpdate(create_table);
        String insert_into_movies_table = "INSERT INTO MOVIES VALUES(?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(insert_into_movies_table);
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("Enter Actor Name : ");
            String actor_name = sc.nextLine();
            System.out.println("Enter Actress Name : ");
            String actress_name = sc.nextLine();
            System.out.println("Enter Year Of Release : ");
            int year_of_release = sc.nextInt();
            sc.nextLine();// to avoid skipping of string input after int input
            System.out.println("Enter Director Name : ");
            String director_name = sc.nextLine();
            pstmt.setString(1,actor_name);
            pstmt.setString(2,actress_name);
            pstmt.setInt(3,year_of_release);
            pstmt.setString(4,director_name);
            pstmt.executeUpdate();
            System.out.println("Do You Want To Enter More Entries [YES/NO] : ");
            String input = sc.nextLine();
            if(input.equalsIgnoreCase("NO")){
                break;
            }
        }
        PreparedStatement pstmt1 = con.prepareStatement("SELECT * FROM MOVIES");
        ResultSet result1 = pstmt1.executeQuery();
        System.out.println("Display All Rows Of Table : ");
        System.out.println("---------------------------");
        while (result1.next()){
            System.out.println(result1.getString(1)+"  "+result1.getString(2)+"  "+result1.getInt(3)+"  "+result1.getString(4)+"  ");
            System.out.println();
        }
        PreparedStatement pstmt2 = con.prepareStatement("SELECT * FROM MOVIES WHERE lead_actor =\"PRABHAS\"");
        ResultSet result2 = pstmt2.executeQuery();
        System.out.println("Display Rows Of Table Based On a Condition (actor_name) : ");
        System.out.println("---------------------------");
        while (result2.next()){
            System.out.println(result2.getString(1)+"  "+result2.getString(2)+"  "+result2.getInt(3)+"  "+result2.getString(4)+"  ");
            System.out.println();
        }
    }
}
