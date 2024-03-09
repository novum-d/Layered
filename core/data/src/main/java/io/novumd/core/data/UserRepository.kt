package io.novumd.core.data

import io.novumd.core.model.UserId

interface UserRepository {
  fun register()
  fun find(id: UserId)
}