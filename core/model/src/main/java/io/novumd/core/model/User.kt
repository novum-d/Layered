package io.novumd.core.model

data class User(
  val id: UserId,
  val name: UserName,
) {
  override fun equals(other: Any?): Boolean {
    if (other !is User) return false
    if (id != other.id) return false
    return true
  }
}

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
