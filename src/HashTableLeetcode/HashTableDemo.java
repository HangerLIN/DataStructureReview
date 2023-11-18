package HashTableLeetcode;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HashTableDemo {
    public boolean isHappy(int n) {
        return isHappy(n, new HashSet<Integer>());
    }

    public boolean isHappy(int n, HashSet<Integer> set) {
        if (n == 1) {
            return true;
        }
        if (set.contains(n)) {
            return false;
        }
        set.add(n);
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return isHappy(sum, set);
    }

//    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
//        //思路：在两数之和的情况下做一个嵌套
//        int count = 0;
//        HashMap<Integer, Integer> map1 = new HashMap<>();
//        HashMap<Integer, Integer> map2 = new HashMap<>();
//        int index = 0;
//        for (int i = 0; i < nums1.length; i++) {
//            for (int j = 0; j < nums2.length; j++) {
//                map1.put(index++, nums1[i] + nums2[j]);
//            }
//
//            for (int k = 0; i < nums3.length; k++) {
//                for (int j = 0; j < nums4.length; j++) {
//                    map2.put(index++, nums3[i] + nums4[j]);
//                }
//            }
//        }
//
//        for (Integer key : map1.keySet()) {
//            if (map2.containsKey(-map1.get(key))) {
//                count += map1.get(key) * map2.get(-map1.get(key));
//            }
//        }
//        return count;
//    }

    public List<List<Integer>> threeSum(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int target = -nums[i] - nums[j];
                if (map.containsKey(target) && map.get(target) != i && map.get(target) != j) {
                    //这里的判断条件是为了防止重复的情况出现
                    //比如说[0,0,0,0]这种情况，如果不加这个判断条件，就会出现重复的情况

                    res.add(List.of(nums[i], nums[j], target));
                }
            }
        }
        return res;
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote == null || ransomNote.length() == 0) {
            return false;
        }
        //使用一个数组来存储每个字符出现的次数
        int[] arr = new int[26];

        for (int i = 0; i < magazine.length(); i++) {
            arr[magazine.charAt(i) - 'a']++;
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            if (arr[ransomNote.charAt(i) - 'a'] == 0) {
                return false;
            }
            arr[ransomNote.charAt(i) - 'a']--;
        }
        return true;
    }

    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            arr[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (arr[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }

    public String[] findRestaurant(String[] list1, String[] list2) {
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < list1.length; i++) {
            set.add(list1[i]);
        }
        List<String> res = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list2.length; i++) {
            if(set.contains(list2[i])){
                res.add(list2[i]);
            }
        }
        return res.toArray(new String[res.size()]);
}

//变成了求只出现一次的元素
    public int[] intersection(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<Integer> result = map.entrySet().stream()
                .filter(e -> ((Map.Entry<Integer, Integer>) e).getValue() > 1)
                .map(e -> ((Map.Entry<Integer, Integer>) e).getKey())
                .collect(Collectors.toList());

        // Convert List<Integer> to int[]
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
//    修改后的代码首先导入了java.util.stream.Collectors。然后，使用Stream API处理Map，并将结果存储到List中。最后，将结果从List<Integer>转换为int[]，并返回。

    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for(int num : nums2){
            if(map.containsKey(num)){
                //判断次数的大小
                if(map.get(num) > 0){
                    res.add(num);
                    map.put(num, map.get(num) - 1);
                }
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int item:
             nums) {
            map.put(item, map.getOrDefault(item,0)+1);
        }

        List<Integer> list = map.entrySet().stream()
                .sorted((o1, o2) -> o1.getValue() - o2.getValue())
                .map(e -> ((Map.Entry<Integer,Integer>) e).getKey())
                .limit(k)
                .collect(Collectors.toList());

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}





