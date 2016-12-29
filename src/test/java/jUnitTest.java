import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

/**
 * Created by AlexanderSavenok on 12/29/2016.
 */
@RunWith(Parameterized.class)
public class jUnitTest {


    int firstValue;
    int secondValue;
    int sum;

    public jUnitTest(int firstValue, int secondValue, int sum) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.sum = sum;
    }

    @Test
    public void verifySumTest() {
        int result = firstValue + secondValue;
        Assert.assertEquals(sum, result);
    }

    @Parameterized.Parameters
    public static List<Object[]> isEmptyData() {
        return Arrays.asList(new Object[][]{
                {3, 7, 10},
                {5, 6, 11},
                {12, 15, 27},
                {33, 12, 45},
        });
    }

}
