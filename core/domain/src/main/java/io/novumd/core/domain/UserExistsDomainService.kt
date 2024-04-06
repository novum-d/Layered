package io.novumd.core.domain

import arrow.core.raise.Raise
import io.novumd.core.model.UserExistsDomainError
import io.novumd.core.model.user.UserId

fun interface UserExistsDomainService {

  context(Raise<UserExistsDomainError>)
  operator fun invoke(id: UserId)
}