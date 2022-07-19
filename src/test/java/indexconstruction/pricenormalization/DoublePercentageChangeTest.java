package indexconstruction.pricenormalization;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//TODO: check this too.
class DoublePercentageChangeTest {

    final DoublePercentageChange unit = new DoublePercentageChange();

    @Test
    void apply() {

        var priceChange = unit.apply(175.0, 100.0);
        Assertions.assertEquals(priceChange, 0.75);

    }
}