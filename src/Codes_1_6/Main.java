package Codes_1_6;
import java.lang.reflect.Array;
import java.util.*;
import java.lang.Math;

public class Main {

    public static class MyStack<E>{
        private HashSet<E>[] arr;
        private int elemCount = 0;

        public MyStack(){
            arr = new HashSet[100];
            for (int i=0; i<100; i++){
                arr[i] = new HashSet<>();
            }
        }

        public int size(){
            return elemCount;
        }

        public boolean contains(E elem){
            int hash = elem.hashCode() % 100;
            return arr[hash].contains(elem);
        }

        public void push(E elem){
            int hash = elem.hashCode() % 100;
            arr[hash].add(elem);
            elemCount++;
        }

        public void remove(E elem){
            if (!contains(elem)) throw new NullPointerException();
            int hash = elem.hashCode() % 100;
            arr[hash].remove(elem);
            elemCount--;
        }
    }

    static int countVariantsDynamic(int stearsCount){
        int[] data = new int[stearsCount + 1];
        data[0] = 0;
        data[1] = 1;
        data[2] = 2;
        for (int i = 3; i <= stearsCount; i++){
            data[i] = data[i - 1] + data[i - 2];
        }
        return data[stearsCount];
    }

    static int C(int n, int k) {
        if (k > n) return 0;
        int r = 1;
        int d;
        for (d = 1; d <= k; d++) {
            r *= n--;
            r /= d;
        }
        return r;
    }

    static int countVariants(int stearsCount){
        int answer = 0, n = stearsCount, k = 0;
        while (k <= n){
            answer += C(n, k);
            n--; k++;
        }
        return answer;
    }

    static boolean isProperly(String sequence){
        int cnt = 0;
        for (int i = 0; i < sequence.length(); i++){
            if (sequence.charAt(i) == '(') {
                cnt++;
            }else{
                cnt--;
                if (cnt < 0) return false;
            }
        }
        return cnt == 0;
    }

    static int notContains(int[] array){
        HashSet<Integer> set = new HashSet<>();
        int i = 0;
        for (i = 0; i < array.length; i++)
            set.add(array[i]);

        i = 1;
        while (set.contains(i)) i++;
        return i;
    }

    static int minSplit(int amount) {
        int [] coins = {50, 20, 10, 5, 1};
        int m = coins.length;
        int [] data = new int[amount + 1]; //yvela ricxvistvis amountis chatvlit inaxavs minimaluri xurdis raodenobas
        data[0] = 0;
        for (int i = 1; i <= amount; i++)
            data[i] = Integer.MAX_VALUE;

        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < m; j++) {
                if (coins[j] <= i) {
                    int res = data[i - coins[j]];
                    //es tu sruldeba anu optimaluri varianti ipova am droistvis
                    if (res != Integer.MAX_VALUE && res + 1 < data[i])
                        data[i] = res + 1;
                }
            }
        }
        return data[amount];
    }

    static boolean isPalindrome(String text){
        if (text.length() < 2) return true;

        char a = text.charAt(0);
        char b = text.charAt(text.length() - 1);

        if (a != b) return false;
        return isPalindrome(text.substring(1, text.length() - 1));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // TODO
        /*
            isPalindrome(String text)
            minSplit(int amount)
            notContains(int[] array)
            isProperly(String sequence)
            countVariants(int stearsCount)
            countVariantsDynamic(int stearsCount) - es dinamiuri amoxsnit
            MyStack<E> - O(1) remove
         */
    }
}