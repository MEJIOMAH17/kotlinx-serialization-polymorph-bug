package com.github.mejiomah17.kotlinx.serialization

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.annotations.TestOnly
import org.junit.jupiter.api.Test

class Reproduce {
    @Test
    fun reproduce() {
        val fine = B.Bx(A.Ax())
        Json.encodeToString(fine)
        val bug: B<A.Ax> = fine
        Json.encodeToString(bug)
    }

    @Serializable
    sealed class A {
        @Serializable
        class Ax : A()
    }

    @Serializable
    sealed class B<T : A> {
        abstract val a: T

        @Serializable
        class Bx<T : A>(
            override val a: T
        ) : B<T>()
    }

}