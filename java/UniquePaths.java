import java.util.LinkedList;

/**
 * Created by vingc on 2017/6/7.
 * ref: https://leetcode.com/problems/unique-paths/#/description
 * num: 62
 A robot is located at the top-left corner of a m x n grid .

 The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

 How many possible unique paths are there?
 */
public class UniquePaths {
    static class Solution {
        class Pos {
            int x;
            int y;
            public Pos( int x, int y ) {
                this.x = x;
                this.y = y;
            }
        }
        public int uniquePaths(int m, int n) {
            LinkedList<Pos> queue = new LinkedList<>();

            if( m == 1 || n == 1 ) {
                return 1;
            }

            int cnt = 0;
            //bfs O(m*n)
            queue.add( new Pos(0,0) );

            while( queue.size() > 0 ) {
                Pos pos = queue.poll();


                //right
                if( pos.y+1 < n ) {
                    //we can move right
                    if( pos.x == m-1 && pos.y+1 == n-1 ) {
                        //reach the aim position. find a new way
                        cnt++;
                    }
                    else {
                        queue.add(new Pos(pos.x, pos.y + 1));
                    }
                }

                //down
                if( pos.x+1 < m ) {
                    //we can move down
                    if( pos.x+1 == m-1 && pos.y == n-1 ) {
                        //reach the aim position. find a new way
                        cnt++;
                    }
                    else {
                        queue.add(new Pos(pos.x + 1, pos.y));
                    }
                }
            }

            return cnt;
        }

        //dfs+dp
        int[][] path;
        public int uniquePaths2(int m, int n) {
            path = new int[m][n]; //the pathes from x & y to m-1 & n-1
            for( int i = 0; i < m; i++ ) {
                for( int j = 0; j < n; j++ ) {
                    path[i][j] = 0;
                }
            }

            return travel( 0, 0, m, n );
        }

        int travel( int x, int y, int m, int n ) {
            if( x == m-1 || y == n-1 ) {
                return 1;
            }
            if( path[x][y] != 0 ) {
                return path[x][y];
            }

            int right = travel( x, y+1, m, n );
            int down = travel( x+1, y, m, n );

            path[x][y] = right+down;
            return path[x][y];
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();

        System.out.println( sol.uniquePaths2( 23, 12 ) );
    }
}
