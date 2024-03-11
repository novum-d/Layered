package io.novumd.core.domain

import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.IOException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ExceptionUserRegisterUsecase : UserRegisterUsecase {
  override fun invoke() {
    throw IOException()
  }
}

class ExceptionUserRegisterUsecase : UserRegisterUsecase {
  override fun invoke() {
    throw IOException()
  }
}

class ExampleUnitTest {
  @Test
  fun addition_isCorrect() {
    assertEquals(4, 2 + 2)
  }
}