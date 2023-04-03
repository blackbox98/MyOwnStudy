class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        for (String skill_tree : skill_trees) {
            skill_tree = skill_tree.replaceAll("[^" + skill + "]", "");
            if ("".equals(skill_tree) || skill.startsWith(skill_tree)) answer++;
        }
        return answer;
    }
}