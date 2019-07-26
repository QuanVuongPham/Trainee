package Task1;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


/**
 * Sử dụng Set để tìm tập giao và tập hợp của 2 tập hợp có 200000 phn tử
 */

/**
 * tạo 1 set con làm tập giao để đảm bảo luôn có phần giao nhau
 * tạo 2 set cha và add set con vào 2 tập cha đó
 * thực hiện lấy giao và hợp như bình thường
 *
 */

public class SetIntersectionUnion
{
    public static void main(String[] args) {
        Set<Integer> SubSet = new HashSet<Integer>();
        Set<Integer> ParentSet1 = new HashSet<Integer>();
        Set<Integer> ParentSet2 = new HashSet<Integer>();

        Random random = new Random();

        while(true)
        {
            SubSet.add(random.nextInt());

            if(SubSet.size() == 50000) break;
        }

        ParentSet1.addAll(SubSet);
        while(true)
        {
            ParentSet1.add(random.nextInt());

            if(ParentSet1.size() == 200000) break;
        }

        ParentSet2.addAll(SubSet);
        while(true)
        {
            ParentSet2.add(random.nextInt());

            if(ParentSet2.size() == 200000) break;
        }

        System.out.println(ParentSet1);
        System.out.println(ParentSet2);

        Set<Integer> Intersection = new HashSet<Integer>(ParentSet1);
        Intersection.retainAll(ParentSet2);
        System.out.println(Intersection.size());

        Set<Integer> Union = new HashSet<Integer>(ParentSet1);
        Union.addAll(ParentSet2);
        System.out.println(Union.size());
    }

//    /**
//     * tạo ra một set có 200000 phần tử
//     * @param set
//     */
//    static void CreatSet(Set<Integer> set)
//    {
//        Random number = new Random();
//
//        while (true)
//        {
//            set.add(number.nextInt(250000)); // chọn cận trên là 250000 để tăng kích thước tập giao
//            if(set.size() == 200000) break;
//        }
//    }
//
//    public static void main(String[] args)
//    {
//        Set<Integer> set1 = new TreeSet<Integer>();
//        Set<Integer> set2 = new TreeSet<Integer>();
//
//        CreatSet(set1);
//        CreatSet(set2);
//
//        Set<Integer> Intersection = new HashSet<Integer>(set1);
//        Intersection.retainAll(set2);
//
//        Set<Integer> Union = new HashSet<Integer>(set1);
//        Union.addAll(set2);
//    }
}
