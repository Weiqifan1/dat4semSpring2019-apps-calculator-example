package dk.cphbusiness.androidkotlinintro

import java.io.Serializable

data class Rational (
        val numerator: Long,
        val denominator: Long
        ) : Comparable<Rational>, Serializable {

    override fun compareTo(other: Rational) =
        (this.numerator*other.denominator).compareTo(other.numerator*this.denominator)

    val gcd: Long by lazy { gcd(numerator, denominator) }
    val text: String
        get() = if (denominator/gcd == 1L) "${numerator/gcd}"
                else "${numerator/gcd}/${denominator/gcd}"


    companion object {
        fun normalised(numerator: Long, denominator: Long): Rational {
            val gcd = gcd(numerator, denominator)
            return Rational(numerator/gcd, denominator/gcd)
            }
        }

    operator fun times(other: Rational) =
        Rational(this.numerator*other.numerator, this.denominator*other.denominator)
    operator fun times(integer: Long) = Rational(numerator*integer, denominator)

    operator fun plus(other: Rational) =
        Rational(
            this.numerator*other.denominator + this.denominator*other.numerator,
            this.denominator*other.denominator
            )
    operator fun plus(integer: Long) = this + integer.toRational()

    operator fun div(other: Rational) =
        Rational(numerator*other.denominator, denominator*other.numerator)
    operator fun div(integer: Long) = this/integer.toRational()

    operator fun unaryMinus() = Rational(-numerator, denominator)

    operator fun minus(other: Rational) = this + -other
    operator fun minus(integer: Long) = this - integer.toRational()

    }

fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a%b)

fun Long.toRational() = Rational(this, 1)
fun Int.toRational() = Rational(this.toLong(), 1)

infix fun Long.over(other: Long) = Rational.normalised(this, other)
infix fun Int.over(other: Long) = Rational.normalised(this.toLong(), other)

operator fun Long.times(rational: Rational) = this.toRational()*rational
operator fun Long.plus(rational: Rational) = this.toRational() + rational
operator fun Long.div(rational: Rational) = this.toRational()/rational
operator fun Long.minus(rational: Rational) = this.toRational() - rational

operator fun Int.times(rational: Rational) = this.toRational()*rational
operator fun Int.plus(rational: Rational) = this.toRational() + rational
operator fun Int.div(rational: Rational) = this.toRational()/rational
operator fun Int.minus(rational: Rational) = this.toRational() - rational

/*
fun main(args: Array<String>) {

    val r1 = 22 over 7
    val r2 = Rational(14, 22)
    println(r1)
    println(r2)
    println(r1*r2)
    println(r1/r2)
    println(r1*3)
    println(4*r2)
    println(gcd(70, 15))
    }
*/