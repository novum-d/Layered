package io.novumd.core.domain.user

import arrow.core.raise.Raise
import io.novumd.core.data.UserRepository
import io.novumd.core.domain.UserExistsDomainService
import io.novumd.core.domain.UserRegisterUsecase
import io.novumd.core.model.UserRegisterUsecaseError
import io.novumd.core.model.user.User
import io.novumd.core.model.user.UserEmail
import io.novumd.core.model.user.UserRegisterCommand


internal class UserRegisterUsecaseImpl(
  private val userRepository: UserRepository,
  private val existsUser: UserExistsDomainService,
) : UserRegisterUsecase {

  context (Raise<UserRegisterUsecaseError>)
  override fun invoke(command: UserRegisterCommand) {
    existsUser(command.email.let(::UserEmail))
    val user = userRepository.run {
      User.create(
        name = command.name,
        email = command.email,
        password = command.password,
      )
    }

    existsUser(user.id)

    userRepository.register(user)
  }
}
