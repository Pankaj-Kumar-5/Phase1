package com.LockMe.main;

import com.LockMe.service.UserActivity;
import com.LockMe.util.Utility;

import java.io.IOException;
import java.util.Scanner;

public class lockMe {
    public static void main(String[] args) throws IOException {
        while (true){
            welcomeScreen();
        }
    }

    public static void welcomeScreen() throws IOException {
        System.out.println("******************************************");
        System.out.println("*******  Welcome to locker  **************");
        System.out.println("*******   by Pankaj kumar   **************");
        System.out.println("******************************************");

        System.out.println("1-> Register as user");
        System.out.println("2-> Login");
        System.out.println("3-> Exit");
        System.out.print("Select option :- ");
        int option = 0;

        try {
             option = Integer.parseInt(Utility.inputFromUser());
        }catch (Exception e){

            System.out.println("Please type number!!!!!!!!!\n\n");
        }

        switch (option){
            case 1: createUser(); break;
            case 2: login(); break;
            case 3: System.exit(1);
            default: System.out.println("Please choose correct option");
        }
    }

    public static void createUser() throws IOException {

        System.out.print("Enter user name:-  ");
        String userName = Utility.inputFromUser().trim();

        System.out.print("Enter password:-  ");
        String password = Utility.inputFromUser().trim();

        UserActivity activity = new UserActivity();
        if(activity.isUserExists(userName)){
            System.out.println("\nUsername is already there. Please choose different and try again.");
            welcomeScreen();
        }else{
            activity.createUser(userName,password);
            System.out.println("Registered successfully. Please login with same credentials");
            System.out.println("Press Enter key to continue");
            System.in.read();
            welcomeScreen();
        }
    }

    public static void login() throws IOException {
        loginScreen();
        System.out.print("Enter user name:- ");
        String userName = Utility.inputFromUser();

        System.out.print("Enter user password:- ");
        String password = Utility.inputFromUser();

        UserActivity activity = new UserActivity();

        if(activity.loginSuccessful(userName,password)){
            while (true) {
                System.out.println("1-> Show all credentials");
                System.out.println("2-> Add credential");
                System.out.println("3-> Search credential");
                System.out.println("4-> Delete credential");
                System.out.println("5-> logout");
                System.out.print("Select option:-  ");

                int option = 0;
                try {
                    option = Integer.parseInt(Utility.inputFromUser());
                }catch (Exception e){
                    System.out.println("Please type number!!!!!!!!!\n\n");
                }

                switch (option) {
                    case 1:
                        displayUserData(userName);
                        break;
                    case 2:
                        addUserData(userName);
                        break;
                    case 3:
                        searchWebsite(userName);
                        break;
                    case 4:
                        deleteWebsite(userName);
                        break;
                    case 5:
                        System.out.println("\nLogged out");
                        System.out.println();
                        welcomeScreen();
                        break;
                    default:
                        System.out.println("\nPlease choose correct option\n");
                }
            }

        }else{
            System.out.println("\nIncorrect credential pls try again\n");
        }
    }

    public static void addUserData(String user) throws IOException {
        UserActivity activity = new UserActivity();
        addScreen();
        activity.addUserEnteredDataInLocker(user);
    }

    public static void displayUserData(String user) throws IOException {
        UserActivity activity = new UserActivity();

        if(Utility.isUserFileExists(user+activity.getUserKey(user))){
            activity.displayUserData(user);
        }else{
            System.out.println("\nYou don't have any thing stored for now");
            System.out.println("Press enter to continue\n");
            System.in.read();
        }
    }

    public static void searchWebsite(String user) throws IOException {
        UserActivity activity = new UserActivity();
        System.out.print("Enter website name to search :-");
        String search = Utility.inputFromUser();
        if(activity.searchWebsite(user,search)){
            System.out.println("\nSearch successful!!!!\n");
        }else{
            System.out.println("\nSearch unsuccessful!!!!\n");
        }
    }

    public static void deleteWebsite(String user) throws IOException {
        UserActivity activity = new UserActivity();

        System.out.print("Enter website name to delete :-");

        String delete = Utility.inputFromUser();

        if(activity.deleteWebsite(user,delete)){
            System.out.println("\nDelete successful!!!!\n\n");
        }else{
            System.out.println("\nDelete unsuccessful!!!!\n\n");
        }
    }


    private static void loginScreen(){
        System.out.println("******************************************");
        System.out.println("**** Login with username and password ****");
        System.out.println("******************************************");
    }

    private static void addScreen(){
        System.out.println("***********************************************");
        System.out.println("**** Please enter you website and password ****");
        System.out.println("***********************************************");
    }
}
