package io.zipcoder.herion;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class MainTest {

    ConvertMoney converter = new ConvertMoney();
    StringBuilder sb = new StringBuilder();

    @Test
    public void moneyTestOne() {

        String expect = "TwoHundredAndFiftyThousandOneHundredAndTwentyFiveDollarsAndFortyCents";

        String actual = converter.moneyNumberToWords("250125.40", sb);

        Assert.assertEquals(expect, actual);

    }

    @Test
    public void moneyTestTwo() {


        String expect = "ThreeHundredAndOneMillionTwoHundredAndFiveThousandOneHundredAndElevenDollarsAndOneCent";

        String actual = converter.moneyNumberToWords("301205111.01", sb);

        Assert.assertEquals(expect, actual);


    }

    @Test
    public void moneyTestThree() {


        String expect = "FiveHundredAndFourteenBillionThreeHundredAndOneMillionTwoHundredAndFiveThousandOneHundredAndElevenDollarsAndTenCents";

        String actual = converter.moneyNumberToWords("514301205111.10", sb);

        Assert.assertEquals(expect, actual);


    }
}
