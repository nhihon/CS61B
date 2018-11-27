public class hello{
	
	//0 1 1 2 3 5 8 13 21
	public static void main(String[] args){
		System.out.println(fib3(Integer.parseInt(args[0]), Integer.parseInt(args[0]), 0, 1 ));
		System.out.println(fib4(Integer.parseInt(args[0]), 0,1));
	}
	
	public static long fib(int n){
		if (n == 0) return 0;
		if (n == 1) return 1;
		return fib(n-1) + fib(n-2);
	}
	
	public static long fib2(int n){
		if (n < 2) return n;
		long f0 = 0;
		long f1 = 1;
		long result = 0;
		for (int i = 2; i <= n; i+=1){
			result = f0 + f1;
			f0 = f1;
			f1 = result;
		}
		return result;
	}
	
	public static long fib3(int n, int k, int f0, int f1){
		for (int i = 2; i <= n; i+=1){
			k = f0 + f1;
			f0 = f1;
			f1 = k;
		}
		return k;
	}
	
	// 0 1 1 2 3 5 8 13 21 34
	public static long fib4(int n, int f0, int f1){
		if (n == 0) return f0;
		return fib4(n-1, f1, f0+f1);
	}	
}
