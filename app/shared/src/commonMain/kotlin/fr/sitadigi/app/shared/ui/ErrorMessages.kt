package fr.sitadigi.app.shared.ui


import fr.sitadigi.core.domain.error.AppError

fun AppError.toMessage(): String = when (this) {
    is AppError.NetworkError -> "Pas de connexion. Vérifie ton réseau et réessaie."
    is AppError.NotAuthenticated -> "Ta session a expiré. Reconnecte-toi."
    is AppError.PaymentRequired -> "Un abonnement est nécessaire pour continuer."
    is AppError.AccessDenied -> "Tu n'as pas le droit de faire cette action."
    is AppError.Conflict -> "Cet élément existe déjà."
    is AppError.NotFound -> "Cet élément n'existe plus ou est introuvable."
    is AppError.RequestNotValid -> "Certaines informations ne sont pas valides. Vérifie-les et réessaie."
    is AppError.TooManyRequests -> "Trop de tentatives. Attends un moment avant de réessayer."
    is AppError.UnknownError -> "Une erreur inconnue s'est produite."
}

