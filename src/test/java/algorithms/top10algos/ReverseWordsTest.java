package algorithms.top10algos;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ReverseWordsTest {

    private static final String SENTENCE = "the sky is blue";
    private static final String REVERSED_SENTENCE = "blue is sky the";
    private ReverseWords rw = new ReverseWords();

    @Test
    public void testReverseWords() {
        String reversedString = rw.reverseWords(SENTENCE);
        assertThat(reversedString, is(REVERSED_SENTENCE));
    }

    @Test
    public void testReverseStringAsCharArray() {
        char[] reversedString = rw.reverseStringAsCharArray(SENTENCE.toCharArray());
        assertThat(reversedString, is(REVERSED_SENTENCE.toCharArray()));
    }

}
