public class Main
{
    public static void main(String args[])
    {
        System.out.println("Welcome to a small example");

        //Create complex number by setting cartesian coordinates
        ComplexNumber number1 = new ComplexNumber().setCartesianCoord(12, 34);

        //Create complex number by setting polar coordinates
        ComplexNumber number2 = new ComplexNumber().setPolarCoord(21, Math.PI/2.0);

        //Add both numbers
        ComplexNumber result = number1.add(number2);

        //Subtract number2 again
        result = result.subtract(number2);

        //Calculate the second power of the complex number
        result = result.power(2);

        //Revert the calculation by using the square root
        result = result.squareRoot();

        //Multiply and divide the number, using method chaining
        result = result.multiply(number1).divide(number1);

        if (result.equal(number1))
            System.out.println("Calculation was successful :)");
        else
            System.out.println("Something's no right");

        System.exit(0);
    }
}
