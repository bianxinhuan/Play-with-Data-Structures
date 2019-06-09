import sun.nio.cs.ext.MacArabic;
import sun.rmi.runtime.Log;

import java.util.ArrayList;

/**
 * -
 *
 * @author bianxinhuan
 * @date 2019-05-14 10:25:29
 */
public class Main {

    private static double testMap(Map<String, Integer> map, String filename) {

        long startTime = System.nanoTime();

        System.out.println(filename);
        ArrayList<String> words = new ArrayList<>();

        if (FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());

            for (String word : words) {
                if (map.contains(word)) {
                    map.set(word, map.get(word) + 1);
                } else {
                    map.add(word, 1);
                }
            }
        }

        System.out.println("Total different words: " + map.getSize());
        System.out.println("Frequency of PRIDE: " + map.get("pride"));
        System.out.println("Frequency of PREJUDICE: "+map.get("prejudice"));

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        String filename = "src/12-AVL-Tree/02-Calculation-Balance-Factor/pride-and-prejudice.txt";

        BSTMap<String, Integer> bstMap = new BSTMap<>();
        double time1 = testMap(bstMap, filename);
        System.out.println("BST Map: " + time1 + "s");

        System.out.println();

        LinkedListMap<String, Integer> linkedListMap = new LinkedListMap<>();
        double time2 = testMap(linkedListMap, filename);
        System.out.println("Linked List Map: " + time2 + "s");
    }

}
