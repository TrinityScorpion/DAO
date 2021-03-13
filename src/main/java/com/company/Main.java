package com.company;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        Connection connection = DbUtil.connect();
        Scanner optionScanner = new Scanner(System.in);
        User user = new User("uzytkownik", "mailuzytkownika@fsf", "fff");
        User newUser = new UserDAO();
        UserDAO userDao = new UserDAO();
        UserDAO userDao1 = new UserDAO();

        newUser.setUserName("maniusxxx");
        newUser.setEmail("manifYFVHGdsk@lgchem.com");
        newUser.setPassword("dfdssdff");

        userDao.setUserName("userdao");
        userDao.setEmail("userdao@wp.pl");
        userDao.setPassword("dadasdadasdasd");
        userDao1.setUserName("userdao12dgsdgddf");
        userDao1.setEmail("userdao12@sfdsfdswp.pl");
        userDao1.setPassword("sfsdfsdfsdfdsfdsfsfsfsfdf");
        // userDao.update(user);

        //login(optionScanner);
        SettingsMenu();
        while (true) {
            String line = optionScanner.nextLine();
            switch (line) {
                case "menu":
                    SettingsMenu();
                    break;
                case "add":
                    System.out.println("Adding new User. User name:");
                    String name = optionScanner.nextLine();
                    System.out.println("User email:");
                    String email = optionScanner.nextLine();
                    System.out.println("User password:");
                    String password = optionScanner.nextLine();
                    userDao.create(new User(name,email, password));
                    System.out.println("Done");
                    break;
                case "remove":
                    System.out.println("Select row number to remove:");
                    try {
                        int temp = optionScanner.nextInt();
                        userDao.remove(temp);
                    } catch (InputMismatchException e) {
                        System.out.println("Exception: " + e.getMessage());
                    }
                    break;
                case "update":

                    break;
                case "read":
                    System.out.println("Select row number to read:");
                    try {
                        int rem = optionScanner.nextInt();
                        userDao.read(rem);
                    } catch (InputMismatchException e) {
                        System.out.println("Exception: " + e.getMessage());
                    }
                    break;
                case "show":
                    userDao.findAll();
                    break;
                case "quit":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choose correct option");
                    break;
            }
        }


    }

    private static void SettingsMenu() {
        System.out.println("=========================");
        System.out.println("Choose option:");
        System.out.println("Add User");
        System.out.println("Remove User");
        System.out.println("Update User");
        System.out.println("Read User");
        System.out.println("Show All Database");
        System.out.println("quit");
        System.out.println("=========================");
    }

    private static void login(Scanner optionScanner) {
        while (true) {
            String login = "maniek";
            String password = "haslo";
            System.out.println("Podaj login...");
            String logIn = optionScanner.nextLine();
            System.out.println("Podaj haslo...");
            String logPass = optionScanner.nextLine();
            if (logIn.equals(login) && logPass.equals(password)) {
                break;
            } else
                System.out.println("Zły login lub hasło");
        }
    }

}
