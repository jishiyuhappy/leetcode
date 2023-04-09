package easy;

public class BestTimeToBuySellStock {

    public int maxProfit(int[] prices) {
        if (prices.length <= 1) return 0;

        int curSmallest = prices[0];
        int curMax = 0;

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < curSmallest) {
                curSmallest = prices[i];
                continue;
            }
            curMax = Math.max(curMax, prices[i] - curSmallest);
        }

        return curMax;
    }
}
