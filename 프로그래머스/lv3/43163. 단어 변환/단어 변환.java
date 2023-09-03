import java.util.*;

class Solution {
    HashSet<String> wordSet = new HashSet<>();
    HashSet<Character>[] idxCharSet;
    HashSet<String> visited = new HashSet<>();
    int answer = Integer.MAX_VALUE;
    public int solution(String begin, String target, String[] words) {
        for(String word : words){
            wordSet.add(word);
        }        
        if(wordSet.contains(target)){
            idxCharSet  = new HashSet[begin.length()];
            for(int i = 0; i < begin.length(); i++){
                idxCharSet[i] = new HashSet<Character>();
            }
            for(String word : words){
                for(int i = 0; i < word.length();i++){
                    idxCharSet[i].add(word.charAt(i));
                }
            }
            
            visited.add(begin);
            backTrack(begin, target, 0);          
        } else{
            answer = 0;
        }
        return answer;
    }
    
    void backTrack(String now, String target, int depth){
        if(now.equals(target)){
            answer = Math.min(depth, answer);
            return;
        }
        
        char[] nowChars = now.toCharArray();
        for(int i = 0; i < now.length(); i++){
            char temp = nowChars[i];
            for(char newChar : idxCharSet[i]){
                nowChars[i] = newChar;
                String newString = String.valueOf(nowChars);
                if(wordSet.contains(newString) && !visited.contains(newString)){
                    visited.add(newString);
                    backTrack(newString, target, depth+1);
                }
                nowChars[i] = temp;
            }
            
        }
        
    }
}