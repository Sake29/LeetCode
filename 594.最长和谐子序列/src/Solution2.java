import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Solution2 {

    public int findLHS(int[] nums) {
        int res = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])){
                map.put(nums[i],map.get(nums[i])+1);
            }
            else {
                map.put(nums[i],1);
            }
        }
        for (int key:map.keySet()){
            if (map.containsKey(key+1)){
                res = Math.max(res, map.get(key) + map.get(key + 1));
            }
        }
        return res;
    }
    @Test
    public void test(){
        int[] nums = {1,3,2,2,5,2,3,7};
        //int[] nums = {1,2,2,1};
        int res = findLHS(nums);
        System.out.println(res);
    }
}
