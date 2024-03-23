package io.novumd.core.domain.user

import io.novumd.core.domain.UserRegisterUsecase
import io.novumd.core.model.User


internal class UserRegisterUsecaseImpl(
  private val userRepository: UserRepository,
  private val userService: UserDomainService
) : UserRegisterUsecase {
  override fun invoke(command: UserRegisterCommand) {
    val userName = UserName(command.name)
    // ファクトリによってインスタンスを生成する
    val user = User.from("", "")

    if (userService.exists(user)) {
      throw CanNotRegisterUserException(user)
    }

    userRepository.save(user)
  }
}

interface UserRepository {
  fun save(user: User)
}

class UserDomainService {
  fun exists(user: User): Boolean {
    // UserServiceの実装に合わせて適切な処理を記述する
    return false
  }
}

@JvmInline
value class UserName(val value: String)

data class UserRegisterCommand(
  val id: String,
  val name: String,
)

class CanNotRegisterUserException(val user: User) : Exception()