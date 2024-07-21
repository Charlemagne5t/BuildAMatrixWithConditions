import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        List<List<Integer>> gR = new ArrayList<>();
        int[][] res = new int[k][k];
        for (int i = 0; i < k; i++) {
            gR.add(new ArrayList<>());
        }
        for (int i = 0; i < rowConditions.length; i++) {
            gR.get(rowConditions[i][0] - 1).add(rowConditions[i][1] - 1);
        }
        List<Integer> sortedR = new ArrayList<>();
        int[] vis = new int[k];
        for (int i = 0; i < k; i++) {
            if (!topSort(gR, sortedR, i, vis)) {
                return new int[][]{};
            }

        }


        List<List<Integer>> gC = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            gC.add(new ArrayList<>());
        }
        for (int i = 0; i < colConditions.length; i++) {
            gC.get(colConditions[i][0] - 1).add(colConditions[i][1] - 1);
        }
        List<Integer> sortedC = new ArrayList<>();
        vis = new int[k];
        for (int i = 0; i < k; i++) {
            if (!topSort(gC, sortedC, i, vis)) {
                return new int[][]{};
            }

        }
        Map<Integer, int[]> coords = new HashMap();
        for (int i = 1; i < k + 1; i++) {
            coords.put(i, new int[]{-1, -1});
        }
        for (int i = 0; i < k; i++) {
            coords.get(sortedR.get(i))[0] = k - i - 1;
            coords.get(sortedC.get(i))[1] = k - i - 1;
        }

        for (Map.Entry<Integer, int[]> e : coords.entrySet()) {
            int i = e.getValue()[0];
            int j = e.getValue()[1];
            res[i][j] = e.getKey();
        }

        return res;
    }

    boolean topSort(List<List<Integer>> g, List<Integer> sorted, int i, int[] vis) {
        if (vis[i] == 2) {
            return true;
        }
        if (vis[i] == 1) {
            return false;
        }
        vis[i] = 1; // visiting
        for (int next : g.get(i)) {
            if (!topSort(g, sorted, next, vis)) {
                return false;
            }

        }
        vis[i] = 2;
        sorted.add(i + 1);
        return true;
    }
}