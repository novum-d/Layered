package io.novumd.core.data

import io.novumd.core.model.User
import io.novumd.core.model.UserExistsRepository
import io.novumd.core.model.UserId


interface UserRepository : UserExistsRepository {
  fun register()
}

internal class UserRepositoryImpl : UserRepository {
  override fun register() {
    TODO("Not yet implemented")
  }

  override fun find(id: UserId): User {
    TODO("Not yet implemented")
  }
}