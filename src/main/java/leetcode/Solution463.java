package leetcode;

public class Solution463 {

  int ans = 0;
  int n;
  int m;

  static int[] dx = new int[]{0, 0, -1, 1};
  static int[] dy = new int[]{1, -1, 0, 0};

  void dfs(int[][] grid, int[] vis, int x, int y) {
    vis[x * m + y] = 1;
    // temp表示这个(x,y)这个点周围有几个临近的点
    int temp = 0;
    for (int i = 0; i < 4; i++) {
      int tx = x + dx[i];
      int ty = y + dy[i];
      if (tx >= 0 && tx < n && ty >= 0 && ty < m && grid[tx][ty] == 1) {
        temp++;
        if (vis[tx * m + ty] == 0) {
          dfs(grid, vis, tx, ty);
        }
      }
    }
    this.ans += (4 - temp);
    System.out.println(String.format("(%d, %d) =  %d", x, y, 4 - temp));
  }

  public int islandPerimeter(int[][] grid) {
    if (grid.length == 0 || grid[0].length == 0) {
      return 0;
    }
    this.ans = 0;
    this.n = grid.length;
    this.m = grid[0].length;

    int[] vis = new int[n * m + 5];
    for (int i = 0; i < n * m; i++) {
      vis[i] = 0;
    }

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (vis[i * m + j] == 0 && grid[i][j] == 1) {
          dfs(grid, vis, i, j);
        }
      }
    }
    return this.ans;
  }

  public static void main(String[] args) {

    int[][] grid = new int[][]{{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0},
        {0, 0, 0, 0}};
    int res = new Solution463().islandPerimeter(grid);
    System.out.println("res: " + res);
    assert res == 16;

    int[][] grid1 = new int[][]{{1, 1, 1}, {1, 0, 1}};
    int res1 = new Solution463().islandPerimeter(grid1);
    System.out.println("res1: " + res1);
    assert res1 == 12;

    int[][] grid2 = new int[][]{{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}};
    int res2 = new Solution463().islandPerimeter(grid2);
    System.out.println("res2: " + res2);
    assert res2 == 16;

    System.out.println("AC~");
  }
}
