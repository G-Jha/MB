import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        List<Map<String,String>> matches = readFile("matches.csv");
        List<Map<String,String>> deliveries = readFile("deliveries.csv");
//        question 1
        /*

        Map<String,Integer>mpy=matchesPlayedPerYear(matches);
        System.out.println(mpy);

         */

//        question 2
        /*

        Map<String,Integer>matchesWon=MatchesWon(matches);
        System.out.println(matchesWon);

         */

//        question 3

        Map<String,Integer>matchesWon=MatchesWon(matches);
        System.out.println(matchesWon);


    }

//    calculate matches won overyears
    private static Map<String, Integer> MatchesWon(List<Map<String, String>> matches) {
        Map<String, Integer> data=new HashMap<>();
        for(int i=0;i<matches.size();i++){
            String team=matches.get(i).get("winner");
            if(team.length()>1){
                if(data.containsKey(team)){
                    data.put(team,data.get(team)+1);
                }else{
                    data.put(team,1);
                }
            }
        }
        return data;
    }

    //    Calculate total number of matches played per season

    private static Map<String, Integer> matchesPlayedPerYear(List<Map<String, String>> matches) {
        Map<String, Integer> data=new HashMap<>();
        for(int i=0;i<matches.size();i++){
            String season=matches.get(i).get("season");
            if(data.containsKey((matches.get(i)).get("season"))){
                data.put(season,data.get(season)+1);
            }else{
                data.put(season,1);
            }
        }
            return data;

    }

//    read files
    private static List<Map<String, String>> readFile(String url) {
        List<Map<String, String>> data = new ArrayList<>();

        try(BufferedReader reader=new BufferedReader(new FileReader ("src/"+url))
        ){
            List<String> fields=new ArrayList<>(Arrays.asList(reader.readLine().split(",",-1)));
            String line;
            while (( line = reader.readLine())!=null){

                List<String> values =new ArrayList<>(Arrays.asList(line.split(",",-1)));
                Map<String,String> temp=new HashMap<>();

                for(int i=0;i<fields.size();i++){
                    temp.put(fields.get(i),values.get(i));
                }
                data.add(temp);
            }

        }catch(Exception e){
            System.out.println(e.getMessage()+ e.getCause());
        }

        return data;
    }

}