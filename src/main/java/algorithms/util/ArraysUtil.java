package algorithms.util;

import java.util.Arrays;

public class ArraysUtil {

    public static int[] removeZeros(int[] array) {
        int zeroIndex = -1;
        do {
            zeroIndex++;
        } while (array[zeroIndex] != 0);

        return zeroIndex > 0 ? Arrays.copyOfRange(array,0,zeroIndex) : array;
    }

    @SafeVarargs
    public static <T> T[] newArray(int length, T... array) {
        return Arrays.copyOf(array, length);
    }

}
