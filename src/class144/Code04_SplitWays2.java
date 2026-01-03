package class144;

// 分割的方法数
// 有同学找到了测试链接，题意几乎一样，而且数据量小很多
// 唯一的区别是：
// 课上讲的题意，单独的3可以分裂成(1, 2)、(2, 1)，一共两种方式
// 测试链接题意，单独的3可以分裂成(0, 3)、(1, 2)、(2, 1)、(3, 0)，一共四种方式
// 也就是对单独的v来说，课上讲的题意，分裂方式为v-1种。测试链接题意，分裂方式为v+1种
// 别的没有任何区别，实现代码中唯一有注释的那行，是仅有的改动
// 测试链接 : https://leetcode.cn/problems/find-the-count-of-monotonic-pairs-ii/
public class Code04_SplitWays2 {

	public static final int MOD = 1000000007;

	// 暴力方法
	// 为了验证
	// 注意这里允许 b 和 c 从 0 开始
	public static int ways1(int[] arr) {
		int ans = 0;
		for (int b = 0, c = arr[0]; b <= arr[0]; b++, c--) {
			ans += f(arr, 1, b, c);
		}
		return ans;
	}

	public static int f(int[] arr, int i, int preb, int prec) {
		if (i == arr.length) {
			return 1;
		}
		int ans = 0;
		for (int b = 0, c = arr[i]; b <= arr[i]; b++, c--) {
			if (preb <= b && prec >= c) {
				ans += f(arr, i + 1, b, c);
			}
		}
		return ans;
	}

	// 正式方法
	// 转化成杨辉三角
	public static int ways2(int[] arr) {
		int n = arr.length;
		// 原始题意，k = arr[0] - 1，这里改成，k = arr[0] + 1
		// 其他代码毫无区别
		int k = arr[0] + 1;
		for (int i = 1; i < n && k > 0; i++) {
			if (arr[i - 1] > arr[i]) {
				k -= arr[i - 1] - arr[i];
			}
		}
		if (k <= 0) {
			return 0;
		}
		return c(k + n - 1, n);
	}

	// LeetCode提交方法
	public static int countOfPairs(int[] arr) {
		int n = arr.length;
		// 原始题意，k = arr[0] - 1，这里改成，k = arr[0] + 1
		// 其他代码毫无区别
		int k = arr[0] + 1;
		for (int i = 1; i < n && k > 0; i++) {
			if (arr[i - 1] > arr[i]) {
				k -= arr[i - 1] - arr[i];
			}
		}
		if (k <= 0) {
			return 0;
		}
		return c(k + n - 1, n);
	}

	public static int c(int n, int k) {
		long fac = 1;
		long inv1 = 1;
		long inv2 = 1;
		for (int i = 1; i <= n; i++) {
			fac = (fac * i) % MOD;
			if (i == k) {
				inv1 = power(fac, MOD - 2);
			}
			if (i == n - k) {
				inv2 = power(fac, MOD - 2);
			}
		}
		return (int) ((((fac * inv1) % MOD) * inv2) % MOD);
	}

	public static long power(long x, long p) {
		long ans = 1;
		while (p > 0) {
			if ((p & 1) == 1) {
				ans = (ans * x) % MOD;
			}
			x = (x * x) % MOD;
			p >>= 1;
		}
		return ans;
	}

	// 为了测试
	public static int[] randomArray(int n, int v) {
		int[] ans = new int[n];
		for (int i = 0; i < n; i++) {
			ans[i] = (int) (Math.random() * v) + 1;
		}
		return ans;
	}

	// 为了测试
	public static void main(String[] args) {
		System.out.println("功能测试开始");
		int N = 10;
		int V = 20;
		int test = 20000;
		for (int i = 0; i < test; i++) {
			int n = (int) (Math.random() * N) + 1;
			int[] arr = randomArray(n, V);
			int ans1 = ways1(arr);
			int ans2 = ways2(arr);
			if (ans1 != ans2) {
				System.out.println("出错了!");
			}
		}
		System.out.println("功能测试结束");

		System.out.println("==========");

		System.out.println("性能测试开始");
		int n = 10000000;
		int v = 10000000;
		long start, end;
		int[] arr = new int[n];
		System.out.println("随机生成的数据测试");
		System.out.println("数组长度 : " + n);
		System.out.println("数值范围 : [" + 1 + "," + v + "]");
		for (int i = 0; i < n; i++) {
			arr[i] = (int) (Math.random() * v) + 1;
		}
		start = System.currentTimeMillis();
		ways2(arr);
		end = System.currentTimeMillis();
		System.out.println("运行时间 : " + (end - start) + " 毫秒");

		System.out.println();

		System.out.println("运行最慢的数据测试");
		System.out.println("数组长度 : " + n);
		System.out.println("数值都是 : " + v);
		System.out.println("这种情况其实是复杂度最高的情况");
		for (int i = 0; i < n; i++) {
			arr[i] = v;
		}
		start = System.currentTimeMillis();
		ways2(arr);
		end = System.currentTimeMillis();
		System.out.println("运行时间 : " + (end - start) + " 毫秒");
		System.out.println("性能测试结束");
	}

}
