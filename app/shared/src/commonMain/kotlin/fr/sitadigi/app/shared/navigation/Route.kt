package fr.sitadigi.app.shared.navigation

import androidx.navigation3.runtime.NavKey
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Serializable
sealed interface Route : NavKey

@Serializable
data object TripListRoute : Route

@Serializable
data class TripDetailRoute(val tripId: String) : Route

val routeSavedStateConfiguration = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(TripListRoute::class, TripListRoute.serializer())
            subclass(TripDetailRoute::class, TripDetailRoute.serializer())
        }
    }
}