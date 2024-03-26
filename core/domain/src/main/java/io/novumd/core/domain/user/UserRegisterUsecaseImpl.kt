package io.novumd.core.domain.user

import arrow.core.raise.Raise
import io.novumd.core.data.UserRepository
import io.novumd.core.domain.UserExistsDomainService
import io.novumd.core.domain.UserRegisterUsecase
import io.novumd.core.model.DomainError
import io.novumd.core.model.User


internal class UserRegisterUsecaseImpl(
  private val userRepository: UserRepository,
  private val userDomainService: UserExistsDomainService,
) : UserRegisterUsecase {

  context (Raise<DomainError>)
  override fun invoke(command: UserRegisterCommand) =
    userDomainService.run {
      // ファクトリによってインスタンスを生成する
      val user = User.from(command.id, command.name)
      userRepository.register(user)
    }
}

data class UserRegisterCommand(
  val id: String,
  val name: String,
)