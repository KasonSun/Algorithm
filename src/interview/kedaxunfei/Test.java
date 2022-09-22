package interview.kedaxunfei;

public class Test {
    public static void main(String[] args) {
        int[] nums = new int[]{360, 360, 360};
        int temp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            temp ^= nums[i];
        }
        System.out.println(temp);
    }
}
