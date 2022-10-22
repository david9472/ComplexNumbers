import java.lang.Math;
/**
* @class ComplexNumber
* @brief Represents a complex number and provides associated methods to manipulate those.
 * Precision is limited in the same way as it is for type double. Some methods may throw.
* */
class ComplexNumber
{
    /**
     * @class CartesianCoord
     * @brief Holds the representation of a complex number in the form of cartesian coordinates with
     * a real- and an imaginary-part.
     */
    static class CartesianCoord
    {
        /**
         * @brief real-part of the complex number
         */
        double re_;

        /**
         * @brief imaginary-part of the complex number
         */
        double im_;

        /**
         * @class CartesianCoord
         * @func CartesianCoord
         * @brief Constructor. Initializes all member to a default value.
         */
        public CartesianCoord()
        {
            re_ = 0.0;
            im_ = 0.0;
        }

        /**
         * @class CartesianCoord
         * @func CartesianCoord
         * @brief Constructor. Initializes member to the provided value.
         * @param re real-part of the complex number
         * @param im imaginary-part of the complex number
         */
        public CartesianCoord(double re, double im)
        {
            re_ = re;
            im_ = im;
        }

        /**
         * @class CartesianCoord
         * @func convertToPolar
         * @brief converts the currently held cartesian representation to polar form. Converting may lead to a loss in precision.
         * @return Converted complex number in polar coordinates.
         */
        private PolarCoord convertToPolar()
        {
            PolarCoord ret = new PolarCoord();
            ret.angle_ = Math.atan2(im_, re_);
            ret.len_ = Math.sqrt(Math.pow(im_, 2)+Math.pow(re_, 2));
            return ret;
        }
    }

    /**
     * @class PolarCoord
     * @brief Holds the representation of a complex number in the form of polar coordinates with
     * a magnitude and an angle.
     */
    static class PolarCoord
    {
        /**
         * @brief magnitude (length) of the complex number
         */
        double len_;

        /**
         * @brief angle of the complex number (in range [-pi, pi])
         */
        double angle_;

        /**
         * @class PolarCoord
         * @func PolarCoord
         * @brief Constructor. Initializes all member to a default value.
         */
        public PolarCoord()
        {
            len_ = 0.0;
            angle_ = 0.0;
        }

        /**
         * @class PolarCoord
         * @func PolarCoord
         * @brief Constructor. Initializes member to the provided value.
         * @param len length of the complex number
         * @param angle angle of the complex number (in range [-pi, pi])
         */
        public PolarCoord(double len, double angle)
        {
            len_ = len;
            angle_ = angle;
        }

        /**
         * @class PolarCoord
         * @func convertToCartesian
         * @brief converts the currently held polar representation to cartesian form. Converting may lead to a loss in precision.
         * @return Converted complex number in cartesian coordinates.
         */
        private CartesianCoord convertToCartesian()
        {
            CartesianCoord ret = new CartesianCoord();
            ret.im_ = len_ * Math.sin(angle_);
            ret.re_ = len_ * Math.cos(angle_);
            return ret;
        }
    }

    /**
     * @brief internal representation of the complex number.
     */
    private CartesianCoord num_;

    /**
     * @brief smallest difference between two numbers. This constant is used for comparing numbers on equality.
     */
    private final double epsilon = Math.pow(10, -5);

    /**
     * @class ComplexNumber
     * @func ComplexNumber
     * @brief Constructor. Initializes all member to a default value.
     */
    public ComplexNumber()
    {
        num_ = new CartesianCoord();
    }

    /**
     * @class ComplexNumber
     * @func setCartesianCoord
     * @brief replaces the currently held complex number with a new complex number.
     * @param carCoord complex number represented as a cartesian number which should be set as a new value, discarding the old one.
     * @return newly set complex number
     */
    public ComplexNumber setCartesianCoord(final CartesianCoord carCoord)
    {
        num_ = carCoord;
        return this;
    }

    /**
     * @class ComplexNumber
     * @func setCartesianCoord
     * @brief replaces the currently held complex number with a new complex number.
     * @param re real-part of the newly set complex number
     * @param im imaginary-part of the newly set complex number
     * @return newly set complex number
     */
    public ComplexNumber setCartesianCoord(final double re, final double im)
    {
        CartesianCoord carCoord = new CartesianCoord(re, im);
        setCartesianCoord(carCoord);
        return this;
    }

    /**
     * @class ComplexNumber
     * @func setPolarCoord
     * @brief replaces the currently held complex number with a new complex number.
     * @param pCoord complex number represented as a polar number which should be set as a new value, discarding the old one.
     * @return newly set complex number
     */
    public ComplexNumber setPolarCoord(final PolarCoord pCoord)
    {
        num_ = pCoord.convertToCartesian();
        return this;
    }

    /**
     * @class ComplexNumber
     * @func setPolarCoord
     * @brief replaces the currently held complex number with a new complex number.
     * @param len length of the new complex number
     * @param angle angle of the new complex number ([-pi, pi])
     * @return newly set complex number
     */
    public ComplexNumber setPolarCoord(final double len, final double angle)
    {
        PolarCoord pCoord = new PolarCoord(len, angle);
        setPolarCoord(pCoord);
        return this;
    }

    /**
     * @class ComplexNumber
     * @func getPolar
     * @brief converts the internally held complex number to polar coordinates and returns the result of the conversion.
     * @return converted complex number in polar form
     */
    public PolarCoord getPolar()
    {
        return num_.convertToPolar();
    }

    /**
     * @class ComplexNumber
     * @func getCartesian
     * @brief returns the internally held complex number. No conversion is performed.
     * @return converted complex number in cartesian form
     */
    public CartesianCoord getCartesian()
    {
        return num_;
    }


    /**
     * @class ComplexNumber
     * @func add
     * @brief Performs an addition between two complex numbers. The calling object as well as the provided object will remain their current value.
     * @param other complex number which should be added
     * @return result of the addition
     */
    public ComplexNumber add(final ComplexNumber other)
    {
        ComplexNumber ret = new ComplexNumber();

        ret.num_.re_ = num_.re_ + other.num_.re_;
        ret.num_.im_ = num_.im_ + other.num_.im_;
        return ret;
    }

    /**
     * @class ComplexNumber
     * @func subtract
     * @brief Performs a subtraction between two complex numbers. The calling object as well as the provided object will remain their current value.
     * @param other complex number which should be subtracted
     * @return result of the subtraction
     */
    public ComplexNumber subtract(final ComplexNumber other)
    {
        ComplexNumber ret = new ComplexNumber();

        ret.num_.re_ = num_.re_ - other.num_.re_;
        ret.num_.im_ = num_.im_ - other.num_.im_;
        return ret;
    }

    /**
     * @class ComplexNumber
     * @func multiply
     * @brief Performs a multiplication between two complex numbers. The calling object as well as the provided object will remain their current value.
     * @param other complex number which should be multiplied
     * @return result of the multiplication
     */
    public ComplexNumber multiply(final ComplexNumber other)
    {
        PolarCoord pThis = num_.convertToPolar();
        PolarCoord pOther = other.num_.convertToPolar();

        pThis.len_ = pThis.len_ * pOther.len_;
        pThis.angle_ = pThis.angle_ + pOther.angle_;

        ComplexNumber ret = new ComplexNumber();
        ret.setPolarCoord(pThis);
        return ret;
    }

    /**
     * @class ComplexNumber
     * @func divide
     * @brief Performs a division between two complex numbers. The calling object as well as the provided object will remain their current value.
     * @param other complex number which should be the divisor of the mathematical operation
     * @return result of the division
     * @throws ArithmeticException in case of division by zero
     */
    public ComplexNumber divide(final ComplexNumber other)
    {
        PolarCoord pThis = num_.convertToPolar();
        PolarCoord pOther = other.num_.convertToPolar();

        if (pOther.len_ == 0)
            throw new ArithmeticException("Division by zero is not allowed!");

        pThis.len_ = pThis.len_ / pOther.len_;
        pThis.angle_ = pThis.angle_ - pOther.angle_;

        ComplexNumber ret = new ComplexNumber();
        ret.setPolarCoord(pThis);
        return ret;
    }

    /**
     * @class ComplexNumber
     * @func squareRoot
     * @brief Calculates the square root of the complex number. The calling object will remain their current value.
     * @return square root of the complex number
     */
    public ComplexNumber squareRoot()
    {
        ComplexNumber ret = new ComplexNumber();
        if ((num_.re_ < 0) && Math.abs(num_.im_) < 0.00000001)
        {
            ret.num_.re_ = 0;
            ret.num_.im_ = Math.sqrt(Math.abs(num_.re_));
            return ret;
        }
        ret.num_.re_ = Math.sqrt((num_.re_ + Math.sqrt(Math.pow(num_.re_, 2) + Math.pow(num_.im_, 2)))/(2));
        ret.num_.im_ = Math.signum(num_.im_)*Math.sqrt((-num_.re_ + Math.sqrt(Math.pow(num_.re_, 2) + Math.pow(num_.im_, 2)))/(2));
        return ret;
    }

    /**
     * @class ComplexNumber
     * @func power
     * @brief calculates the power of the complex number
     * @param exponent exponent to which power the complex number should be raised.
     * @return complex number to the power of exponent
     * @throws ArithmeticException in case exponent has a value < 1
     */
    public ComplexNumber power(final int exponent)
    {
        if (exponent <= 0)
            throw new ArithmeticException("Power is only defined for integer greater or equal 1!");

        PolarCoord pCoord = new PolarCoord();
        PolarCoord This = num_.convertToPolar();
        pCoord.len_ = Math.pow(This.len_, exponent);
        pCoord.angle_ = (This.angle_ * exponent)%(Math.PI*2);

        ComplexNumber ret = new ComplexNumber();
        ret.setPolarCoord(pCoord);
        return ret;
    }

    /**
     * @class ComplexNumber
     * @func equal
     * @brief compares two complex numbers for equality
     * @param other Complex number which should be checked on equality with current number
     * @return true - both complex numbers have a smaller difference in both, real- and imaginary-part than defined by the constant epsilon;
     *         false - else
     */
    public boolean equal(final ComplexNumber other)
    {
        return ((Math.abs(num_.re_ - other.num_.re_) < epsilon) && (Math.abs(num_.im_ - other.num_.im_) < epsilon));
    }
}