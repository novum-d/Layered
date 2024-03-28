package io.novumd.core.domain

import io.novumd.core.domain.user.UserRegisterCommand


interface UserRegisterUsecase {
  operator fun invoke(command: UserRegisterCommand)
}