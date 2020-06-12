package algorithms.top10algos;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ReversePolishNotationTest {

    private ReversePolishNotation rpn = new ReversePolishNotation();

    @Test
    public void testEvaluateReversePolishNotation() {
        String[] expression = new String[] {"4", "13", "5", "/", "+"};
        int result = rpn.evaluateReversePolishNotation(expression);
        assertThat(result, is(6));
    }

}
