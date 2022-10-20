import java.lang.Math;

class ComplexNumber
{
    static class CartesianCoord
    {
        double re_;
        double im_;
        public CartesianCoord()
        {
            re_ = 0.0;
            im_ = 0.0;
        }

        public CartesianCoord(double re, double im)
        {
            re_ = re;
            im_ = im;
        }    

        private PolarCoord convertToPolar()
        {
            PolarCoord ret = new PolarCoord();
            ret.angle_ = Math.atan2(im_, re_);
            ret.len_ = Math.sqrt(Math.pow(im_, 2)+Math.pow(re_, 2));
            return ret;
        }
    }

    static class PolarCoord
    {
        double len_;
        double angle_;
        public PolarCoord()
        {
            len_ = 0.0;
            angle_ = 0.0;
        }

        public PolarCoord(double len, double angle)
        {
            len_ = len;
            angle_ = angle;
        }

        private CartesianCoord convertToCartesian()
        {
            CartesianCoord ret = new CartesianCoord();
            ret.im_ = len_ * Math.sin(angle_);
            ret.re_ = len_ * Math.cos(angle_);
            return ret;
        }
    }

    private CartesianCoord num_;

    public ComplexNumber()
    {
        num_ = new CartesianCoord();
    }

    public ComplexNumber setCartesianCoord(final CartesianCoord carCoord)
    {
        num_ = carCoord;
        return this;
    }

    public ComplexNumber setCartesianCoord(final double re, final double im)
    {
        CartesianCoord carCoord = new CartesianCoord(re, im);
        setCartesianCoord(carCoord);
        return this;
    }

    public ComplexNumber setPolarCoord(final PolarCoord pCoord)
    {
        num_ = pCoord.convertToCartesian();
        return this;
    }

    public ComplexNumber setPolarCoord(final double len, final double angle)
    {
        PolarCoord pCoord = new PolarCoord(len, angle);
        setPolarCoord(pCoord);
        return this;
    }

    public PolarCoord getPolar()
    {
        return num_.convertToPolar();
    }

    public CartesianCoord getCartesian()
    {
        return num_;
    }


    public ComplexNumber add(final ComplexNumber other)
    {
        ComplexNumber ret = new ComplexNumber();

        ret.num_.re_ = num_.re_ + other.num_.re_;
        ret.num_.im_ = num_.im_ + other.num_.im_;
        return ret;
    }

    public ComplexNumber subtract(final ComplexNumber other)
    {
        ComplexNumber ret = new ComplexNumber();

        ret.num_.re_ = num_.re_ - other.num_.re_;
        ret.num_.im_ = num_.im_ - other.num_.im_;
        return ret;
    }

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

    public ComplexNumber divide(final ComplexNumber other)
    {
        PolarCoord pThis = num_.convertToPolar();
        PolarCoord pOther = other.num_.convertToPolar();

        pThis.len_ = pThis.len_ / pOther.len_;
        pThis.angle_ = pThis.angle_ - pOther.angle_;

        ComplexNumber ret = new ComplexNumber();
        ret.setPolarCoord(pThis);
        return ret;
    }

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

    public static void main(String args[])
    {
       ComplexNumber complex = new ComplexNumber();

        ComplexNumber.PolarCoord pCoord = new ComplexNumber.PolarCoord(3,Math.PI);
        ComplexNumber.PolarCoord pCoord2 = new ComplexNumber.PolarCoord(2,Math.PI);

        System.out.println(pCoord.len_);

    
    
    
    
    
    }






}