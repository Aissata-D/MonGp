# MonGP

Application mobile qui met en relation des voyageurs (les « GP », pour *grands porteurs*) et des personnes qui veulent faire parvenir un colis. Elle couvre les trajets **France → Mali**.

> Projet de l'entreprise SITADIGI destiné aux téléphone Android et Iphone( deux store: play store et app store)

## Pourquoi

Aujourd'hui, les demandes de GP circulent dans des groupes WhatsApp et se perdent dans le flux des messages. MonGP les regroupe en un seul endroit, où on peut les consulter et les filtrer.

## Comment ça marche

- Un **voyageur** publie son voyage gratuitement : villes de départ et d'arrivée, date et heure du vol, numéro de vol, kilos disponibles, prix indicatif au kilo. Il peut le modifier ou le supprimer.
- Un **expéditeur** parcourt les voyages sans payer, les filtre par villes et ouvre le détail.
- Les deux se contactent par la **messagerie intégrée**. La première conversation est offerte.
- L'application ne gère **ni le paiement du transport, ni le suivi, ni la livraison**. Le prix et la remise du colis se règlent entre les deux personnes, hors application.
- Un même compte peut être voyageur et expéditeur. Un **administrateur** dispose d'un tableau de bord web.

## Modèle économique

| Gratuit | Payant |
|---|---|
| Publier un voyage, consulter les voyages, filtrer par villes, première conversation | Les conversations suivantes |

Un seul produit payant : un abonnement illimité de **9 € pour 1 mois**, sans renouvellement automatique, vendu par achat intégré Apple et Google (RevenueCat prévu).

## Architecture

Le projet est un monorepo Kotlin Multiplatform, organisé en Clean Architecture avec MVVM.

| Module | Rôle |
|---|---|
| `core` | Domaine partagé : entités, interfaces de repository, cas d'usage. Cibles Android, iOS, JVM, JS et WasmJS. Aucune dépendance à l'UI ni au réseau. |
| `app/shared` | Interface Compose Multiplatform, partagée entre les plateformes. Dépend de `core`. |
| `app/androidApp`, `app/iosApp`, `app/webApp` | Points d'entrée Android, iOS et web (tableau de bord admin). |
| `server` | Backend Ktor. Réutilise les entités de `core` grâce à la cible JVM. |

Sens des dépendances, toujours vers l'intérieur :

```
Écran Compose → ViewModel → Cas d'usage → Interface de repository ← Implémentation (Ktor Client)
```

Les interfaces de repository sont définies dans `core`. Leurs implémentations vivent à l'extérieur, ce qui garde le domaine indépendant du réseau.


## Stack

- Kotlin Multiplatform et Compose Multiplatform
- Ktor (serveur, puis client)
- kotlinx.serialization
- Gradle avec catalogue de versions
-  Koin, SQLDelight pour le mode hors ligne, RevenueCat pour les achats intégrés

## Avancement

- [x] Maquettes Figma
- [x] Entités et interfaces de repository (`core`)
- [x] Cas d'usage (les derniers sont en cours)
- [ ] Implémentations des repositories (Ktor Client)
- [ ] ViewModels et écrans
- [ ] Backend Ktor
- [ ] Mode hors ligne avec synchronisation
- [ ] Achats intégrés (RevenueCat)
- [ ] Tableau de bord administrateur

## Lancer le projet

Prérequis : Android Studio avec le support Kotlin Multiplatform.

Pour vérifier que le domaine compile :

```
./gradlew :core:compileKotlinJvm
```
# MonGp
