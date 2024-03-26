package io.novumd.core.domain

import io.novumd.core.model.UserExistsCommand
import io.novumd.core.model.UserId

fun interface UserExistsDomainService : UserExistsCommand {
  override operator fun invoke(id: UserId)
}