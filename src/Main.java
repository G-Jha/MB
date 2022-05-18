import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Map<String,String>> matches = readFile("matches.csv");
        List<Map<String,String>> deliveries = readFile("deliveries.csv");
//        question 1

        Map<String,Integer>mpy=matchesPlayedPerYear(matches);
        System.out.println(mpy);


//        question 2

        Map<String,Integer>matchesWon=MatchesWon(matches);
        System.out.println(matchesWon);


//        question 3

        Map<String,Integer> extras = extra2016(matches,deliveries);
        System.out.println(extras);

//        question  4

        Map<String,Integer>echoBaller=echomomy(matches,deliveries);
        System.out.println(echoBaller);

    }

    private static Map<String, Integer> echomomy(List<Map<String, String>> matches, List<Map<String, String>> deliveries) {
        ArrayList<String> matchId= getMatchesId(matches,"2015");
        Map<String, Integer>data=new HashMap<>();
        for (int i = 0; i < deliveries.size(); i++) {
            if (matchId.contains(deliveries.get(i).get("match_id"))) {
                int runs = Integer.parseInt(deliveries.get(i).get("total_runs"));
                String current_Baller = deliveries.get(i).get("bowler");
                if (data.containsKey(current_Baller)) {
                    data.put(current_Baller, data.get(current_Baller) + runs);
                } else {
                    data.put(current_Baller, runs);
                }
            }
        }
        return data;
    }

//
// calculate extras

    private static Map<String, Integer> extra2016(List<Map<String, String>> matches, List<Map<String, String>> deliveries) {
        ArrayList<String> matchId= getMatchesId(matches,"2016");
        Map<String,Integer> data=new HashMap<>();
        for (int i = 0; i < deliveries.size(); i++) {
            if(matchId.contains(deliveries.get(i).get("match_id"))){
                String team=deliveries.get(i).get("bowling_team");
                int score= Integer.parseInt(deliveries.get(i).get("extra_runs"));
                if(score>0){
                    if(data.containsKey(team)){
                        data.put(team,data.get(team)+score);
                    }else{
                        data.put(team,score);
                    }
                }
            }
        }
        return data;
    }

//filter matches by year
    private static ArrayList<String> getMatchesId(List<Map<String, String>> matches, String s) {
        ArrayList<String> matchId= new ArrayList<>();
        for (int i = 0; i < matches.size(); i++) {
            if(matches.get(i).get("season").equals(s)){
                matchId.add(matches.get(i).get("id"));
            }
        }
        return matchId;
    }


    //    calculate matches won over years
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