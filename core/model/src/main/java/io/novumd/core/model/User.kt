package io.novumd.core.model

import arrow.core.raise.Raise


interface User {
  val id: UserId
  val name: UserName

  companion object {

    context(Raise<DomainError.UserExists>, UserExistsCommand)
    fun from(
      id: String,
      name: String,
    ): User {
      // raise(DomainError.UserExists)
      return UserData(
        id = id.let(::UserId),
        name = name.let(::UserName),
      )
    }
  }
}


private data class UserData(
  override val id: UserId,
  override val name: UserName,
) : User {

  override fun equals(other: Any?): Boolean {
    if (other !is User) return false
    if (id != other.id) return false
    return true
  }

  override fun hashCode(): Int {
    var result = id.hashCode()
    result = 31 * result + name.hashCode()
    return result
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

fun interface UserExistsCommand {
  operator fun invoke(id: UserId)
}
