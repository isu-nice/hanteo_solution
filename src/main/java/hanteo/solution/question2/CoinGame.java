package hanteo.solution.question2;

import java.util.Arrays;

import static hanteo.solution.question2.exception.ExceptionMessage.*;

public class CoinGame {

    public int countSumCombinations(int sum, int[] coins) {
        validateInput(sum, coins);

        int[] dp = new int[sum + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int i = coin; i <= sum; i++) {
                dp[i] += dp[i - coin];
            }
        }

        return dp[sum];
    }

    private void validateInput(int sum, int[] coins) {
        int minCoin = Arrays.stream(coins).min().orElse(0);

        validateSum(sum);
        validateCoins(minCoin);
        validateMinCoin(sum, minCoin);
    }

    /**
     * 합계가 1보다 작은 경우 exception
     */
    private void validateSum(int sum) {
        if (sum < 1) {
            throw new IllegalArgumentException(INVALID_SUM.getMessage());
        }
    }

    /**
     * 음수나 0인 동전이 있는 경우 exception
     */
    private static void validateCoins(int minCoin) {
        if (minCoin <= 0) {
            throw new IllegalArgumentException(INVALID_COINS.getMessage());
        }
    }

    /**
     * 동전의 최소값보다 합계가 작은 경우 exception
     */
    private static void validateMinCoin(int sum, int minCoin) {
        if (minCoin > sum) {
            throw new IllegalArgumentException(INVALID_MIN_COIN.getMessage());
        }
    }
}
