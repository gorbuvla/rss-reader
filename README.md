# RSS Reader App

[![jCenter](https://img.shields.io/badge/Kotlin-1.3.72-green.svg)]()
[![jCenter](https://img.shields.io/badge/Compose-dev09-green.svg)](https://developer.android.com/jetpack/compose)

## Project
After trying out SwiftUI on simple project I was curious to try Compose.
This is a simple RSS Reader, main goal of the project is to get familiar with Jetpack Compose.

## Git
Wondering what all these emojis are? Checkout [gitmoji](https://gitmoji.carloscuesta.me/).

## Tech stack

* Jetpack Compose
* Kotlin corutines & Flow
* Koin
* Architecture components

## Structure

Because of experimental compose compiler compatibility issues, app is split up into several modules.
Each module is an Android Library and is designed in a way so that Compose, Room and experimental Flow api
do not reside in single module.

Logic structure - 3 layers:
* App - container for the whole app, contains DI graph, basic setup of global stuff (Timber, etc.)
* Features - contains single feature, features should be independent and can depend on libraries.
* Libraries - contains feature unrelated code, can depend on each other.

Features:
- **:articles**
Contains whole article flow (list & detail).
- **:feeds**
Module containing the functionality concerning Feeds CRUD.

Libraries:
- **:core**
Module with application domain.
- **:database**
Module responsible for local data persistence.
- **:rss**
Module responsible for remote RSS data access.
- **:ui**
Presentation related code which can be shared between features.

## Compose pitfalls

- No other way to use Room & Flow with Compose than modularize the app 🤷‍
- [Issue](https://issuetracker.google.com/issues/150174792) General problem with lists. **VerticalScroller** is ok for limited amount of data, however when used to present list of articles it takes half day to be drawn.
So using **AdapterList** is much better in this case, but it has its own pitfalls. Once list data is loaded and user starts scrolling, previously used composables for row items are not reused/disposed, which
makes app leak like ones 🍑 after 🥛 + 🥒.
- [Compose Slack](https://kotlinlang.slack.com/archives/CJLTWPH7S/p1586083806130100) I had to simplify design because of some component incompatibility. Generally, there are adapters for each composable component. And adapter **Card** seems to not behave properly with **AdapterList**.