package io.novumd.core.domain.app

import arrow.core.raise.Raise
import io.novumd.core.data.AppRepository
import io.novumd.core.domain.AppTamperedWithDomainService
import io.novumd.core.model.AppTamperedWithDomainServiceError

internal class AppTamperedWithDomainServiceImpl(
    private val appRepository: AppRepository,
) : AppTamperedWithDomainService {
    context(Raise<AppTamperedWithDomainServiceError>)
    override fun invoke() {
        appRepository.checkAppTamperedWith()
    }
}