package io.novumd.core.domain.user

import io.novumd.core.domain.UserExistsDomainService
import io.novumd.core.model.user.UserId

internal class UserExistsDomainServiceImpl : UserExistsDomainService {
  override fun invoke(id: UserId): Boolean {
    // TODO exists
    return false
  }
}