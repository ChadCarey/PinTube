
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chad
 */
class UserController {

    public HashMap <String, String> users;
    String filePath;
    
    public UserController() {
        // get file path
        users = new HashMap();
        filePath = System.getProperty("user.dir") + "/users.txt";
        System.err.println(filePath);
        // make sure the file exists
        File file = new File(filePath);
        if (!file.exists()) {
            // if file doesn't exist create it
            try {
                file.createNewFile();
                System.err.println("Created new file at " + file.getAbsolutePath());
            } catch (IOException ex) {
                System.err.println("ERROR creating new user file");
            } 
        }
        try {
            // read usrs and passwords into hash map from file for now
            // later this can be replaced with mysql
            BufferedReader read = new BufferedReader(new FileReader(filePath));
            String fileContents = "";
            
            try {
                fileContents = read.readLine();
                System.err.println("line read"); 
            } catch (IOException ex) {
            }
            
            String[] userPass = new String[]{"", ""};
            
            while (fileContents != null) {
                System.err.println("Entered split loop for: " + fileContents);
                try {
                userPass = fileContents.split(", ");
                } catch (Exception e) {
                    System.out.println(e);
                    //System.exit(1);
                }
                System.err.println("Split: " + userPass[0] + " " + userPass[1]);
                users.put(userPass[0], userPass[1]);
                System.err.println(users.get(userPass[0]));
                try {
                    fileContents = read.readLine();
                    System.err.println("line read"); 
                } catch (IOException ex) {
                    System.err.println("line read failed in while loop");
                }
            }
            read.close();
        } catch (FileNotFoundException ex) {
                System.out.println("ERROR reading file");
        } catch (IOException ex) {
            System.err.println("Error closing file");
        }
        System.err.println("Ending userController costructor"); 
    }     
    
    /**
     * this will check to see if the user and password are valid
     * @param user
     * @param pass
     * @return boolean
     */
    public boolean validLogin(String user, String pass) {
        if(user.isEmpty()) {
            return false;
        }
        
        String p = users.get(user);
        if (p == null || !p.equals(pass)){
            return false;
        }
        return true;
    }

    /**
     * This checks to see f the user exists already to avoid duplicate names
     * @param user
     * @return 
     */
    public boolean userExists(String user) {
        if(users.isEmpty()) {
            return false;
        }
        
        if(users.get(user) == null) {
            return false;
        }
        return true;
    }
    
    /**
     * This will add a new user if the user doesn't already exist
     * @param user
     * @param pass
     * @return 
     */
    public boolean addUser(String user, String pass) {
        if(userExists(user)) {
            return false;
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            writer.append(user + ", " + pass + "\n");
            users.put(user, pass);
            writer.close();
            return true;
        } catch (IOException ex) {
            System.out.println("ERROR writing user to file");
            return false;
        }
    }
    
}
