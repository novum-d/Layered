package io.novumd.core.domain

import arrow.core.raise.Raise
import io.novumd.core.model.UserEmailExistsDomainServiceError
import io.novumd.core.model.UserIdExistsDomainServiceError
import io.novumd.core.model.user.UserEmail
import io.novumd.core.model.user.UserId

interface UserExistsDomainService {

  context(Raise<UserIdExistsDomainServiceError>)
  operator fun invoke(id: UserId)

  context(Raise<UserEmailExistsDomainServiceError>)
  operator fun invoke(id: UserEmail)
}