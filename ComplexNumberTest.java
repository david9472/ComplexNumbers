import java.beans.PropertyEditorSupport;

import static org.junit.jupiter.api.Assertions.*;

class ComplexNumberTest {
    final double PRECISION = Math.pow(10, -6);
    private boolean equal(final double a, final double b)
    {
        return (Math.abs(a-b) < PRECISION);
    }

    @org.junit.jupiter.api.Test
    void testSetCartesianCoordNums()
    {
        final double re = 32.2;
        final double im = 89;
        ComplexNumber num = new ComplexNumber();
        num.setCartesianCoord(re, im);

        ComplexNumber.CartesianCoord cCoord = num.getCartesian();
        assertTrue(equal(cCoord.re_ , re));
        assertTrue(equal(cCoord.im_ , im));
    }

    @org.junit.jupiter.api.Test
    void testSetCartesianCoordStruct()
    {
        ComplexNumber.CartesianCoord cCoordExpected = new ComplexNumber.CartesianCoord();
        cCoordExpected.im_ = 545.2;
        cCoordExpected.re_ = 82;

        ComplexNumber num = new ComplexNumber();
        num.setCartesianCoord(cCoordExpected);

        ComplexNumber.CartesianCoord cCoordActual = num.getCartesian();
        assertTrue(equal(cCoordExpected.im_ , cCoordActual.im_));

        assertTrue(equal(cCoordExpected.im_, cCoordActual.im_));
        assertTrue(equal(cCoordExpected.re_, cCoordActual.re_));
    }

    @org.junit.jupiter.api.Test
    void testSetPolarCoordNums()
    {
        final double len = 32.2;
        final double angle = Math.PI/2;
        ComplexNumber num = new ComplexNumber();
        num.setPolarCoord(len, angle);

        ComplexNumber.PolarCoord pCoord = num.getPolar();
        assertTrue(equal(pCoord.len_, len));
        assertTrue(equal(pCoord.angle_, angle));
    }

    @org.junit.jupiter.api.Test
    void testSetPolarCoordStruct()
    {
        ComplexNumber.PolarCoord pCoordExpected = new ComplexNumber.PolarCoord();
        pCoordExpected.len_ = 545.2;
        pCoordExpected.angle_ = Math.PI/4;

        ComplexNumber num = new ComplexNumber();
        num.setPolarCoord(pCoordExpected);

        ComplexNumber.PolarCoord pCoordActual = num.getPolar();
        assertTrue(equal(pCoordActual.len_, pCoordExpected.len_));
        assertTrue(equal(pCoordActual.angle_, pCoordExpected.angle_));

    }

    @org.junit.jupiter.api.Test
    void getPolarFirstQuadrant()
    {
        //first quadrant
        ComplexNumber num = new ComplexNumber();
        num.setCartesianCoord(1,1);
        final double len_expected = Math.sqrt(2);
        final double angle_expected = Math.toRadians(45);

        ComplexNumber.PolarCoord pCoord = num.getPolar();
        assertTrue(equal(pCoord.angle_, angle_expected));
        assertTrue(equal(pCoord.len_, len_expected));
    }

    @org.junit.jupiter.api.Test
    void getPolarSecondQuadrant()
    {
        //second quadrant
        ComplexNumber num = new ComplexNumber();
        num.setCartesianCoord(-1,1);
        final double len_expected2 = Math.sqrt(2);
        final double angle_expected2 = Math.toRadians(135);

        ComplexNumber.PolarCoord pCoord = num.getPolar();
        assertTrue(equal(pCoord.angle_, angle_expected2));
        assertTrue(equal(pCoord.len_, len_expected2));
    }

    @org.junit.jupiter.api.Test
    void getPolarThirdQuadrant()
    {
        //second quadrant
        ComplexNumber num = new ComplexNumber();
        num.setCartesianCoord(-1,-1);
        final double len_expected = Math.sqrt(2);
        final double angle_expected = Math.toRadians(-135);

        ComplexNumber.PolarCoord pCoord = num.getPolar();
        assertTrue(equal(pCoord.angle_, angle_expected));
        assertTrue(equal(pCoord.len_, len_expected));
    }

    @org.junit.jupiter.api.Test
    void getPolarFourthQuadrant()
    {
        //second quadrant
        ComplexNumber num = new ComplexNumber();
        num.setCartesianCoord(1,-1);
        final double len_expected = Math.sqrt(2);
        final double angle_expected = Math.toRadians(-45);

        ComplexNumber.PolarCoord pCoord = num.getPolar();
        assertTrue(equal(pCoord.angle_, angle_expected));
        assertTrue(equal(pCoord.len_, len_expected));
    }


    @org.junit.jupiter.api.Test
    void getCartesianFirstQuadrant()
    {
      ComplexNumber num = new ComplexNumber();
      num.setPolarCoord(Math.sqrt(2), Math.toRadians(45));
      final double re_expected = 1;
      final double im_expected = 1;
      ComplexNumber.CartesianCoord cCoord = num.getCartesian();
      assertTrue(equal(cCoord.re_, re_expected));
      assertTrue(equal(cCoord.im_, im_expected));
    }

    @org.junit.jupiter.api.Test
    void getCartesianSecondQuadrant()
    {
        ComplexNumber num = new ComplexNumber();
        num.setPolarCoord(Math.sqrt(2), Math.toRadians(135));
        final double re_expected = -1;
        final double im_expected = 1;
        ComplexNumber.CartesianCoord cCoord = num.getCartesian();
        assertTrue(equal(cCoord.re_, re_expected));
        assertTrue(equal(cCoord.im_, im_expected));
    }

    @org.junit.jupiter.api.Test
    void getCartesianThirdQuadrant()
    {
        ComplexNumber num = new ComplexNumber();
        num.setPolarCoord(Math.sqrt(2), Math.toRadians(225));
        final double re_expected = -1;
        final double im_expected = -1;
        ComplexNumber.CartesianCoord cCoord = num.getCartesian();
        assertTrue(equal(cCoord.re_, re_expected));
        assertTrue(equal(cCoord.im_, im_expected));
    }

    @org.junit.jupiter.api.Test
    void getCartesianFourthQuadrant()
    {
        ComplexNumber num = new ComplexNumber();
        num.setPolarCoord(Math.sqrt(2), Math.toRadians(315));
        final double re_expected = 1;
        final double im_expected = -1;
        ComplexNumber.CartesianCoord cCoord = num.getCartesian();
        assertTrue(equal(cCoord.re_, re_expected));
        assertTrue(equal(cCoord.im_, im_expected));
    }

    @org.junit.jupiter.api.Test
    void add()
    {
        final double re_1 = 32;
        final double im_1 = 897;
        final double re_2 = 232;
        final double im_2 = 7238;
        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber num2 = new ComplexNumber();
        num2.setCartesianCoord(re_2, im_2);

        ComplexNumber ret = num1.add(num2);

        assertTrue(equal(ret.getCartesian().re_, re_1+re_2));
        assertTrue(equal(ret.getCartesian().im_, im_1+im_2));

        assertTrue(equal(num1.getCartesian().re_, re_1));
        assertTrue(equal(num1.getCartesian().im_, im_1));
        assertTrue(equal(num2.getCartesian().re_, re_2));
        assertTrue(equal(num2.getCartesian().im_, im_2));
    }

    @org.junit.jupiter.api.Test
    void addImaginaryOnly()
    {
        final double re_1 = 32;
        final double im_1 = 897;
        final double re_2 = 0;
        final double im_2 = 7238;
        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber num2 = new ComplexNumber();
        num2.setCartesianCoord(re_2, im_2);

        ComplexNumber ret = num1.add(num2);

        assertTrue(equal(ret.getCartesian().re_, re_1+re_2));
        assertTrue(equal(ret.getCartesian().im_, im_1+im_2));

        assertTrue(equal(num1.getCartesian().re_, re_1));
        assertTrue(equal(num1.getCartesian().im_, im_1));
        assertTrue(equal(num2.getCartesian().re_, re_2));
        assertTrue(equal(num2.getCartesian().im_, im_2));
    }

    @org.junit.jupiter.api.Test
    void subtractAllPositive()
    {
        final double re_1 = 872;
        final double im_1 = 123;
        final double re_2 = 21;
        final double im_2 = 90;
        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber num2 = new ComplexNumber();
        num2.setCartesianCoord(re_2, im_2);

        ComplexNumber ret = num1.subtract(num2);

        assertTrue(equal(ret.getCartesian().re_, re_1-re_2));
        assertTrue(equal(ret.getCartesian().im_, im_1-im_2));

        assertTrue(equal(num1.getCartesian().re_, re_1));
        assertTrue(equal(num1.getCartesian().im_, im_1));
        assertTrue(equal(num2.getCartesian().re_, re_2));
        assertTrue(equal(num2.getCartesian().im_, im_2));
    }

    @org.junit.jupiter.api.Test
    void subtractAllNegative()
    {
        final double re_1 = -31;
        final double im_1 = -65;
        final double re_2 = -12;
        final double im_2 = -74;
        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber num2 = new ComplexNumber();
        num2.setCartesianCoord(re_2, im_2);

        ComplexNumber ret = num1.subtract(num2);

        assertTrue(equal(ret.getCartesian().re_, re_1-re_2));
        assertTrue(equal(ret.getCartesian().im_, im_1-im_2));

        assertTrue(equal(num1.getCartesian().re_, re_1));
        assertTrue(equal(num1.getCartesian().im_, im_1));
        assertTrue(equal(num2.getCartesian().re_, re_2));
        assertTrue(equal(num2.getCartesian().im_, im_2));
    }

    @org.junit.jupiter.api.Test
    void subtractMixedSign()
    {
        final double re_1 = -31;
        final double im_1 = 65;
        final double re_2 = 12;
        final double im_2 = -79;
        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber num2 = new ComplexNumber();
        num2.setCartesianCoord(re_2, im_2);

        ComplexNumber ret = num1.subtract(num2);

        assertTrue(equal(ret.getCartesian().re_, re_1-re_2));
        assertTrue(equal(ret.getCartesian().im_, im_1-im_2));

        assertTrue(equal(num1.getCartesian().re_, re_1));
        assertTrue(equal(num1.getCartesian().im_, im_1));
        assertTrue(equal(num2.getCartesian().re_, re_2));
        assertTrue(equal(num2.getCartesian().im_, im_2));
    }

    @org.junit.jupiter.api.Test
    void multiplyMixedSign()
    {
        final double re_1 = 4;
        final double im_1 = -9;
        final double re_2 = -32;
        final double im_2 = 2;
        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber num2 = new ComplexNumber();
        num2.setCartesianCoord(re_2, im_2);

        ComplexNumber ret = num1.multiply(num2);

        assertTrue(equal(ret.getCartesian().re_, -110));
        assertTrue(equal(ret.getCartesian().im_, 296));

        assertTrue(equal(num1.getCartesian().re_, re_1));
        assertTrue(equal(num1.getCartesian().im_, im_1));
        assertTrue(equal(num2.getCartesian().re_, re_2));
        assertTrue(equal(num2.getCartesian().im_, im_2));
    }

    @org.junit.jupiter.api.Test
    void multiplyAllPositve()
    {
        final double re_1 = 10;
        final double im_1 = 12;
        final double re_2 = 3;
        final double im_2 = 90;
        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber num2 = new ComplexNumber();
        num2.setCartesianCoord(re_2, im_2);

        ComplexNumber ret = num1.multiply(num2);

        assertTrue(equal(ret.getCartesian().re_, -1050));
        assertTrue(equal(ret.getCartesian().im_, 936));

        assertTrue(equal(num1.getCartesian().re_, re_1));
        assertTrue(equal(num1.getCartesian().im_, im_1));
        assertTrue(equal(num2.getCartesian().re_, re_2));
        assertTrue(equal(num2.getCartesian().im_, im_2));
    }

    @org.junit.jupiter.api.Test
    void multiplyAllNegative()
    {
        final double re_1 = -15;
        final double im_1 = -23;
        final double re_2 = -9;
        final double im_2 = -13;
        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber num2 = new ComplexNumber();
        num2.setCartesianCoord(re_2, im_2);

        ComplexNumber ret = num1.multiply(num2);

        assertTrue(equal(ret.getCartesian().re_, -164));
        assertTrue(equal(ret.getCartesian().im_, 402));

        assertTrue(equal(num1.getCartesian().re_, re_1));
        assertTrue(equal(num1.getCartesian().im_, im_1));
        assertTrue(equal(num2.getCartesian().re_, re_2));
        assertTrue(equal(num2.getCartesian().im_, im_2));
    }

    @org.junit.jupiter.api.Test
    void multiplyImaginaryOnly()
    {
        final double re_1 = -15;
        final double im_1 = -23;
        final double re_2 = 0;
        final double im_2 = 1;
        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber num2 = new ComplexNumber();
        num2.setCartesianCoord(re_2, im_2);

        ComplexNumber ret = num1.multiply(num2);

        assertTrue(equal(ret.getCartesian().re_, 23));
        assertTrue(equal(ret.getCartesian().im_, -15));

        assertTrue(equal(num1.getCartesian().re_, re_1));
        assertTrue(equal(num1.getCartesian().im_, im_1));
        assertTrue(equal(num2.getCartesian().re_, re_2));
        assertTrue(equal(num2.getCartesian().im_, im_2));
    }

    @org.junit.jupiter.api.Test
    void divideAllPositive()
    {
        final double re_1 =23;
        final double im_1 = 9;
        final double re_2 = 3;
        final double im_2 = 7;
        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber num2 = new ComplexNumber();
        num2.setCartesianCoord(re_2, im_2);

        ComplexNumber ret = num1.divide(num2);

        assertTrue(equal(ret.getCartesian().re_, (66.0/29.0)));
        assertTrue(equal(ret.getCartesian().im_, (-67.0/29.0)));

        assertTrue(equal(num1.getCartesian().re_, re_1));
        assertTrue(equal(num1.getCartesian().im_, im_1));
        assertTrue(equal(num2.getCartesian().re_, re_2));
        assertTrue(equal(num2.getCartesian().im_, im_2));
    }

    @org.junit.jupiter.api.Test
    void divideAllNegative()
    {
        final double re_1 =-23;
        final double im_1 = -65;
        final double re_2 = -9;
        final double im_2 = -4;
        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber num2 = new ComplexNumber();
        num2.setCartesianCoord(re_2, im_2);

        ComplexNumber ret = num1.divide(num2);

        assertTrue(equal(ret.getCartesian().re_, (467.0/97.0)));
        assertTrue(equal(ret.getCartesian().im_, (493.0/97.0)));

        assertTrue(equal(num1.getCartesian().re_, re_1));
        assertTrue(equal(num1.getCartesian().im_, im_1));
        assertTrue(equal(num2.getCartesian().re_, re_2));
        assertTrue(equal(num2.getCartesian().im_, im_2));
    }

    @org.junit.jupiter.api.Test
    void divideMixedSign()
    {
        final double re_1 =-23;
        final double im_1 = 32;
        final double re_2 = 13;
        final double im_2 = -4;
        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber num2 = new ComplexNumber();
        num2.setCartesianCoord(re_2, im_2);

        ComplexNumber ret = num1.divide(num2);

        assertTrue(equal(ret.getCartesian().re_, (-427.0/185.0)));
        assertTrue(equal(ret.getCartesian().im_, (324.0/185.0)));

        assertTrue(equal(num1.getCartesian().re_, re_1));
        assertTrue(equal(num1.getCartesian().im_, im_1));
        assertTrue(equal(num2.getCartesian().re_, re_2));
        assertTrue(equal(num2.getCartesian().im_, im_2));
    }

    @org.junit.jupiter.api.Test
    void divideRealSetToZero()
    {
        final double re_1 =-23;
        final double im_1 = 32;
        final double re_2 = 0;
        final double im_2 = 7;
        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber num2 = new ComplexNumber();
        num2.setCartesianCoord(re_2, im_2);

        ComplexNumber ret = num1.divide(num2);

        assertTrue(ret.getCartesian().re_ > 4.571427);
        assertTrue(ret.getCartesian().re_ < 4.571429);

        assertTrue(ret.getCartesian().im_ > 3.285713);
        assertTrue(ret.getCartesian().im_ < 3.285715);
    }

    @org.junit.jupiter.api.Test
    void divide1()
    {
        final double re_1 = 2;
        final double im_1 = 3;
        final double re_2 = 3;
        final double im_2 = -4;
        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber num2 = new ComplexNumber();
        num2.setCartesianCoord(re_2, im_2);

        ComplexNumber ret = num1.divide(num2);

        assertTrue(equal(ret.getCartesian().re_, -6.0/25.0));
        assertTrue(equal(ret.getCartesian().im_, 17.0/25.0));
    }

    @org.junit.jupiter.api.Test
    void divide2()
    {
        final double re_1 = 1;
        final double im_1 = -2;
        final double re_2 = 0;
        final double im_2 = 1;
        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber num2 = new ComplexNumber();
        num2.setCartesianCoord(re_2, im_2);

        ComplexNumber ret = num1.divide(num2);

        assertTrue(equal(ret.getCartesian().re_, -2));
        assertTrue(equal(ret.getCartesian().im_, -1));
    }

    @org.junit.jupiter.api.Test
    void squareRootAllPositve()
    {
        final double re_1 =4;
        final double im_1 = 6;
        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber ret = num1.squareRoot();

        assertTrue(ret.getCartesian().re_ > 2.367603);
        assertTrue(ret.getCartesian().re_ < 2.367605);

        assertTrue(ret.getCartesian().im_ > 1.267102);
        assertTrue(ret.getCartesian().im_ < 1.267104);
    }

    @org.junit.jupiter.api.Test
    void squareRootAllNegative()
    {
        final double re_1 =-23;
        final double im_1 = -17;
        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber ret = num1.squareRoot();

        assertTrue(ret.getCartesian().re_ > 1.673423);
        assertTrue(ret.getCartesian().re_ < 1.673425);

        assertTrue(ret.getCartesian().im_ > -5.079405);
        assertTrue(ret.getCartesian().im_ < -5.079403);
    }

    @org.junit.jupiter.api.Test
    void squareRootMixedSign()
    {
        final double re_1 =-23;
        final double im_1 = 2;
        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber ret = num1.squareRoot();

        assertTrue(ret.getCartesian().re_ > 0.2083178);
        assertTrue(ret.getCartesian().re_ < 0.208318);

        assertTrue(ret.getCartesian().im_ > 4.800352);
        assertTrue(ret.getCartesian().im_ < 4.800354);
    }

    @org.junit.jupiter.api.Test
    void squareRootRealOnlyPositve()
    {
        final double re_1 =16;
        final double im_1 = 0;
        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber ret = num1.squareRoot();

        assertTrue(equal(4, ret.getCartesian().re_));
        assertTrue(equal(0, ret.getCartesian().im_));
    }

    @org.junit.jupiter.api.Test
    void squareRootRealOnlyNegative()
    {
        final double re_1 =-16;
        final double im_1 = 0;
        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber ret = num1.squareRoot();

        assertTrue(equal(0, ret.getCartesian().re_));
        assertTrue(equal(4, ret.getCartesian().im_));
    }

    @org.junit.jupiter.api.Test
    void squareRootImaginaryOnlyPositve()
    {
        final double re_1 = 0;
        final double im_1 = 49;
        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber ret = num1.squareRoot();

        assertTrue(ret.getCartesian().re_ > 4.949746);
        assertTrue(ret.getCartesian().re_ < 4.949748);

        assertTrue(ret.getCartesian().im_ > 4.949746);
        assertTrue(ret.getCartesian().im_ < 4.949748);
    }

    @org.junit.jupiter.api.Test
    void squareRootImaginaryOnlyNegative()
    {
        final double re_1 = 0;
        final double im_1 = -81;
        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber ret = num1.squareRoot();

        assertTrue(ret.getCartesian().re_ > 6.363960);
        assertTrue(ret.getCartesian().re_ < 6.363962);

        assertTrue(ret.getCartesian().im_ > -6.363962);
        assertTrue(ret.getCartesian().im_ < -6.363960);
    }

    @org.junit.jupiter.api.Test
    void PowerException0()
    {
        final double re_1 = 0;
        final double im_1 = -81;
        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        assertThrows(ArithmeticException.class, () -> num1.power(0));
    }

    @org.junit.jupiter.api.Test
    void PowerException1()
    {
        final double re_1 = 0;
        final double im_1 = -81;
        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        assertThrows(ArithmeticException.class, () -> num1.power(-1));
    }

    @org.junit.jupiter.api.Test
    void PowerOne()
    {
        final double re_1 = 0;
        final double im_1 = -81;
        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber ret = num1.power(1);
        assertTrue(equal(re_1, ret.getCartesian().re_));
        assertTrue(equal(im_1, ret.getCartesian().im_));
    }

    @org.junit.jupiter.api.Test
    void PowerTwo()
    {
        final double re_1 = 12;
        final double im_1 = 7;
        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber ret = num1.power(2);
        assertTrue(equal(95, ret.getCartesian().re_));
        assertTrue(equal(168, ret.getCartesian().im_));
    }

    @org.junit.jupiter.api.Test
    void PowerSeven()
    {
        final double re_1 = -3;
        final double im_1 = 23;
        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber ret = num1.power(7);
        assertTrue(equal(2.847001224 * Math.pow(10, 9), ret.getCartesian().re_));
        assertTrue(equal(-2.222732696 * Math.pow(10, 9), ret.getCartesian().im_));
    }

    @org.junit.jupiter.api.Test
    void TestEqual0()
    {
        final double re_1 = 12;
        final double im_1 = 90390;
        final double re_2 = 8934;
        final double im_2 = 1;

        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber num2 = new ComplexNumber();
        num2.setCartesianCoord(re_2, im_2);

        assertFalse(num1.equal(num2));
    }

    @org.junit.jupiter.api.Test
    void TestEqual1()
    {
        final double re_1 = 12;
        final double im_1 = 1;
        final double re_2 = 12.00001;
        final double im_2 = 1;

        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber num2 = new ComplexNumber();
        num2.setCartesianCoord(re_2, im_2);

        assertTrue(num1.equal(num2));
    }

    @org.junit.jupiter.api.Test
    void TestEqual2()
    {
        final double re_1 = 12;
        final double im_1 = 1;
        final double re_2 = 12.0000001;
        final double im_2 = 1;

        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber num2 = new ComplexNumber();
        num2.setCartesianCoord(re_2, im_2);

        assertTrue(num1.equal(num2));
    }

    @org.junit.jupiter.api.Test
    void TestEqual2_1()
    {
        final double re_1 = 12;
        final double im_1 = 1;
        final double re_2 = 12;
        final double im_2 = 1.0000001;

        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber num2 = new ComplexNumber();
        num2.setCartesianCoord(re_2, im_2);

        assertTrue(num1.equal(num2));
    }

    @org.junit.jupiter.api.Test
    void TestEqual3()
    {
        final double re_1 = 18932;
        final double im_1 = 1;
        final double re_2 = 18932;
        final double im_2 = 1;

        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber num2 = new ComplexNumber();
        num2.setCartesianCoord(re_2, im_2);

        assertTrue(num1.equal(num2));
    }

    @org.junit.jupiter.api.Test
    void TestEqual4()
    {
        final double re_1 = -423;
        final double im_1 = 1;
        final double re_2 = 423;
        final double im_2 = 1;

        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber num2 = new ComplexNumber();
        num2.setCartesianCoord(re_2, im_2);

        assertFalse(num1.equal(num2));
    }

    @org.junit.jupiter.api.Test
    void TestEqual5()
    {
        final double re_1 = 90;
        final double im_1 = -1;
        final double re_2 = 90;
        final double im_2 = 1;

        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber num2 = new ComplexNumber();
        num2.setCartesianCoord(re_2, im_2);

        assertFalse(num1.equal(num2));
    }

    @org.junit.jupiter.api.Test
    void TestEqual6()
    {
        final double re_1 = -90;
        final double im_1 = -1;
        final double re_2 = -90;
        final double im_2 = -1;

        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber num2 = new ComplexNumber();
        num2.setCartesianCoord(re_2, im_2);

        assertTrue(num1.equal(num2));
    }

    @org.junit.jupiter.api.Test
    void MethodChainingAddSubtract()
    {
        final double re_1 = 897;
        final double im_1 = 123;
        final double re_2 = 672;
        final double im_2 = 21;

        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber num2 = new ComplexNumber();
        num2.setCartesianCoord(re_2, im_2);

        ComplexNumber ret = num1.add(num2).subtract(num2);
        assertTrue(ret.equal(num1));
    }

    @org.junit.jupiter.api.Test
    void MethodChainingSubtractAdd()
    {
        final double re_1 = 83;
        final double im_1 = 453;
        final double re_2 = 12;
        final double im_2 = 54;

        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber num2 = new ComplexNumber();
        num2.setCartesianCoord(re_2, im_2);

        ComplexNumber ret = num1.subtract(num2).add(num2);
        assertTrue(ret.equal(num1));
    }

    @org.junit.jupiter.api.Test
    void MethodChainingMultiplyDivide()
    {
        final double re_1 = 12;
        final double im_1 = 32;
        final double re_2 = 3;
        final double im_2 = 8;

        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber num2 = new ComplexNumber();
        num2.setCartesianCoord(re_2, im_2);

        ComplexNumber ret = num1.multiply(num2).divide(num2);
        assertTrue(ret.equal(num1));
    }

    @org.junit.jupiter.api.Test
    void MethodChainingDivideMultiply()
    {
        final double re_1 = 98;
        final double im_1 = 21;
        final double re_2 = 5;
        final double im_2 = 72;

        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber num2 = new ComplexNumber();
        num2.setCartesianCoord(re_2, im_2);

        ComplexNumber ret = num1.divide(num2).multiply(num2);
        assertTrue(ret.equal(num1));
    }

    @org.junit.jupiter.api.Test
    void MethodChainingPowerSqrt()
    {
        final double re_1 = 82;
        final double im_1 = 983;
        final double re_2 = 9;
        final double im_2 = 92;

        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber num2 = new ComplexNumber();
        num2.setCartesianCoord(re_2, im_2);

        ComplexNumber ret = num1.power(2).squareRoot();
        assertTrue(ret.equal(num1));
    }

    @org.junit.jupiter.api.Test
    void MethodChainingSqrtPower()
    {
        final double re_1 = 873;
        final double im_1 = 782;
        final double re_2 = 983;
        final double im_2 = 672;

        ComplexNumber num1 = new ComplexNumber();
        num1.setCartesianCoord(re_1, im_1);

        ComplexNumber num2 = new ComplexNumber();
        num2.setCartesianCoord(re_2, im_2);

        ComplexNumber ret = num1.squareRoot().power(2);
        assertTrue(ret.equal(num1));
    }

}