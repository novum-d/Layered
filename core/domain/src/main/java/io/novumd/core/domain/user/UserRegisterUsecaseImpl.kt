package io.novumd.core.domain.user

import arrow.core.raise.Raise
import io.novumd.core.data.UserRepository
import io.novumd.core.domain.UserExistsDomainService
import io.novumd.core.domain.UserRegisterUsecase
import io.novumd.core.model.Err
import io.novumd.core.model.UserRegisterDataError
import io.novumd.core.model.user.User
import io.novumd.core.model.user.UserRegisterCommand


internal class UserRegisterUsecaseImpl(
  private val userRepository: UserRepository,
  private val userExistsDomainService: UserExistsDomainService,
) : UserRegisterUsecase {

  context (Raise<UserRegisterDataError>)
  override fun invoke(command: UserRegisterCommand) {
    val user = userRepository.run {
      User.create(
        name = command.name,
        email = command.email,
        password = command.password,
      )
    }

    if (userExistsDomainService(user.id)) raise(Err.UserExists)

    userRepository.register(user)
  }
}
