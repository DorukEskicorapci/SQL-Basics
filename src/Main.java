import java.sql.*;
import java.util.Scanner;

public class Main {
    static final String URL = "jdbc:sqlite:db.db";

    static void operator() throws SQLException {


        System.out.println("1.Select \n2.Insert \n3.Delete \n4.Update \n5.Exit ");
        System.out.print("\nEnter command number or name: ");


        Scanner sc = new Scanner(System.in);
        Integer input = sc.nextInt();
        boolean count = false;

        while (input < 5){

            if (count){

                System.out.println("===========================================================================");
                operator();

            } else {
                count = true;
            }


            if (input == 1) {

                System.out.println();
                sqlcommands.select();

            } else if (input == 2) {

                System.out.print("\nType the username that will be assigned to your data: ");
                String usernameinut = sc.next();
                System.out.print("Type the name that will be assigned to your data: ");
                String nameinput = sc.next();
                System.out.print("Type the surname that will be assigned to your data: ");
                String surnameinput = sc.next();
                System.out.print("Type the age that will be assigned to your data: ");
                Integer ageinput = sc.nextInt();
                System.out.print("\n");
                sqlcommands.insert(usernameinut,nameinput,surnameinput,ageinput);

            } else if (input == 3) {

                System.out.print("\nType the username assigned to data you want to delete: ");

                String deleteinput = sc.nextLine();

                sqlcommands.delete(deleteinput);
                System.out.print("\n");

            } else if (input == 4) {
                System.out.print("\nType the username assigned to data you want to update: ");

                String updateinput = sc.next();
                sqlcommands.update(updateinput);

            } else if (input == 5) {
                break;
            }
        }

    }


    public static void main(String[] args) throws SQLException {
        driver();
    }

    static void driver() throws SQLException {

        Connection connection = null;
        Scanner sc = new Scanner(System.in);

        try{
            connection = getConnection();
            System.out.println("Connecting...");
            Statement statement = connection.createStatement();


            try {
                if (connection != null) {
                    System.out.println("Connected and running \n");

                    //static final String URL = "jdbc:sqlite:db.db";


                    operator();


                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                System.out.print("\n1. Yes \n2. No" + "\nDo you want to try connect again?: ");

                String input = sc.nextLine();

                if (input.equalsIgnoreCase("1") || input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("1.yes")) {
                    driver();
                } else if (input.equalsIgnoreCase("2") || input.equalsIgnoreCase("no") || input.equalsIgnoreCase("2.no")) {};

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }


    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection(URL);
    }

}
