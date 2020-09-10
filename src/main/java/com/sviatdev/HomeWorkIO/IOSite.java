package com.sviatdev.HomeWorkIO;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class IOSite {
    public static void main(String[] args) throws IOException {
        File file = new File("usersData.txt");

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))){
            URL url = new URL("https://jsonplaceholder.typicode.com/users");
            Gson gson = new Gson();
            List<String> names = new ArrayList();
            List<String> usernames = new ArrayList();
            List<String> emails = new ArrayList();
            try(InputStream is = url.openStream()){
                InputStreamReader isr = new InputStreamReader(is);
                Type type = new TypeToken<List<User>>(){}.getType();
                List<User> users = gson.fromJson(isr, type);

                for(User user: users){
                    names.add(user.getName());
                    usernames.add(user.getUsername());
                    emails.add(user.getEmail());
                }
                bufferedWriter.write("Names: \n");
                for(String name: names){
                    bufferedWriter.write(name+", ");
                }
                bufferedWriter.write("\nNames: \n");
                for(String username: usernames){
                    bufferedWriter.write(username+", ");
                }
                bufferedWriter.write("\nEmails: \n");
                for(String email: emails){
                    bufferedWriter.write(email+", ");
                }
            }
        }

    }
}
