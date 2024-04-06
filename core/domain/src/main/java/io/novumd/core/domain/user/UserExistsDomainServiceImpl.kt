package io.novumd.core.domain.user

import arrow.core.raise.Raise
import io.novumd.core.data.UserRepository
import io.novumd.core.domain.UserExistsDomainService
import io.novumd.core.model.Err
import io.novumd.core.model.UserEmailExistsDomainServiceError
import io.novumd.core.model.UserIdExistsDomainServiceError
import io.novumd.core.model.user.UserEmail
import io.novumd.core.model.user.UserId

internal class UserExistsDomainServiceImpl(
  private val userRepository: UserRepository
) : UserExistsDomainService {

  context(Raise<UserIdExistsDomainServiceError>)
  override fun invoke(id: UserId) {
    userRepository.fetch(id) ?: raise(Err.Domain.UserIdAlreadyExists)
  }

  context(Raise<UserEmailExistsDomainServiceError>)
  override fun invoke(id: UserEmail) {
    userRepository.fetch(id) ?: raise(Err.Domain.UserEmailAlreadyExists)
  }
}