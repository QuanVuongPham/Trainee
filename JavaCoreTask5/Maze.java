package task5;

import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class Maze
{
    public static void main(String[] args)
    {
        //
        JFrame frame = new JFrame();
        frame.setSize(650, 470);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MazePanel mp = new MazePanel();
        frame.add(mp);
        frame.setVisible(true);

    }

    public static class MazePanel extends JPanel
    {
        private static final long serialVersionUID = -566807999447681130L;
        private int[][] maze =
                { // khởi tạo ma trận mảng 2 chiều
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
                        {1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
                        {1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1},
                        {1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1},
                        {1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1},
                        {1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1},
                        {1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1},
                        {1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1},
                        {0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 2, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1},
                        {1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1},
                        {1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
                        {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
                        {1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1},
                        {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1},
                        {1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
                };
        private int sizeh, sizew, start, end;

        public MazePanel()
        {
            // Kích thước ma trận
            sizeh = 21;
            sizew = 31;
            start = 10;
            end = 0;
            solve();
            repaint(); // vẽ ma trận và lời giải
        }

        /**
         * Implement một phương pháp tìm đường nào đó.
         * <p>
         * Yêu cầu : Vẽ đường đi từ điểm bắt đầu đến điểm kết thúc. Không hiện
         * các phần thừa - là các phần đường cụt hoặc đường đi bị sai. Chỉ vẽ
         * tuyến đường chính đi từ điểm đầu (màu vàng) đến màu đỏ.
         * <p>
         * Đường đi từ điểm đầu đến điểm cuối được tô màu xanh dương, để tô màu
         * xanh dương hãy set giá trị của điểm trên ma trận sang một số > 2
         */

        public void solve()
        {
            // Hàm này chứa phương pháp tìm đường từ điểm start đến vị
            // trí màu đỏ trên ma trận
//            BFS(maze, 10, 0, 15, 10);

            Queue<Integer> Path = new LinkedList<Integer>();
            SearchPath(maze, 10, 0, Path);


        }

//        class Node
//        {
//            int x, y;
//
//            Node(int x, int y)
//            {
//                this.x = x;
//                this.y = y;
//            }
//        }
//
//        public boolean Valid(int[][] maze, boolean visited[][], int row, int col)
//        {
//            return (row >= 0) && (row <= maze.length) && (col >= 0) && (col < maze[0].length) && !visited[row][col];
//        }
//
//        public void BFS(int[][] maze,int XStart, int YStart, int XEnd,int YEnd)
//        {
//            boolean[][] visited = new boolean[maze.length][maze[0].length];
//
//            Queue<Node> queue = new ArrayDeque();
//
//            visited[XStart][YStart] = true;
//            queue.add(new Node(XStart, YStart));
//
//            while (!queue.isEmpty())
//            {
//                Node node = queue.poll();
//
//                XStart = node.x;
//                YStart = node.y;
//
//                if(XStart == XEnd && YStart == YEnd)
//                {
//                    break;
//                }
//
//                if(Valid(maze, visited, XStart + 1, YStart + 0))
//                {
//                    visited[XStart + 1][YStart + 0] = true;
//                    queue.add(new Node(XStart + 1, YStart + 0));
//                }
//
//                if(Valid(maze, visited, XStart - 1, YStart + 0))
//                {
//                    visited[XStart - 1][YStart + 0] = true;
//                    queue.add(new Node(XStart - 1, YStart + 0));
//                }
//
//                if(Valid(maze, visited, XStart + 0, YStart - 1))
//                {
//                    visited[XStart + 0][YStart - 1] = true;
//                    queue.add(new Node(XStart + 0, YStart - 1));
//                }
//
//                if(Valid(maze, visited, XStart + 0, YStart + 1))
//                {
//                    visited[XStart + 0][YStart + 1] = true;
//                    queue.add(new Node(XStart + 0, YStart + 1));
//                }
//            }
//        }

        public boolean SearchPath(int[][] maze, int x, int y, Queue<Integer> Path)
        {
            if (maze[x][y] == 2)
            {
                Path.add(x);
                Path.add(y);
                return true;
            }

            if (maze[x][y] == 0)
            {
                maze[x][y] = 3;
                int dx = -1;
                int dy = 0;
                if (SearchPath(maze, x + dx, y + dy, Path))
                {
                    Path.add(x);
                    Path.add(y);
                    return true;
                }

                dx = 1;
                dy = 0;
                if (SearchPath(maze, x + dx, y + dy, Path))
                {
                    Path.add(x);
                    Path.add(y);
                    return true;
                }

                dx = 0;
                dy = 1;
                if (SearchPath(maze, x + dx, y + dy, Path))
                {
                    Path.add(x);
                    Path.add(y);
                    return true;
                }

                dx = 0;
                dy = -1;
                if (SearchPath(maze, x + dx, y + dy, Path))
                {
                    Path.add(x);
                    Path.add(y);
                    return true;
                }

            }
            return false;
        }

        public void paintComponent(Graphics g) // vẽ ma trận + lời giải
        {
            super.paintComponent(g);
            for (int j = 0; j < sizew; j++) {
                for (int i = 0; i < sizeh; i++) {
                    if (i == start && j == end)
                        g.setColor(Color.yellow);
                    else if (maze[i][j] == 0)
                        g.setColor(Color.white);
                    else if (maze[i][j] == 1)
                        g.setColor(Color.black);
                    else if (maze[i][j] == 2)
                        g.setColor(Color.red);
                    else
                        g.setColor(Color.blue); // blue là màu của lời giải
                    g.fillRect(j * 20, i * 20, 20, 20);
                }
            }
        }
    }
}
