import java.util.Set;
import java.util.TreeSet;

/**
 * Leetcode 804. 唯一摩尔斯密码词 Unique Morse Code Words
 * https://leetcode.com/problems/unique-morse-code-words/description/
 *
 * @author bianxinhuan
 * @date 2019-05-13 11:17:22
 */
public class Solution {

    public int uniqueMorseRepresentations(String[] words) {

        String[] codes = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

        Set<String> set = new TreeSet<>();
        for (String word : words) {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                res.append(codes[word.charAt(i) - 'a']);
            }
            set.add(res.toString());
        }

        return set.size();
    }
}
