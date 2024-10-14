package io.novumd.core.model.user

import arrow.core.raise.Raise
import arrow.core.raise.ensure
import io.novumd.core.model.DomainErr
import io.novumd.core.model.Err

@JvmInline
value class UserId(val value: String)

@JvmInline
value class UserName(val value: String) {
    context(Raise<DomainErr.UserInput.Name>)
    fun validate() {
        val maxLength = 3
        ensure(value.length > maxLength) {
            DomainErr.UserInput.Name
        }
    }
}

@JvmInline
value class UserEmail(val value: String) {
    context(Raise<DomainErr.UserInput.Email>)
    fun validate() {
        val regex = """^[a-zA-Z0-9_.+-]+@([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]*\.)+[a-zA-Z]{2,}$""".toRegex()
        ensure(regex.matches(value)) {
            DomainErr.UserInput.Email
        }
    }
}

@JvmInline
value class UserPassword(val value: String) {
    context(Raise<DomainErr.UserInput.Password>)
    fun validate() {
        val minLength = 22
        val regex = """^\d{4}$""".toRegex()
        ensure(value.length > minLength && regex.matches(value)) {
            DomainErr.UserInput.Password
        }
    }
}
