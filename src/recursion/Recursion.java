package recursion;

public class Recursion {

	public static void main(String[] args) {
		System.out.println("Factorial:" + factorial(4));
		System.out.println("Fibonacci:" + fib(3));
		System.out.println("Suma:" + exclusiveSum(3));
		System.out.println(pyramid(3));
		System.out.println("Palindromo: " + isPalindrome("AAAAB"));
	}

	public static int factorial(int n) {
		if (n<=1) return 1;
		return n * factorial(n-1);
	}
	
	public static int fib(int n) {
		if (n==0 || n == 1) return n;
		return fib(n-1) + fib(n-2);
	}
	
	public static int exclusiveSum(int n) {
		if (n<=1) return 0;
		return n-1 + exclusiveSum(n-1);
	}
	
	public static String pyramid(int n) {
		if (n == 0) return "";
		else {
			System.out.println("X ".repeat(n));
			return pyramid(n-1);
		}
	}
	
	public static boolean isPalindrome(String word) {		
		if(word.length() == 0) return true;
		
		return _isPalindrome(word,0,word.length()-1);
	}
	
	public static boolean _isPalindrome(String word, int inicio, int fin) {
		if (inicio >= fin) return true;
		if (word.charAt(inicio) != word.charAt(fin)) return false;
		return _isPalindrome(word, inicio + 1, fin-1);
	}
	
}
