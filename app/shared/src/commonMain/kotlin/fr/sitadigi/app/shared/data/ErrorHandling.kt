package fr.sitadigi.app.shared.data

import fr.sitadigi.core.domain.error.AppError
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import kotlin.coroutines.cancellation.CancellationException


suspend fun HttpResponse.throwIfError() {
    if (status.isSuccess()) return
    val message = bodyAsText()
    when (status.value) {
        400 -> throw AppError.RequestNotValid(message)
        401 -> throw AppError.NotAuthenticated(message)
        402 -> throw AppError.PaymentRequired(message)
        403 -> throw AppError.AccessDenied(message)
        404 -> throw AppError.NotFound(message)
        409 -> throw AppError.Conflict(message)
        429 -> throw AppError.TooManyRequests(message)
        else -> throw AppError.UnknownError(message)
    }
}


suspend fun <T> safeCall(block: suspend () -> T): T {
    try {
        return block()
    } catch (e: AppError) {
        throw e
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        throw AppError.NetworkError(e.message)
    }
}