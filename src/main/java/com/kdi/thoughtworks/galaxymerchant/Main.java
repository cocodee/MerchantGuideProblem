package com.kdi.thoughtworks.galaxymerchant;

import com.kdi.thoughtworks.galaxymerchant.Exceptions.ParseException;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        String filePath = null;
        if (args.length != 0)
            filePath = args[0];
        BufferedReader br = null;
        Processer processer = new Processer();
        try {
            if (filePath == null){
                InputStream in = Processer.class.getResourceAsStream("/input.txt");
                br =new BufferedReader(new InputStreamReader(in));
            }
            else{
                FileReader fileReader = new FileReader(filePath);
                br = new BufferedReader(fileReader);
            }
            String line = br.readLine();
            while (line!=null) {
                processer.processInput(line);
                line = br.readLine();
            }
            processer.generateOutput();
        } catch (FileNotFoundException e) {
            System.out.println("File could not be found.");
        } catch (IOException e) {
            System.out.println("Read file error.");
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {

                }
            }
        }
    }
}
