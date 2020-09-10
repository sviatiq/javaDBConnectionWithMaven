package com.sviatdev.IOStreams;

import java.io.*;

public class IOStreamsExample {
    public static void main(String[] args) throws IOException{

        File file = new File("1.txt");
       try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))){
           bufferedWriter.write("Hello");
       }catch(IOException ex){
           ex.printStackTrace();
       }
//        bufferedWriter.write("dabdabdab");
//        try (FileOutputStream fos = new FileOutputStream(file)) {
//            fos.write("Hello, World".getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String str;
            while (true) {
                    if (((str = bufferedReader.readLine()) != null))
                        System.out.println(str);


            }
        }
    }
}

