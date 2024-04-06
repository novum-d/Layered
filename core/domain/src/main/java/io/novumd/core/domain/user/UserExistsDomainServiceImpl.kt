package io.novumd.core.domain.user

import io.novumd.core.domain.UserExistsDomainService
import io.novumd.core.model.user.UserEmail
import io.novumd.core.model.user.UserId

internal class UserExistsDomainServiceImpl : UserExistsDomainService {
  override fun invoke(id: UserId) {}
  override fun invoke(id: UserEmail) {}
}