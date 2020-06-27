package algorithms.top10algos;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class KMPSubstringSearchTest {

    private KMPSubstringSearch kmp = new KMPSubstringSearch();

    @Test
    public void testGetPatternArray() {
        int[] arr = kmp.getPatternArray("aabaabaaa");

        assertThat(arr, is(new int[]{0,1,0,1,2,3,4,5,2}));
    }

    @Test
    public void testFindSubstring() {
        int index = kmp.findSubstring("abxabcabcaby", "abcaby");

        assertThat(index, is(6));
    }

}
