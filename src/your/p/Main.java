package your.p;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    
    static Map<String,Map<String,Integer>> outerCurrent;
    public static void main(String[] args) throws FileNotFoundException,IOException{

        
        File fileDir = new File(args[0]);

        File fileList[] = fileDir.listFiles();

        for(File f : fileList){
//            System.out.println(f);

            StringBuilder sb = new StringBuilder();

            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            
            String line = "";
            String outputString = "";

            while((line=br.readLine())!=null){
                line = line.replaceAll("\\p{Punct}", "");

                sb.append(line);

            }
            outputString = sb.toString();

//            System.out.println(outputString);
            String[] splitString = outputString.split(" ");

            outerCurrent = new HashMap<>();

            for(int i = 0; i < splitString.length-1; i++){
                String word = splitString[i].toLowerCase();
                String nextWord = splitString[i+1].toLowerCase();

                int totalNextWords = 0;
                if(outerCurrent.containsKey(word)){
                    Map<String,Integer> innerNext = outerCurrent.get(word);
                    for(int count : innerNext.values()){
                        totalNextWords += count;
                    }

                    if(innerNext.containsKey(nextWord)){
                        int occurence = innerNext.get(nextWord);
                        innerNext.put(nextWord, occurence+1);
                    } 
                    else{
                        innerNext.put(nextWord,1);
                    }
                } else{
                    Map<String,Integer> innerNext = new HashMap<>();
                    innerNext.put(nextWord, 1);

                    outerCurrent.put(word, innerNext);
                }

                

            }

            
        }

    }

}