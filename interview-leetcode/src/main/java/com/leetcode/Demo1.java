import java.util.HashSet;
import java.util.Set;

public class Demo1 {
    public static void main(String[] args) {
        System.out.println("Hello World!");

    }
    public int[] distinctDifferenceArray(int[] nums) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        int[] res = new int[nums.length];
        int index = 0;
        for(int i=0; i < nums.length; i++){
            set.add(nums[i]);
            index++;
            for(int j=index ;j < nums.length; j++){
                set2.add(nums[j]);
            }
            res[i] = set.size() - set2.size();
            set2.clear();
        }
        return res;
    }
}
