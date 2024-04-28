package io.novumd.core.model.user

import arrow.core.raise.Raise
import arrow.core.raise.ensure
import io.novumd.core.model.Err

@JvmInline
value class UserId(val value: String)

@JvmInline
value class UserName(val value: String) {
  context(Raise<Err.Domain.UserInvalid.Name>)
  fun validate() {
    val maxLength = 3
    ensure(value.length > maxLength) {
      Err.Domain.UserInvalid.Name
    }
  }
}

@JvmInline
value class UserEmail(val value: String) {
  context(Raise<Err.Domain.UserInvalid.Email>)
  fun validate() {
    val pattern = """^[a-zA-Z0-9_.+-]+@([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]*\.)+[a-zA-Z]{2,}$"""
    ensure(pattern.toRegex().matches(value)) {
      Err.Domain.UserInvalid.Email
    }
  }
}

@JvmInline
value class UserPassword(val value: String) {
  context(Raise<Err.Domain.UserInvalid.Password>)
  fun validate() {
    val minLength = 22
    val pattern = """\d{4}"""
    ensure(value.length > minLength && """^$pattern$""".toRegex().matches(value)) {
      Err.Domain.UserInvalid.Password
    }
  }
}
