package interview.xiechen;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 一棵树，每个节点被染成红色r， 绿色g， 蓝色b。删除一条边，使得剩下的两个连通块各恰好有三种颜色。
 * 有多少合法的边可以删除？ 注：树指不含重边和自环的无向连通图
 */
public class Q3 {
    static int ans = 0;
    static int r, g, b;
    static boolean[] vis;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String str = scanner.nextLine();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == 'r') r += 1;
            if (c == 'g') g += 1;
            if (c == 'b') b += 1;
        }

        // build tree
        ArrayList<Integer>[] nodeMap = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            nodeMap[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            int x = scanner.nextInt() - 1;
            int y = scanner.nextInt() - 1;
            nodeMap[x].add(y);
            nodeMap[y].add(x);
        }

        vis = new boolean[n];
        dfs(str, nodeMap, 0);
        System.out.println(ans);
    }

    public static ChildColorNumber dfs(String str, ArrayList<Integer>[] nodeMap, int node) {
        vis[node] = true;
        ArrayList<Integer> children = nodeMap[node];
        ChildColorNumber colorNumber = new ChildColorNumber();

        for (Integer child : children) {
            if (vis[child]) continue;
            ChildColorNumber ccn = dfs(str, nodeMap, child);
            if (ccn.red > 0 && ccn.green > 0 && ccn.blue > 0 && ccn.red < r && ccn.green < g && ccn.blue < b) ans += 1;
            colorNumber.red += ccn.red;
            colorNumber.green += ccn.green;
            colorNumber.blue += ccn.blue;
        }
        if (str.charAt(node) == 'r') colorNumber.red += 1;
        if (str.charAt(node) == 'g') colorNumber.green += 1;
        if (str.charAt(node) == 'b') colorNumber.blue += 1;
        return colorNumber;
    }
}

class ChildColorNumber {
    public int red;
    public int green;
    public int blue;

    public ChildColorNumber() {
        this.red = 0;
        this.blue = 0;
        this.green = 0;
    }
}

