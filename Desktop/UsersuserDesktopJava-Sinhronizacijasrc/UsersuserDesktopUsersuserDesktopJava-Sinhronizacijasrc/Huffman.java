package TeorijaNaInformacija;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;



public class Huffman {
    static final boolean readFromFile = false;
    static final boolean newTextBasedOnOldOne = false;

    static PriorityQueue<Node> nodes = new PriorityQueue<>((o1, o2) -> (o1.value < o2.value) ? -1 : 1);
    static TreeMap<Character, String> codes = new TreeMap<>();
    static String text = "";
    static String encoded = "";
    static String decoded = "";
    static int ASCII[] = new int[1024];

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = (readFromFile) ? new Scanner(new File("input.txt")) : new Scanner(System.in);
        int decision = 1;
        while (decision != -1) {
            if (handlingDecision(scanner, decision)) continue;
            decision = consoleMenu(scanner);
        }
    }

    private static int consoleMenu(Scanner scanner) {
        int decision;
        System.out.println("\n---- Menu ----\n" +
                "-- [-1] to exit \n" +
                "-- [1] to enter new text\n" +
                "-- [2] to encode a text\n" +
                "-- [3] to decode a text");
        decision = Integer.parseInt(scanner.nextLine());
        if (readFromFile)
            System.out.println("Одлука: " + decision + "\n---- Крај на мени ----\n");
        return decision;
    }

    private static boolean handlingDecision(Scanner scanner, int decision) {
        if (decision == 1) {
            if (handleNewText(scanner)) return true;
        } else if (decision == 2) {
            if (handleEncodingNewText(scanner)) return true;
        } else if (decision == 3) {
            handleDecodingNewText(scanner);
        }
        return false;
    }

    private static void handleDecodingNewText(Scanner scanner) {
        System.out.println("Внесете текст за кодирање:");
        encoded = scanner.nextLine();
        System.out.println("Текст за декодирање: " + encoded);
        decodeText();
    }

    private static boolean handleEncodingNewText(Scanner scanner) {
        System.out.println("Внесете го текстот за декодирање:");
        text = scanner.nextLine();
        System.out.println("Текст за енкодирање: " + text);

        if (!IsSameCharacterSet()) {
            System.out.println("Not Valid input");
            text = "";
            return true;
        }
        encodeText();
        return false;
    }

    private static boolean handleNewText(Scanner scanner) {
        int oldTextLength = text.length();
        System.out.println("Внесете го текстот:");
        text = scanner.nextLine();
        if (newTextBasedOnOldOne && (oldTextLength != 0 && !IsSameCharacterSet())) {
            System.out.println("Not Valid input");
            text = "";
            return true;
        }
        ASCII = new int[128];
        nodes.clear();
        codes.clear();
        encoded = "";
        decoded = "";
        System.out.println("Text: " + text);
        calculateCharIntervals(nodes, true);
        buildTree(nodes);
        generateCodes(nodes.peek(), "");

        printCodes();
        System.out.println("-- Encoding/Decoding --");
        encodeText();
        decodeText();
        return false;



    }

    private static boolean IsSameCharacterSet() {
        boolean flag = true;
        for (int i = 0; i < text.length(); i++)
            if (ASCII[text.charAt(i)] == 0) {
                flag = false;
                break;
            }
        return flag;
    }

    private static void decodeText() {
        decoded = "";
        Node node = nodes.peek();

        for (int i = 0; i < encoded.length(); ) {
            Node tmpNode = node;
            while (tmpNode.left != null && tmpNode.right != null && i < encoded.length()) {
                if (encoded.charAt(i) == '1')
                    tmpNode = tmpNode.right;
                else tmpNode = tmpNode.left;
                i++;
            }
            if (tmpNode != null)
                if (tmpNode.character.length() == 1)
                    decoded += tmpNode.character;
                else
                    System.out.println("Input not Valid");

        }
        System.out.println("Декодиран тест: " + decoded);
    }

    private static void encodeText() {
        encoded = "";
        for (int i = 0; i < text.length(); i++)
            encoded += codes.get(text.charAt(i));
        System.out.println("Енкодиран текст: " + encoded);
    }

   private static void buildTree(PriorityQueue<Node> vector) {
        while (vector.size() > 1)
            vector.add(new Node(vector.poll(), vector.poll()));
    }

    private static void printCodes() {
        System.out.println("--- Printing Codes ---");
        codes.forEach((k, v) -> System.out.println("'" + k + "' : " + v));
    }

    private static void calculateCharIntervals(PriorityQueue<Node> vector, boolean printIntervals) {
        if (printIntervals) System.out.println("-- фрекфенциите на зборовите --");
        System.out.println("Должината на текстот " + text.length());
        for (int i = 0; i < text.length(); i++)
            ASCII[text.charAt(i)]++;

        for (int i = 0; i < ASCII.length; i++)
            if (ASCII[i] > 0) {
                vector.add(new Node(text.indexOf(i),ASCII[i] / (text.length() * 1.0), ((char) i) + ""));
                if (printIntervals)
                    System.out.println(text.indexOf(i) +  " --- " +  "'" + ((char) i) + "' : " + ASCII[i] / (text.length() * 1.0));
            }

    }
            // главната функција  за развивање на  кодовоите на дрвото
    private static void generateCodes(Node node, String s) {
        if (node != null) {
            if (node.right != null)
                 // ако десното не е лист
                generateCodes(node.right, s + "1");
                // ако левото не е лист
            if (node.left != null)
                generateCodes(node.left, s + "0");
                // ако е лист  искодирај го кодот
            if (node.left == null && node.right == null)
                codes.put(node.character.charAt(0), s);
        }
    }
}

class Node {
    Node left, right;
    double value;
    String character;
    int i;
    //класата за дрвото
                //индекс //вредност //карактер
    public Node(int i ,double value, String character) {
        this.value = value;
        this.character = character;
        left = null;    //помош при движење лево
        right = null;      //помош при движење десно
        this.i=i;
    }

    public Node(Node left, Node right) {
        this.value = left.value + right.value;
        character = left.character + right.character;
        if (left.value < right.value) {
            this.right = right;
            this.left = left;
        } else {
            this.right = left;
            this.left = right;
        }
    }
}