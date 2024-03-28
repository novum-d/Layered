package io.novumd.core.model

import arrow.core.raise.Raise


interface User {
  val id: UserId
  val name: UserName

  companion object {
    context(Raise<DomainError.DatabaseError>, UserFactoryCommand)
    fun from(name: String): User =
      UserData(
        id = createUserId(),
        name = name.let(::UserName),
      )
  }
}

interface UserFactoryCommand {
  context(Raise<DomainError.DatabaseError>)
  fun createUserId(): UserId
}


private data class UserData(
  override val id: UserId,
  override val name: UserName,
) : User


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
