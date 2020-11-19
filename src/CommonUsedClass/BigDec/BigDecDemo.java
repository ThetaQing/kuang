package CommonUsedClass.BigDec;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class BigDecDemo {
    public static void main(String[] args) {
        BigDecimal b1 = new BigDecimal("1.4");
        BigDecimal b2 = new BigDecimal("0.5");
        BigDecimal b3 = new BigDecimal("0.9");

        BigDecimal r1 = b1.add(b2);
        System.out.println(r1);

        r1 = b1.subtract(b2);
        System.out.println(r1);

        r1 = b1.multiply(b2);
        System.out.println(r1);

        r1 = b1.divide(b2,2,BigDecimal.ROUND_HALF_UP);
        System.out.println(r1);




    }
}
