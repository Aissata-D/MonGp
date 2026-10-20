package fr.sitadigi.core.domain.error

sealed class AppError(message: String? = null) : Exception(message) {
    class NotAuthenticated(message: String? = null) : AppError(message) // 401 code error
    class PaymentRequired(message: String? = null) : AppError(message) // 402 code error
    class RequestNotValid(message: String? = null) : AppError(message) // 400 code error
    class AccessDenied(message: String? = null) : AppError(message)// 403 code error
    class NotFound(message: String? = null) : AppError(message) // 404 code error
    class Conflict(message: String? = null) : AppError(message)// 409 code error
    class TooManyRequests(message: String? = null) : AppError(message) // 429 code error
    class NetworkError(message: String? = null) : AppError(message) // No response from the server: no connection or the request timed out
    class UnknownError(message: String? = null) : AppError(message) // Any HTTP code the contract does not cover, including 5xx errors.
}
