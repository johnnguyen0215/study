import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by John on 9/14/2016.
 */
public class PracticeProblems {

    /**
     * 1.1 Is Unique: Implement an algorithm to determine if a string has all unique characters.
     * What if you cannot use additional data structures?
     *
     * @param s
     * @return
     */
    public static boolean isUniqueInefficient(String s) {
        ArrayList<Character> arr = new ArrayList<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (arr.contains(s.charAt(i))) {
                return false;
            } else {
                arr.add(s.charAt(i));
            }
        }
        return true;
    }

    public static boolean isUniqueEfficient(String s) {
        int counter = 0;
        while (counter < s.length()) {
            char c = s.charAt(counter);
            String sub = s.substring(counter+1, s.length());
            if (sub.indexOf(c) != -1) {
                return false;
            }
            counter++;
        }
        return true;
    }

    public static void SumOfSurroundingCells(int width, int[][] sourceMatrix, int[][] resultMatrix) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                int sum = 0;
                // Check top left
                if ((i%width) - 1 > 0 && (j%width) - 1 > 0) {
                    sum += sourceMatrix[i-1][j-1];
                }
                // Check top
                if ((i%width) - 1 > 0) {
                    sum += sourceMatrix[i-1][j];
                }
                // Check top right
                if ((i%width) - 1 > 0 && (j%width) + 1 < width) {
                    sum += sourceMatrix[i-1][j+1];
                }
                // Check right
                if ((j%width) + 1 < width) {
                    sum += sourceMatrix[i][j+1];
                }
                // Check bottom right
                if ((i%width) + 1 < width && (j%width) + 1 < width) {
                    sum += sourceMatrix[i+1][j+1];
                }
                // Check bottom
                if ((i%width) + 1 < width) {
                    sum += sourceMatrix[i+1][j];
                }
                // Check bottom left
                if ((i%width) + 1 < width && (j%width) - 1 > 0) {
                    sum += sourceMatrix[i+1][j-1];
                }
                // Check left
                if ((j%width) - 1 > 0) {
                    sum += sourceMatrix[i][j-1];
                }
                resultMatrix[i][j] = sum;
            }
        }

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(resultMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }


    /**
     * 1.2 Check Permutation: Given two strings, write a method to decide if one is a permutation of the other
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean checkPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        HashMap<Character, Integer> h1 = new HashMap<>();
        HashMap<Character, Integer> h2 = new HashMap<>();

        for (int i = 0; i < s1.length(); i++) {
            if (!h1.containsKey(s1.charAt(i))) {
                h1.put(s1.charAt(i), 1);
            } else {
                h1.put(s1.charAt(i), h1.get(s1.charAt(i)) + 1);
            }

            if (!h2.containsKey(s2.charAt(i))) {
                h2.put(s2.charAt(i), 1);
            } else {
                h2.put(s2.charAt(i), h2.get(s2.charAt(i)) + 1);
            }
        }

        for (Character c : h1.keySet()) {
            if (h1.get(c) != h2.get(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 1.3 URLify: Write a method to replace all spaces in a strong with `$20`. You may assume that
     * the string has sufficient space at the end to hold the additional characters, and that you are
     * given the "true" length of the string.
     *
     * @param s
     * @param length
     */
    public static void URLify(char[] s, int length) {
        int i = 0;
        int fullLen = length;

        while (i < fullLen) {
            if (s[i] == ' ') {
                fullLen += 2;
                int j = fullLen - 1;
                while (j > i) {
                    s[j] = s[j-2];
                    j--;
                }
                s[i] = '%';
                s[i+1] = '2';
                s[i+2] = '0';
                i += 3;
            } else {
                i++;
            }
        }
        printArray(s, 17);
    }

    /**
     * 1.4 Palindrome Permutation: Given a string, write a function to check if it is a permutation
     * of a palindrome. A palindrome is a word or phrase that is the same forwards and backwards. A
     * permutation is a rearrangement of letters. The palindrome does not need to be limited to just
     * dictionary words
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean isPalindromePermutation(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        HashMap<Character, Integer> h1 = new HashMap<>();
        HashMap<Character, Integer> h2 = new HashMap<>();
        int i = 0;
        int j = s1.length() - 1;
        while (i < s1.length()) {
            if (s1.charAt(i) != s2.charAt(j)) {
                return false;
            } else {
                if (h1.containsKey(s1.charAt(i))) {
                    h1.put(s1.charAt(i), h1.get(s1.charAt(i)) + 1);
                } else {
                    h1.put(s1.charAt(i), 1);
                }

                if (h2.containsKey(s2.charAt(j))) {
                    h2.put(s2.charAt(i), h2.get(s2.charAt(j)) + 1);
                } else {
                    h2.put(s2.charAt(j), 1);
                }
            }
            i++;
            j--;
        }
        for (Character c : h1.keySet()) {
            if (h1.get(c) != h2.get(c)) {
                return false;
            }
        }
        return true;
    }

    public static void printArray(char[] arr, int length) {
        for (int i = 0; i < length; i++) {
            System.out.print(arr[i]);
        }
    }

    /**
     * 1.5 One Away: There are three types of edits that can be performed on strings: insert
     * a character, remove a character, or replace a character. Given two strings, write a function
     * to check if they are one edit (or zero edits) away.
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean oneAway(String s1, String s2) {
        int length1 = s1.length();
        int length2 = s2.length();
        int diff = 0;
        if (Math.abs(length1 - length2) > 1) {
            return false;
        } else if (length1 == length2) {
            for (int i = 0; i < length1; i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    diff++;
                }
            }
        } else {
            String greater;
            String lesser;

            if (length1 > length2) {
                greater = s1;
                lesser = s2;
            } else {
                greater = s2;
                lesser = s1;
            }

            int i = 0;
            int j = 0;
            while (i < greater.length() && diff < 2) {
                if (greater.charAt(i) != lesser.charAt(j)) {
                    diff ++;
                    i++;
                } else {
                    i++;
                    j++;
                }
            }
        }
        return diff <= 1;
    }

    /**
     * 1.6 String Compression: Implement a method to perform basic string compression using the
     * counts of repeated characters. For example, the string aabcccccaaa would become a2b1c5a3.
     * If the "compressed" string would not become smaller than the original string, your method should
     * return the original string. You can assume the string has only uppercase and lowercase characters
     * (a-z)
     *
     * @param s
     * @return
     */
    public static String compressString(String s) {
        HashMap<Character, Integer> hm = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (hm.containsKey(s.charAt(i))) {
                hm.put(s.charAt(i), hm.get(s.charAt(i)) + 1);
            } else {
                hm.put(s.charAt(i), 1);
            }
        }
        String compressedString = "";
        for (Character c : hm.keySet()) {
            compressedString += c;
            compressedString += hm.get(c);
        }
        return s.length() > compressedString.length() ? compressedString : s;
    }

    /**
     * 1.7 Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is
     * 4 bytes, write a method to rotate the image by 90 degrees. Can you do this in place?
     *
     * @param matrix
     * @param n
     */
    public static void rotateMatrix(int[][] matrix, int n) {
        int l = n/2; int s = n-1;
        for (int i = 0; i < l; i++) {
            for (int j = i; j < s - i; j++) {
                int topLeft = matrix[i][j];
                int topRight = matrix[j][s - i];
                int botRight = matrix[s-i][s-j];
                int botLeft = matrix[s-j][i];
                matrix[i][j] = botLeft;
                matrix[j][s-i] = topLeft;
                matrix[s-i][s-j] = topRight;
                matrix[s-j][i] = botRight;
            }
        }
        printMatrix(matrix, n, n);
    }

    public static void printMatrix(int[][] matrix, int m, int n) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 1.8 Zero Matrix: Write an algorithm such that if an element in an MxN matrix is 0, its entire row
     * and colum are set to 0
     *
     * @param matrix
     */
    public static void nullifyMatrix(int[][] matrix) {
        int M = matrix.length;
        int N = matrix[0].length;
        boolean[] rows = new boolean[M];
        boolean[] cols = new boolean[N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == 0) {
                    rows[i] = true;
                    cols[j] = true;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            if (rows[i]) {
                for (int j = 0; j < N; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            if (cols[i]) {
                for (int j = 0; j < M; j++) {
                    matrix[j][i] = 0;
                }
            }
        }

        printMatrix(matrix, M, N);
    }

    /**
     * 1.9 String Rotation. Assume you have a method isSubstring which checks if one word is a substring
     * of another. Given two substrings, s1 and s2, write code to check if s2 is a rotation of s1 using
     * only one call to isSubstring (e.g., "waterbottle" is a rotation of "erbottlewat").
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean isRotation(String s1, String s2) {
        if (s1.length() == s2.length() && s1.length() != 0) {
            String s1s1 = s1 + s1;
            return s1s1.contains(s2);
        }
        return false;
    }

    /*** Linked List Helper Functions ***/

    public static void printNodes(Node head) {
        Node n = head;
        while(n != null) {
            System.out.print(n.data + " ");
            n = n.next;
        }
        System.out.println();
    }

    public static Node arrayToLinkedList(int[] arr) {
        Node list = new Node(arr[0]);
        Node n = list;
        for (int i = 1; i < arr.length; i++) {
            n.next = new Node(arr[i]);
            n = n.next;
        }
        return list;
    }

    /*** End Linked List Helper Functions ***/

    /**
     * 2.1 Remove Dupes: Write code to remove duplicates from an unsorted linked list
     *
     * @param head
     * @return
     */
    public static Node removeDupes(Node head) {
        Node n = head;
        ArrayList<Integer> buffer = new ArrayList<Integer>();
        buffer.add(n.data);
        while (n.next != null) {
            int val = n.next.data;
            if (buffer.contains(val)) {
                n.next = n.next.next;
                val = n.next.data;
                if (n.next.next == null) {
                    n.next = null;
                    break;
                }
            }
            buffer.add(val);
            n = n.next;
        }
        return head;
    }

    /**
     * 2.2 Return Kth to Last: Implement an algorithm to find the kth to last element of a singly
     * linked list
     *
     * @param k
     * @param head
     * @return
     */
    public static Node returnKth(int k, Node head) {
        Node n  = head;
        while (n != null) {
            Node tail = n.next;
            int counter = 0;

            while (tail != null) {
                counter++;
                tail = tail.next;
            }

            if (counter == k) {
                return n;
            }

            n = n.next;
        }
        return n;
    }

    /**
     * 2.3 Delete Middle Node: Implement an algorithm to delete a node in the middle (i.e., any
     * node but the first and the last node, not necessarily the exact middle) of a singly
     * linked list, given only access to that node
     *
     * @param middle
     */
    public static void deleteMiddleNode(Node middle) {
        Node n = middle;
        if (n == null || n.next == null) {
            return;
        }
        while (n.next.next != null) {
            n.data = n.next.data;
            n = n.next;
        }
        n.data = n.next.data;
        n.next = null;
    }

    /**
     * 2.4 Partition: Write code to partition a linked list around a value x, such that all nodes less than x come
     * before all nodes greater than or equal to x.
     * @param head
     * @param x
     * @return
     */
    private static Node partition(Node head, int x) {
        if (head == null) {
            return null;
        }
        Node left = null;
        Node right = null;
        Node n = head;
        while (n != null) {
            if (n.data < x) {
                if (left == null) {
                    left = new Node(n.data);
                } else {
                    left.appendToTail(n.data);
                }
            } else {
                if (right == null) {
                    right = new Node(n.data);
                } else {
                    right.appendToTail(n.data);
                }
            }
            n = n.next;
        }
        Node result = null;
        if (left == null) {
            result = right;
        } else {
            Node temp = left;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = right;
            result = left;
        }
        return result;
    }

    /**
     * 2.5 Sum Lists: You have two numbers represented by a linked list, where each node contains a
     * single digit. The digits are stored in reverse order. such that the 1's digit is at the head
     * of the list. Write a function that adds the two numbers and returns the sum as a linked list
     */
    private static int linkedListSumHelper(Node head) {
        Node n = head;
        int total = 0;
        int place = 0;
        while (n != null) {
            total += (Math.pow(10, place)) * n.data;
            place++;
            n = n.next;
        }
        return total;
    }

    private static int linkedListSum(Node l1, Node l2) {
        int total1 = linkedListSumHelper(l1);
        int total2 = linkedListSumHelper(l2);
        return total1 + total2;
    }

    private static int linkedListSumHelperV2(Node head) {
        String s = "";
        Node n = head;
        while (n != null) {
            s += String.valueOf(n.data);
            n = n.next;
        }
        return Integer.valueOf(s);
    }

    private static int linkedListSumV2(Node l1, Node l2) {
        int total1 = linkedListSumHelperV2(l1);
        int total2 = linkedListSumHelperV2(l2);
        return total1 + total2;
    }

    /**
     * 2.6 Palindrome: Implement a function to check if a linked list is a palindrome
     * @param head
     * @return
     */
    private static boolean isLinkedListPalindrome(Node head) {
        Node reversed = reverseLinkedList(head);
        Node n1 = head;
        Node n2 = reversed;
        while (n1 != null && n2 != null) {
            if (n1.data != n2.data) {
                return false;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        return true;
    }

    private static Node reverseLinkedList(Node head) {
        Node n = head.next;
        Node reversed = new Node(head.data);
        while (n != null) {
            Node temp = new Node(n.data);
            temp.next = reversed;
            reversed = temp;
            n = n.next;
        }
        return reversed;
    }

    public static void main(String[] args){
        int arr[] = {1, 0, 1, 1, 0, 1};
        Node list = arrayToLinkedList(arr);
        System.out.println(isLinkedListPalindrome(list));
    }
}
