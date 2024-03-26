package io.novumd.core.data

import io.novumd.core.model.User
import io.novumd.core.model.UserId


interface UserRepository {
  fun register(user: User)

  fun isExists(id: UserId): User
}

internal class UserRepositoryImpl : UserRepository {
  override fun register(user: User) {
    TODO("Not yet implemented")
  }

  override fun isExists(id: UserId): User {
    TODO("Not yet implemented")
  }
}