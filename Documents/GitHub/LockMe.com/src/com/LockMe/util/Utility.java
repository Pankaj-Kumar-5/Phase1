package com.LockMe.util;

import java.io.*;
import java.util.ArrayList;

public class Utility {

    public static String inputFromUser() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine().trim();

        while (input.equals("\n") || input.equals("")) {
            input = br.readLine().trim();
        }
        return input;
    }

    public static void writeToDatabase(String data) {
        String path = System.getProperty("user.dir") + "\\src\\com\\LockMe\\Resource\\database\\database.txt";
        writeToFile(path, data);
    }

    public static void writeToKeyFile() {
        String path = System.getProperty("user.dir") + "\\src\\com\\LockMe\\Resource\\database\\key.txt";
        if (!isFileExists(path))
            writeToFileAndDontAppend(path, "100");
        else
            writeToFileAndDontAppend(path, (readFromKeyFile() + 1) + "");
    }

    public static int readFromKeyFile() {
        String path = System.getProperty("user.dir") + "\\src\\com\\LockMe\\Resource\\database\\key.txt";
        return Integer.parseInt(readFromFile(path).get(0));
    }

    public static void writeToUserFile(String user, String data) {
        String path = System.getProperty("user.dir") + "\\src\\com\\LockMe\\Resource\\usersLocker\\" + user + ".txt";
        writeToFile(path, data);
    }

    public static void writeToUserFileAndDontAppend(String user, String data) {
        String path = System.getProperty("user.dir") + "\\src\\com\\LockMe\\Resource\\usersLocker\\" + user + ".txt";
        writeToFileAndDontAppend(path, data);
    }

    public static void deleteUserFile(String user) {
        String path = System.getProperty("user.dir") + "\\src\\com\\LockMe\\Resource\\usersLocker\\" + user + ".txt";
        File myObj = new File(path);
        myObj.delete();
    }

    public static ArrayList<String> readFromUserFile(String user) {
        String path = System.getProperty("user.dir") + "\\src\\com\\LockMe\\Resource\\usersLocker\\" + user + ".txt";
        return readFromFile(path);
    }

    public static ArrayList<String> readFromDatabase() {
        String path = System.getProperty("user.dir") + "\\src\\com\\LockMe\\Resource\\database\\database.txt";
        if (!isDatabaseExists())
            writeToDatabase("1 1 1");
        return readFromFile(path);
    }

    public static boolean isUserFileExists(String user) {
        String path = System.getProperty("user.dir") + "\\src\\com\\LockMe\\Resource\\usersLocker\\" + user + ".txt";
        File myObj = new File(path);
        return myObj.exists();
    }

    public static boolean isFileExists(String path) {
        File myObj = new File(path);
        return myObj.exists();
    }

    public static boolean isDatabaseExists() {
        String path = System.getProperty("user.dir") + "\\src\\com\\LockMe\\Resource\\database\\database.txt";
        File myObj = new File(path);
        return myObj.exists();
    }



    // To create database and write to file
    public static void writeToFile(String path, String data) {
        FileWriter myWriter;

        try {
            File myObj = new File(path);

            if (!myObj.exists())
                myObj.createNewFile();

            myWriter = new FileWriter(path, true);
            myWriter.append(data + "\n");
            myWriter.close();

        } catch (IOException e) {
            System.out.println("Error while creating database in your system");
        }
    }

    // To create database and write to file
    public static void writeToFileAndDontAppend(String path, String data) {
        FileWriter myWriter;

        try {
            File myObj = new File(path);

            if (!myObj.exists())
                myObj.createNewFile();

            myWriter = new FileWriter(path);
            myWriter.write(data + "\n");
            myWriter.close();

        } catch (IOException e) {
            System.out.println("Error while creating database in your system");
        }
    }


    // To create database and write to file
    public static ArrayList<String> readFromFile(String path) {
        FileWriter myWriter;
        ArrayList<String> data = new ArrayList<>();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            while (line != null) {
                data.add(line);

                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
