package io.novumd.core.model.user

@JvmInline
value class UserId(val value: String) {
  init {
    val pattern = """[a-z]{4}"""
    require("""^$pattern-$pattern$""".toRegex().matches(value)) {
      "UserId pattern is not matched."
    }
  }
}

@JvmInline
value class UserName(val value: String) {
  init {
    require(value.length > 3) {
      "UserName required more than length 3. But, value is $value"
    }
  }
}
