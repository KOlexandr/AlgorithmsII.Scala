package lab10_11;

/** Cormen, Leiserson, Rivest, Stein. Introduction to Algorithms, 2nd Ed.
 * Chapter 32. String Matching
 */
public class StringJMatcher {

    /** Cormen, Leiserson, Rivest, Stein. Introduction to Algorithms, 2nd Ed.
     * Chapter 32.1. The naive string-matching algorithm
     */
    public static int naive(final String src, final String find){
        if(find.length() > src.length()) {
            return -1;
        }
        final int n = src.length();
        final int m = find.length();
        for (int i = 0; i <= n - m; i++) {
            if(includes(src, find, i)){
                return i;
            }
        }
        return -1;
    }

    /** Cormen, Leiserson, Rivest, Stein. Introduction to Algorithms, 2nd Ed.
     * Chapter 32.2. The Rabin-Karp algorithm
     */
    public static int rabinKarp(final String src, final String find, final int d, final int q){
        if(find.length() > src.length()) {
            return -1;
        }
        final int n = src.length();
        final int m = find.length();
        final int h = ((int) Math.pow(d, m - 1)) % q;

        int p = 0;
        int t = 0;

        for (int i = 0; i < m; i++) {
            t = (d * t + src.charAt(i)) % q;
            p = (d * p + find.charAt(i)) % q;
        }
        for (int s = 0; s < n - m; s++) {
            if(p == t && includes(src, find, s)){
                return s;
            } else {
                t = (d * (t - src.charAt(s) * h) + src.charAt(s + m)) % q;
                if(t < 0) {
                    t += q;
                }
            }
        }
        if(p == t && includes(src, find, n - m)) {
            return n - m;
        }
        return -1;
    }

    private static boolean includes(final String src, final String find, final int startIdx){
        if(src.length() < find.length()){
            return false;
        }
        for (int i = 0; i < find.length(); i++) {
            if(src.charAt(i + startIdx) != find.charAt(i)){
                return false;
            }
        }
        return true;
    }

    /** Cormen, Leiserson, Rivest, Stein. Introduction to Algorithms, 2nd Ed.
     * Chapter 32.4. The Knuth-Morris-Pratt algorithm
     */
    public static int kmp(final String src, final String find){
        if(find.length() > src.length()) {
            return -1;
        }
        final int n = src.length();
        final int m = find.length();
        final int[] pi = computePrefixFunction(find);
        int q = -1;
        for (int i = 0; i < n; i++) {
            while(q >= 0 && find.charAt(q + 1) != src.charAt(i)) {
                q = pi[q];
            }
            if(find.charAt(q + 1) == src.charAt(i)) {
                q++;
            }
            if(q == m - 1){
                return i - q;
            }
        }
        return -1;
    }

    private static int[] computePrefixFunction(final String str) {
        final int m = str.length();
        final int[] pi = new int[m];
        pi[0] = -1;
        int k = -1;
        for (int q = 1; q < m; q++) {
            while(k >= 0 && str.charAt(k + 1) != str.charAt(q)){
                k = pi[k];
            }
            if(str.charAt(k + 1) == str.charAt(q)){
                k++;
            }
            pi[q] = k;
        }
        return pi;
    }

    /** Cormen, Leiserson, Rivest, Stein. Introduction to Algorithms, 2nd Ed.
     * Chapter 32.3. String matching with ﬁnite automata
     */
    public static int finiteAutomaton(final String src, final String find){
        return -1;
    }
}