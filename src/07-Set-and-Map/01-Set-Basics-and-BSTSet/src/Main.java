import java.util.ArrayList;

/**
 * -
 *
 * @author bianxinhuan
 * @date 2019-05-11 19:08:54
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");

        ArrayList<String> words1 = new ArrayList<>();
        if (FileOperation.readFile("src/12-AVL-Tree/02-Calculation-Balance-Factor/pride-and-prejudice.txt", words1)) {
            System.out.println("Total words: " + words1.size());

            BSTSet<String> set1 = new BSTSet<>();
            for (String word : words1) {
                set1.add(word);
            }
            System.out.println("Total different words: " + set1.getSize());
        }

        System.out.println();

        System.out.println("A Tale of Two Cities");

        ArrayList<String> words2 = new ArrayList<>();
        if (FileOperation.readFile("a-tale-of-two-cities.txt", words2)) {
            System.out.println("Total words: " + words2.size());

            BSTSet<String> set2 = new BSTSet<>();
            for (String word : words2) {
                set2.add(word);
            }
            System.out.println("Total different words: " + set2.getSize());
        }
    }

}
