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
    userRepository.find(id) ?: raise(DomainErr.UserIdAlreadyExists)
  }

  context(Raise<UserEmailExistsDomainServiceError>)
  override fun invoke(email: UserEmail) {
    userRepository.find(email) ?: raise(DomainErr.UserEmailAlreadyExists)
  }
}