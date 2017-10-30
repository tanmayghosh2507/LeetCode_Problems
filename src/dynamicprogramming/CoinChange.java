package dynamicprogramming;

public class CoinChange {

	public static void main(String[] args) {
		int[] coins = { 7, 2, 3, 6 };
		int amount = 13;
		CoinChange coinChange = new CoinChange();
		System.out.println(coinChange.coinChange(coins, amount));
	}

	public int coinChange(int[] coins, int amount) {
		int[] total = new int[amount + 1];
		total[0] = 0;

		for (int i = 1; i <= amount; i++) {
			total[i] = Integer.MAX_VALUE;
		}

		for (int j = 0; j < coins.length; j++) {
			for (int i = 0; i <= amount; i++) {
				if (coins[j] <= i) {
					if (total[i - coins[j]] < total[i])
						total[i] = Math.min(total[i], 1 + total[i - coins[j]]);
				}
			}
		}

		if (total[amount] == Integer.MAX_VALUE)
			return -1;
		else
			return total[amount];
	}

}
