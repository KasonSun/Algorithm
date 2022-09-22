package island;

/**
 * 463.岛屿的周长（easy）
 * 给定一个 row x col 的二维网格地图 grid ，其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。
 * 网格中的格子 水平和垂直 方向相连（对角线方向不相连）。整个网格被水完全包围，但其中---恰好有一个岛屿----（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 *
 * 周长：边界长度+与海洋相邻的长度
 */
public class IslandCircleLength_463 {
    /**
     * 法一：dfs
     * @param grid
     * @return
     */
    public int islandCircleLength(int[][] grid) {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    // 题目限制只有一个岛屿，计算一个即可
                    return dfs(grid, r, c);
                }
            }
        }
        return 0;
    }

    int dfs(int[][] grid, int r, int c) {
        // 函数因为「坐标 (r, c) 超出网格范围」返回，对应一条黄色的边
        if (!inArea(grid, r, c)) {
            return 1;
        }
        // 函数因为「当前格子是海洋格子」返回，对应一条蓝色的边
        if (grid[r][c] == 0) {
            return 1;
        }
        // 函数因为「当前格子是已遍历的陆地格子」返回，和周长没关系
        if (grid[r][c] != 1) {
            return 0;
        }
        grid[r][c] = 2;
        return dfs(grid, r - 1, c)
                + dfs(grid, r + 1, c)
                + dfs(grid, r, c - 1)
                + dfs(grid, r, c + 1);
    }

    // 判断坐标 (r, c) 是否在网格中
    boolean inArea(int[][] grid, int r, int c) {
        return 0 <= r && r < grid.length
                && 0 <= c && c < grid[0].length;
    }

    /**
     * 法二：直接遍历，由遍历方向，看当前位置和之前遍历过的位置是否出现相邻，然后减去对应的边
     * 从左向右，从上到下遍历，大方向是右下，因此当前位置应该与左上方比
     * @param grid
     * @return
     */
    public int islandCircleLength01(int[][] grid) {
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j]==0) continue;
                if(grid[i][j]==1) result+=4;
                if(i>0 && grid[i-1][j]==1) result -= 2;//上方相邻减去两条边
                if(j>0 && grid[i][j-1]==1) result -= 2;//左侧相邻减去两条边
            }
        }
        return result;
    }
}
