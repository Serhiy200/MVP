package model;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class GetMVP {

    public static final String WRONG_FILE = "wrongFile";

    public String basketball(List<GameFields> gameFields, int goalsTypeCount) throws Exception {
        Set<String> teams = getTeams(gameFields);
        List<String[]> teamGoals = getTeamGoals(teams, gameFields);
        String wonTeam = getWonTeam(teamGoals);

        Map<String, Integer> mvp = new HashMap<>();

        for (int i = 0; i < gameFields.size(); i++) {
            String[] goals = gameFields.get(i).getGoals().split(";");
            if (goals.length == goalsTypeCount && checkExistGoals(goals)) {
                int scorePoint = Integer.parseInt(goals[0]);
                int rebound = Integer.parseInt(goals[1]);
                int assist = Integer.parseInt(goals[2]);

                int score = 2 * scorePoint + 1 * rebound + 1 * assist;
                if (gameFields.get(i).getTeamName().equals(wonTeam)) {
                    score += 10;
                }

                mvp.put(gameFields.get(i).getNickName(), score);
            } else {
                throw new Exception(WRONG_FILE);
            }
        }


        return maxMapValue(mvp);
    }

    public String handball(List<GameFields> gameFields, int goalsTypeCount) throws Exception {
        Set<String> teams = getTeams(gameFields);
        List<String[]> teamGoals = getTeamGoals(teams, gameFields);
        String wonTeam = getWonTeam(teamGoals);


        Map<String, Integer> mvp = new HashMap<>();

        for (int i = 0; i < gameFields.size(); i++) {
            String[] goals = gameFields.get(i).getGoals().split(";");
            if (goals.length == goalsTypeCount && checkExistGoals(goals)) {
                int goalMade = Integer.parseInt(goals[0]);
                int goalReceived = Integer.parseInt(goals[1]);

                int score = 2 * goalMade - 1 * goalReceived;
                if (gameFields.get(i).getTeamName().equals(wonTeam)) {
                    score += 10;
                }

                mvp.put(gameFields.get(i).getNickName(), score);
            } else {
                throw new Exception(WRONG_FILE);
            }

        }


        return maxMapValue(mvp);
    }

    private Set<String> getTeams(List<GameFields> gameFields) throws Exception {
        Set<String> teams = new HashSet<>();
        for (GameFields fields : gameFields) {
            teams.add(fields.getTeamName());
        }
        if (teams.size() < 2) {
            throw new Exception(WRONG_FILE);
        } else {
            return teams;
        }

    }

    private List<String[]> getTeamGoals(Set<String> teams, List<GameFields> gameFields) throws Exception {
        List<String[]> goalTeams = new ArrayList<>();
        for (String team : teams) {
            String[] goals = new String[(gameFields.size() / teams.size()) + 1];
            int n = 0;
            goals[n++] = team;
            for (int i = 0; i < gameFields.size(); i++) {
                if (gameFields.get(i).getTeamName().equals(team)) {
                    if (StringUtils.isNotEmpty(gameFields.get(i).getGoals())) {
                        goals[n++] = gameFields.get(i).getGoals();
                    } else {
                        throw new Exception(WRONG_FILE);
                    }

                }
            }
            goalTeams.add(goals);

        }

        return goalTeams;
    }

    private String getWonTeam(List<String[]> teamGoals) throws Exception {
        Map<String, Integer> scoreTeams = new HashMap<String, Integer>();
        for (int i = 0; i < teamGoals.size(); i++) {
            int teamScoreGoals = 0;
            String[] goals = teamGoals.get(i);
            if (goals.length > 0) {
                for (int n = 1; n < goals.length; n++) {
                    String goal = goals[n].split(";")[0];
                    if(StringUtils.isNotEmpty(goal)){
                        teamScoreGoals += Integer.parseInt(goal);
                    }else {
                        throw new Exception(WRONG_FILE);
                    }

                }
                scoreTeams.put(goals[0], teamScoreGoals);
            } else {
                throw new Exception(WRONG_FILE);
            }
        }
        return maxMapValue(scoreTeams);
    }

    private boolean checkExistGoals(String[] goals){
        boolean allIsExist = true;

        for(String goal:goals){
            if(goal.isEmpty()){
                allIsExist = false;
            }
        }

        return allIsExist;
    }

    public <K, V extends Comparable<V>> K maxMapValue(Map<K, V> map) {
        Optional<Map.Entry<K, V>> maxEntry = map.entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue));
        return maxEntry.get()
                .getKey();
    }
}
