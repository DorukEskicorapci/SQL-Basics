import java.sql.*;
import java.util.Scanner;

public class sqlcommands {
    static final String URL = "jdbc:sqlite:db.db";



    static void insert(String username_in, String name_in, String surname_in, Integer age_in) throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(URL);
        Statement statement = connection.createStatement();
        boolean checkbox = true;

        username_in = "'" + username_in + "'";
        name_in = "'" + name_in + "'";
        surname_in = "'" + surname_in + "'";
        age_in = age_in;
        String checkbox_in =  "'" + checkbox + "'";

        String values = surname_in + name_in + surname_in + age_in;

        System.out.println("Intended input summary: " + username_in + " " + name_in + " " + surname_in + " '" + age_in + "'" );


        String data = "INSERT INTO users(username, name, surname, age, checkbox) VALUES (" + username_in + ", " + name_in + ", " + surname_in + ", " + age_in + ", " + checkbox_in + ");";
        statement.executeUpdate(data);
        statement.close();
        System.out.println("User inserted successfully \n");

    }

    static void select() throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(URL);

        String data;
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM users;");

        System.out.printf("%-10s %-20s %-20s %-20s %-5s", "id", "username", "name", "surname", "age");
        System.out.print("\n ---------------------------------------------------------------------------");

        while(resultSet.next()) {
            System.out.println();
            System.out.printf("%-10s %-20s %-20s %-20s %-5s",
            resultSet.getInt("id"),
            resultSet.getString("username"),
            resultSet.getString("name"),
            resultSet.getString("surname"),
            resultSet.getInt("age")
            );
        }

        System.out.println("\n");
    }


    static void delete(String username_in) throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(URL);
        Statement statement = connection.createStatement();
        Scanner sc = new Scanner(System.in);

        String name_in = "";
        String surname_in = "";
        int age_in = 0;

        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM users;");

        while(resultSet.next()) {
            if(resultSet.getString("username").equals(username_in)) {
               name_in = resultSet.getString("name");
               surname_in = resultSet.getString("surname");
               age_in = resultSet.getInt("age") ;
            }
        }

        username_in = "'" + username_in + "'";
        name_in = "'" + name_in + "'";
        surname_in = "'" + surname_in + "'";

        Integer check = 2;

        while (check == 2) {
            System.out.print("Intended data summary: " + username_in + " " + name_in + " " + surname_in + " '" + age_in + "'" + "\n");
            System.out.print("\n1.Yes\n2.No" + "\nIs this account you?: ");
            Integer check2 = sc.nextInt();
            check = check2;
            if (check == 1) {
                String data = "DELETE FROM users WHERE username =" + username_in + ";";
                statement.executeUpdate(data);
            } else if (check == 2) {

                System.out.print("\nType the username again: ");
                String deleteinput = sc.next();
                ResultSet resultSet2 = connection.createStatement().executeQuery("SELECT * FROM users;");

                while (resultSet2.next()) {
                    if (resultSet2.getString("username").equals(deleteinput)) {
                        username_in = "'"+ deleteinput+"'";
                        name_in = resultSet2.getString("name");
                        surname_in = resultSet2.getString("surname");
                        age_in = resultSet2.getInt("age");
                    } else {
                        username_in = "NA";
                        name_in = "";
                        surname_in = "";
                        age_in = 0;
                    }
                }
            }
        }
        System.out.println("Deleted user: " + username_in);
        statement.close();
    }

    static void update(String username_in) throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(URL);
        Statement statement = connection.createStatement();
        Scanner sc = new Scanner(System.in);

        String name_in = "";
        String surname_in = "";
        int age_in = 0;

        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM users;");

        while(resultSet.next()) {
            if(resultSet.getString("username").equals(username_in)) {
                name_in = resultSet.getString("name");
                surname_in = resultSet.getString("surname");
                age_in = resultSet.getInt("age") ;
            }
        }

        username_in = "'" + username_in + "'";
        name_in = "'" + name_in + "'";
        surname_in = "'" + surname_in + "'";

        Integer check = 2;

        while (check == 2) {
            System.out.print("Intended data summary: " + username_in + " " + name_in + " " + surname_in + " '" + age_in + "'" + "\n");
            System.out.print("\n1.Yes\n2.No" + "\nIs this account you?: ");
            Integer check2 = sc.nextInt();
            check = check2;
            String data;
            if (check == 1) {

                System.out.print("\n1.Username\n2.Name\n3.Surname\n4.Age" + "\nWhich value you want to change?: ");
                Integer ask = sc.nextInt();


                switch (ask){
                    case 1:{
                        System.out.print("\nEnter the new value: ");
                        String newvalue = sc.next();
                        username_in = newvalue;
                        data = "UPDATE users SET username = '" + newvalue + "' WHERE username = '" + username_in + "';";
                        statement.executeUpdate(data);
                        System.out.println("Changed user: " + username_in);
                        System.out.print("New data summary: " + username_in + " " + name_in + " " + surname_in + " '" + age_in + "'" + "\n");
                        break;
                    }
                    case 2: {
                        System.out.print("\nEnter the new value: ");
                        String newvalue = sc.next();
                        data = "UPDATE users SET name = '" + newvalue + "' WHERE username = '" + username_in + "';";
                        statement.executeUpdate(data);
                        name_in = newvalue;
                        System.out.println("Changed user: " + username_in);
                        System.out.print("New data summary: " + username_in + " " + name_in + " " + surname_in + " '" + age_in + "'" + "\n");
                        break;
                    }
                    case 3: {
                        System.out.print("\nEnter the new value: ");
                        String newvalue = sc.next();
                        data = "UPDATE users SET surname = '" + newvalue + "' WHERE username = '" + username_in + "';";
                        statement.executeUpdate(data);
                        surname_in = newvalue;
                        System.out.println("Changed user: " + username_in);
                        System.out.print("New data summary: " + username_in + " " + name_in + " " + surname_in + " '" + age_in + "'" + "\n");
                        break;
                    }
                    case 4: {
                        System.out.print("\nEnter the new value: ");
                        String newvalue = sc.next();
                        data = "UPDATE users SET age = '" + newvalue + "' WHERE username = '" + username_in + "';";
                        statement.executeUpdate(data);
                        age_in = resultSet.getInt(newvalue);
                        System.out.println("Changed user: " + username_in);
                        System.out.print("New data summary: " + username_in + " " + name_in + " " + surname_in + " '" + age_in + "'" + "\n");
                        break;
                    }
                    default: {
                        System.out.println("Invalid value!!! \n");
                        break;
                    }
                }




            } else if (check == 2) {

                System.out.print("\nType the username again: ");
                String deleteinput = sc.next();
                ResultSet resultSet2 = connection.createStatement().executeQuery("SELECT * FROM users;");

                while (resultSet2.next()) {
                    if (resultSet2.getString("username").equals(deleteinput)) {
                        username_in = "'"+ deleteinput+"'";
                        name_in = resultSet2.getString("name");
                        surname_in = resultSet2.getString("surname");
                        age_in = resultSet2.getInt("age");
                        break;
                    } else {
                        username_in = "NA";
                        name_in = "";
                        surname_in = "";
                        age_in = 0;
                    }
                }
            }
        }

        statement.close();
    }



}
