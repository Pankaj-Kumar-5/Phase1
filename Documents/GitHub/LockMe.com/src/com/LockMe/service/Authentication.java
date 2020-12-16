package com.LockMe.service;

import com.LockMe.util.User;
import com.LockMe.util.Utility;

import java.util.ArrayList;

public class Authentication {

    public boolean isUserValid(User user) {
        ArrayList<String> userDatabase = Utility.readFromDatabase();
        for (String userdb : userDatabase) {
            if ((user.getUserName() +" "+ user.getPassword()).equals(userdb)) {
                return true;
            }
        }
        return false;
    }
}
