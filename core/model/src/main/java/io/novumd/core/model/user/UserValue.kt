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
    val maxLength = 3
    require(value.length > maxLength) {
      "UserName required more than length $maxLength. But, value is $value"
    }
  }
}

@JvmInline
value class UserEmail(val value: String) {
  init {
    val pattern = """"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$"""
    require("""^$pattern$""".toRegex().matches(value)) {
      "UserEmail is not email."
    }
  }
}

@JvmInline
value class UserPassword(val value: String) {
  init {
    val minLength = 22
    require(value.length > minLength) {
      "UserId required less than length $minLength. But, value is $value"
    }

    val pattern = """[\d]{4}"""
    require("""^$pattern$""".toRegex().matches(value)) {
      "UserId pattern is not matched."
    }
  }
}
