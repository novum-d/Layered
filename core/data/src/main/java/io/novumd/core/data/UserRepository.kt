package io.novumd.core.data

import io.novumd.core.model.user.User
import io.novumd.core.model.user.UserFactoryCommand
import io.novumd.core.model.user.UserId


interface UserRepository : UserFactoryCommand {
  fun register(user: User)

  override fun createUserId(): UserId
}

internal class UserRepositoryImpl : UserRepository {
  override fun register(user: User) {
    TODO("Not yet implemented")
  }

  override fun createUserId(): UserId {
    TODO("Not yet implemented")
  }
}