package io.novumd.core.domain.user

import arrow.core.raise.Raise
import io.novumd.core.data.UserRepository
import io.novumd.core.domain.UserExistsDomainService
import io.novumd.core.domain.UserRegisterUsecase
import io.novumd.core.model.DomainError
import io.novumd.core.model.UserRegistrationError
import io.novumd.core.model.user.User
import io.novumd.core.model.user.UserRegisterCommand


internal class UserRegisterUsecaseImpl(
  private val userRepository: UserRepository,
  private val userExistsDomainService: UserExistsDomainService,
) : UserRegisterUsecase {

  context (Raise<UserRegistrationError>)
  override fun invoke(command: UserRegisterCommand) {
    val user = userRepository.run {
      User.create(
        name = command.name,
        email = command.email,
        password = command.password,
      )
    }

    if (userExistsDomainService(user.id)) raise(DomainError.UserExists)

    userRepository.register(user)
  }
}
