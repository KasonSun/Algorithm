package island;

/**
 * 200岛屿数量（Easy）.给你一个由'1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 */
public class IslandNum_200 {
    public void dfs(char[][] grid, int r, int c) {
        //判断是否在网格区域中
        if (!inArea(grid, r, c)) {
            return;
        }
        //如果这个格子不是岛屿，直接返回
        if (grid[r][c] != '1') {
            return;
        }
        //将遍历过的格子标记为2
        grid[r][c] = '2';

        //访问上、下、左、右四个相邻点
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    private boolean inArea(char[][] grid, int r, int c) {
        return 0 <= r && r < grid.length && 0 <= c && c < grid[0].length;
    }

    public int islandNum(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int nums = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if(grid[r][c]=='1'){
                    nums++;
                    dfs(grid, r, c);
                }
            }
        }
        return nums;
    }
}
