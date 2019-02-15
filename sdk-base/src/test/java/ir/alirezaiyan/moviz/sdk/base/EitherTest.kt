package ir.alirezaiyan.moviz.sdk.base

import ir.alirezaiyan.moviz.sdk.base.Either.Left
import ir.alirezaiyan.moviz.sdk.base.Either.Right
import ir.alirezaiyan.moviz.sdk.test.AndroidTest
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqualTo
import org.junit.Test

class EitherTest : AndroidTest() {

    @Test fun `Either Right should return correct type`() {
        val result = Right("moviz")

        result shouldBeInstanceOf Either::class.java
        result.isRight shouldBe true
        result.isLeft shouldBe false
        result.either({},
                { right ->
                    right shouldBeInstanceOf String::class.java
                    right shouldEqualTo "moviz"
                })
    }

    @Test fun `Either Left should return correct type`() {
        val result = Left("moviz")

        result shouldBeInstanceOf Either::class.java
        result.isLeft shouldBe true
        result.isRight shouldBe false
        result.either(
                { left ->
                    left shouldBeInstanceOf String::class.java
                    left shouldEqualTo "moviz"
                }, {})
    }
}