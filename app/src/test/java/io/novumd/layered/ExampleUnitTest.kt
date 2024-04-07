package io.novumd.layered

import arrow.core.raise.recover
import io.novumd.core.model.user.UserUpdateCommand
import io.novumd.core.model.user.validate
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
  @Test
  fun test() {
    recover({ UserUpdateCommand("", "", "", "").validate() }) {
      assertEquals(4, it.all.size)
    }
  }
}