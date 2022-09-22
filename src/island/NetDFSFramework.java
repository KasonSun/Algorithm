package island;

/**
 * 网格Dfs框架代码
 */
public class NetDFSFramework {
    public void dfs(int[][] grid, int r, int c) {
        //判断是否在网格区域中
        if (!inArea(grid, r, c)) {
            return;
        }
        //如果这个格子不是岛屿，直接返回
        if (grid[r][c] != 1) {
            return;
        }
        //将遍历过的格子标记为2
        grid[r][c] = 2;

        //访问上、下、左、右四个相邻点
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    private boolean inArea(int[][] grid, int r, int c) {
        return 0 <= r && r < grid.length && 0 <= c && c < grid[0].length;
    }
}
