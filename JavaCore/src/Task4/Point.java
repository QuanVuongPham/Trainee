package Task4;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * sinh 8000 điểm cách A(800, 800) không quá 400
 * sinh 10000 điểm cách B(4000, 800) không quá 500
 * sinh 12000 điểm cách C(2400, 2400) không quá 600
 * <p>
 * trộn 30000 điểm ghi ra file ouput4
 */
class Point {
    int Quantity; // số lượng điểm cần sinh quanh điểm đã cho
    Random random = new Random();


    /**
     * sinh bằng hàm random với cận trên và cận dưới như trong code
     *
     * @param x        hoành độ điểm đã cho
     * @param y        tung độ điểm đã cho
     * @param distance khoảng cách đã cho
     * @return
     */
    public int[] GeneratePointSurround(int x, int y, int distance) {
        int[] point = new int[2];
        while (true) {
            point[0] = x - distance + random.nextInt(x + distance);
            point[1] = y - distance + random.nextInt(y + distance);

            if (Math.sqrt((point[0] - x) * (point[0] - x) + (point[1] - y) * (point[1] - y)) <= distance) break;
        }

        return point;
    }

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        List<int[]> ListPoint = new ArrayList<int[]>(); // dùng để chứa tất cả các điểm sinh ra


        /**
         * sinh cụm quanh A
         */
        Point A = new Point();
        A.Quantity = 8000;
        while (true) {
            int[] NewPoint = A.GeneratePointSurround(800, 800, 400);
            if (!ListPoint.contains(NewPoint))
                ListPoint.add(NewPoint);
            if (ListPoint.size() == A.Quantity) break;
        }


        /**
         * sinh cụm quanh B
         */
        Point B = new Point();
        B.Quantity = 10000;
        while (true) {
            int[] NewPoint = B.GeneratePointSurround(4000, 800, 500);
            if (!ListPoint.contains(NewPoint))
                ListPoint.add(NewPoint);
            if (ListPoint.size() == A.Quantity + B.Quantity) break;
        }


        /**
         * sinh cụm quanh C
         */
        Point C = new Point();
        C.Quantity = 12000;
        while (true) {
            int[] NewPoint = C.GeneratePointSurround(2400, 2400, 600);
            if (!ListPoint.contains(NewPoint))
                ListPoint.add(NewPoint);
            if (ListPoint.size() == A.Quantity + B.Quantity + C.Quantity) break;
        }


        Collections.shuffle(ListPoint);

        PrintWriter printWriter = new PrintWriter("output4", "UTF-8");
        for (int[] element : ListPoint)
        {
            for (int i = 0; i < element.length; i++)
            {
                printWriter.print(element[i] + "  ");
            }
            printWriter.println();
        }
        printWriter.close();

    }

}
