package com.LockMe.service;

import com.LockMe.util.User;
import com.LockMe.util.Utility;

import java.io.IOException;
import java.util.ArrayList;

public class UserActivity {
    private User user;

    public void createUser(String userName, String password) {
        User user = new User(userName, password);
        Utility.writeToKeyFile();
        Utility.writeToDatabase(userName + " " + password + " " + Utility.readFromKeyFile());
        System.out.println("\nUser created successfully\n");
    }

    public void addUserEnteredDataInLocker(String user) throws IOException {
        String key = getUserKey(user);
        System.out.print("Enter website:-  ");
        String website = Utility.inputFromUser();

        if(Utility.isUserFileExists(user+key)) {
            ArrayList<String> users = Utility.readFromUserFile(user + getUserKey(user));

            for (int i = 0; i < users.size(); i += 2) {
                if (users.get(i).replace("Website  :- ", "").trim().equals(website)) {
                    System.out.println();
                    System.out.println("Website " + website + " is already there");
                    System.out.println("Please delete website credential and retry");
                    System.out.println();
                    return;
                }
            }
        }

        System.out.print("Enter password:-  ");
        String password = Utility.inputFromUser();

        Utility.writeToUserFile(user + key, "Website  :- " + website);
        Utility.writeToUserFile(user + key, "Password :- " + password);
        System.out.println("\nYour credential is stored successful\n");
    }

    public String getUserKey(String user) {
        ArrayList<String> userData = Utility.readFromDatabase();
        String key = "";

        for (int i = 0; i < userData.size(); i++) {
            if (userData.get(i).split(" ")[0].equals(user)) {
                key = userData.get(i).split(" ")[2];
            }
        }

        return key;
    }

    public void displayUserData(String user) {
        ArrayList<String> users = Utility.readFromUserFile(user + getUserKey(user));
        System.out.println();
        for (int i = 0; i < users.size(); i += 2) {
            System.out.println(users.get(i));
            System.out.println(users.get(i + 1));
            System.out.println();
        }
        System.out.println();
    }

    public boolean searchWebsite(String user, String website) {
        if (!Utility.isUserFileExists(user + getUserKey(user))) {
            System.out.println("\nThere no credential stored for now\n");
            return false;
        } else {
            ArrayList<String> users = Utility.readFromUserFile(user + getUserKey(user));

            for (int i = 0; i < users.size(); i += 2) {
                if (users.get(i).replace("Website  :- ", "").trim().equals(website)) {
                    System.out.println();
                    System.out.println(users.get(i));
                    System.out.println(users.get(i + 1));
                    return true;
                }
            }
        }

        return false;
    }

    public boolean deleteWebsite(String user, String website) {
        String key = getUserKey(user);
        boolean status = false;

        if (!Utility.isUserFileExists(user + getUserKey(user))) {
            System.out.println("\nFile not found !!! No credential stored\n");
            return false;
        } else {
            ArrayList<String> users = Utility.readFromUserFile(user + getUserKey(user));
            ArrayList<String> afterDelete = new ArrayList<>();

            for (int i = 0; i < users.size(); i += 2) {
                if (users.get(i).replace("Website  :- ", "").trim().equals(website)) {
                    status = true;
                } else {
                    afterDelete.add(users.get(i));
                    afterDelete.add(users.get(i + 1));
                }
            }

            if(status){
                Utility.deleteUserFile(user + key);
            }

            if(afterDelete.size()!=0 && status){
                for (int i = 0; i < afterDelete.size(); i += 2) {
                    Utility.writeToUserFile(user + key, afterDelete.get(i));
                    Utility.writeToUserFile(user + key, afterDelete.get(i + 1));
                }
            }

        }
        return status;
    }

    public boolean isUserExists(String userName) {
        if (isUserThere(userName) && Utility.isDatabaseExists()) {
            return true;
        }
        return false;
    }

    private boolean isUserThere(String isUser) {
        ArrayList<String> users = Utility.readFromDatabase();
        for (String dbUsers : users) {
            if (dbUsers.split(" ")[0].equals(isUser)) {
                return true;
            }
        }
        return false;
    }

    public boolean loginSuccessful(String user, String pass) {
        ArrayList<String> users = Utility.readFromDatabase();
        for (String dbUsers : users) {
            if (dbUsers.split(" ")[0].equals(user) && dbUsers.split(" ")[1].equals(pass)) {
                System.out.println("\n**************************");
                System.out.println("**** Login successful ****");
                System.out.println("**************************\n");
                return true;
            }
        }
        return false;
    }
}
