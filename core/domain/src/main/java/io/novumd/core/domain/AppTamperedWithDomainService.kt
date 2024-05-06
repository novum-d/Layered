package io.novumd.core.domain

import arrow.core.raise.Raise
import io.novumd.core.model.AppTamperedWithDomainServiceError

interface AppTamperedWithDomainService {

    context(Raise<AppTamperedWithDomainServiceError>)
    operator fun invoke()
}