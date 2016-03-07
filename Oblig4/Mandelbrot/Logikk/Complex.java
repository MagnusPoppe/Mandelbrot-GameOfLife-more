package Oblig4.Mandelbrot.Logikk;

/**
 * Created by jooivind on 25.02.2016.
 *
 * Komplekst tall - forenklet!
 * Skal kun brukes for utregning av mandelbrot funksjonen.
 */
public class Complex
{
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

	/**
	 * Opprette et komplekst tall
	 * @param real reell-del
	 * @param imaginary imaginær-del
	 */
	public Complex(double real, double imaginary)
	{
		this.real = real;
		this.imaginary = imaginary;
	}

	/**
	 * Gange det komplekse tallet med seg selv
	 */
	public void square()
	{
		double newReal;
		double newImaginary;
		newReal = this.real * this.real - this.imaginary * this.imaginary;
		newImaginary = 2 * this.real * this.imaginary;
		this.real = newReal;
		this.imaginary = newImaginary;
	}

	/**
	 * Legge til et annet komplekst tall til dette komplekse tallet
	 * @param rhs tall man legger til
	 */
	public void add(Complex rhs)
	{
		this.real += rhs.real;
		this.imaginary += rhs.imaginary;
	}

	/**
	 * Summen av kvadratet av de to komponentene
	 * @return reelldel^2 + imaginærdel^2
	 */
	public double getLengthSquared()
	{
		return this.real * this.real + this.imaginary * this.imaginary;
	}
}
