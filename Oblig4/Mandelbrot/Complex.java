package Oblig4.Mandelbrot;

/**
 * Created by jooivind on 25.02.2016.
 *
 *
 */
public class Complex {
    private double real;
    private double imaginary;

    /**
     * Standard konstruksjon av en Complex blir satt til 0+0i
     */
    public Complex()
    {
        real = 0.0;
        imaginary = 0.0;
    }
    public Complex(double real, double imaginary)
    {
        this.real = real;
        this.imaginary = imaginary;
    }

	/**
         * Gange det komplekse tallet med seg selv
         * @return svaret
         */
        public Complex square()
    {
        double newReal;
        double newImaginary;
        newReal = this.real*this.real - this.imaginary*this.imaginary;
        newImaginary = 2*this.real * this.imaginary;
        return new Complex(newReal, newImaginary);
    }

    public Complex add(Complex rhs)
    {
        return new Complex(this.real+rhs.real, this.imaginary+rhs.imaginary);
    }

    public double getReal()
    {
        return this.real;
    }

    public double getImaginary()
    {
        return this.imaginary;
    }

    public double getLengthSquared()
    {
        return this.real*this.real + this.imaginary*this.imaginary;
    }
}
