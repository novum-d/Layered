package io.novumd.core.model.user

import arrow.core.raise.Raise
import io.novumd.core.model.DomainError


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

fun User.asEntity() =
  UserEntity(
    id = id,
    name = name,
  )

private data class UserData(
  override val id: UserId,
  override val name: UserName,
) : User


interface UserFactoryCommand {
  context(Raise<DomainError.DatabaseError>)
  fun createUserId(): UserId
}

