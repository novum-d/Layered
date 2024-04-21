package io.novumd.core.domain

import io.novumd.core.model.user.UserRegisterCommand

interface UserRegisterUseCase {
  operator fun invoke(registerCommand: UserRegisterCommand)
}