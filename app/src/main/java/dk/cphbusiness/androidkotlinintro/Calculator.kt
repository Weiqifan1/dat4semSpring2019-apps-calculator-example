package dk.cphbusiness.androidkotlinintro

import java.io.Serializable

class Calculator : Serializable {
    var stackT = 0.toRational()
    var stackZ = 0.toRational()
    var stackY = 0.toRational()
    var stackX = 0.toRational()

    var memory = 0.toRational()

    var history: Calculator? = null

    fun enter(number: Long): Calculator = push(number.toRational())

    fun div(): Calculator {
        val active = copy()
        val top = active.pop()
        active.stackX /= top
        return active
        }

    fun plus(): Calculator {
        val active = copy()
        val top = active.pop()
        active.stackX += top
        return active
        }

    fun times(): Calculator {
        val active = copy()
        val top = active.pop()
        active.stackX *= top
        return active
        }

    fun minus(): Calculator {
        val active = copy()
        val top = active.pop()
        active.stackX -= top
        return active
        }

    fun clear(): Calculator {
        val active = copy()
        active.stackX = 0.toRational()
        active.stackY = 0.toRational()
        active.stackZ = 0.toRational()
        active.stackT = 0.toRational()
        return active
        }

    fun store(): Calculator {
        val active = copy()
        active.memory = stackX
        return active
        }

    fun recall(): Calculator {
        return push(memory)
        }

    fun swap(): Calculator {
        val active = copy()
        active.stackY = stackX
        active.stackX = stackY
        return active
        }

    fun rollDown(): Calculator {
        val active = copy()
        val top = active.pop()
        active.stackT = top
        return active
        }

    fun push(rational: Rational): Calculator {
        val active = copy()
        active.stackT = stackZ
        active.stackZ = stackY
        active.stackY = stackX
        active.stackX = rational
        return active
        }

    private fun pop(): Rational {
        val top = stackX
        stackX = stackY
        stackY = stackZ
        stackZ = stackT
        return top
        }

    fun copy(): Calculator {
        val copyOfThis = Calculator()
        copyOfThis.stackX = stackX
        copyOfThis.stackY = stackY
        copyOfThis.stackZ = stackZ
        copyOfThis.stackT = stackT
        copyOfThis.memory = memory
        copyOfThis.history = this
        return copyOfThis
        }
    }