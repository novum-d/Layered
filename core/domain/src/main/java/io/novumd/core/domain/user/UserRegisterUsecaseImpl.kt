package io.novumd.core.domain.user

import arrow.core.Eval.Companion.raise
import arrow.core.raise.Raise
import io.novumd.core.data.UserRepository
import io.novumd.core.domain.UserExistsDomainService
import io.novumd.core.domain.UserRegisterUsecase
import io.novumd.core.model.DomainError
import io.novumd.core.model.User
import io.novumd.core.model.UserRegisterUseCaseError


internal class UserRegisterUsecaseImpl(
  private val userRepository: UserRepository,
  private val userExistsDomainService: UserExistsDomainService,
) : UserRegisterUsecase {

  context (Raise<UserRegisterUseCaseError>)
  override fun invoke(command: UserRegisterCommand) {
    val user = userRepository.run { User.from(command.name) }
    if (userExistsDomainService(user.id)) raise(DomainError.UserExists)
    userRepository.register(user)
  }
}

data class UserRegisterCommand(
  val name: String,
)
