public class ArrayHandler {

    public int[] intsAfterLastFour(int[] originalArray) {
        for (int i = originalArray.length - 1; i >= 0; i--) {
            if (originalArray[i] == 4) {
                return getSliceArray(i + 1, originalArray);
            }
        }
        throw new RuntimeException();
    }

    private int[] getSliceArray(int fromIndex, int[] originalArray) {
        int[] resultArray = new int[originalArray.length - fromIndex];

        for (int i = fromIndex, j = 0; i < originalArray.length; i++, j++) {
            resultArray[j] = originalArray[i];
        }
        return resultArray;
    }

    public boolean findFourOrOne(int[] array) {
        boolean one = false;
        boolean four = false;

        for (int num : array) {
            if (num == 1) {
                one = true;
            } else if (num == 4) {
                four = true;
            } else {
                return false;
            }
        }
        return one && four;
    }
}