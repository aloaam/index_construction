package indexconstruction.pricenormalization;

import java.util.function.BiFunction;


//TODO: ask I see my boss sometimes does this.
public class DoublePercentageChange implements BiFunction<Double, Double, Double> {

    @Override
    public Double apply(Double lastPrice, Double previousPrice) {

        return lastPrice / previousPrice - 1;
    }


}
