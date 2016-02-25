package Mandelbrot.Mandelbrot;

/**
 * Created by jooivind on 25.02.2016.
 *
 *
 */
public class Complex {
    private double real;
    private double imaginary;

    public Complex(double real, double imaginary)
    {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Complex square()
    {
        double newReal;
        double newImaginary;
        newReal = this.real*this.real + this.imaginary*this.imaginary;
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
}
