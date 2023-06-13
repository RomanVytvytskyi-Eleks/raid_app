# Raid App

### App provides essential information for individuals on the ground handling unannounced inspections and access to a 24/7 helpline to secure instant qualified legal support.

***

This is Android version of iOS app available in [App Store](https://apps.apple.com/ua/app/dentons-dawn-raids/id1524265416)

***

### Project code structure

The project tries to combine popular Android tools and to demonstrate best development practices by utilizing up to date tech-stack like Compose, Kotlin Flow and Koin.

* UI
  * [Compose](https://developer.android.com/jetpack/compose) declarative UI framework

* Tech/Tools
  * [Kotlin](https://kotlinlang.org/) 100% coverage
  * [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) and [Flow](https://developer.android.com/kotlin/flow) for async operations
  * [Koin](https://insert-koin.io/) for dependency injection
  * [Jetpack](https://developer.android.com/jetpack)
    * [Compose](https://developer.android.com/jetpack/compose)
    * [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) for navigation between composables
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) that stores, exposes and manages UI state
  * [Retrofit](https://square.github.io/retrofit/) for networking

  * Single activity architecture (with [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started)) that defines navigation graphs
  * MVI
  * [Android Architecture components](https://developer.android.com/topic/libraries/architecture) ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation))
  * [Android KTX](https://developer.android.com/kotlin/ktx) - Jetpack Kotlin extensions

## Architecture layers

* View - Composable screens that consume state, apply effects and delegate events.
* ViewModel - [AAC ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) that manages and reduces the state of the corresponding screen. Additionally, it intercepts UI events and produces side-effects. The ViewModel lifecycle scope is tied to the corresponding screen composable.
* Model - Repository classes that retrieve data. In a clean architecture context, one should use use-cases that tap into repositories.

![](https://i.imgur.com/UXwFbmv.png)

There are a three core components described:
* **State** - data class that holds the state content of the corresponding screen e.g. list of `User`, loading status etc. The state is exposed as a Compose runtime `MutableState` object from that perfectly matches the use-case of receiving continuous updates with initial value.

* **Event** - plain object that is sent through callbacks from the UI to the presentation layer. Events should reflect UI events caused by the user. Event updates are exposed as a `MutableSharedFlow` type which is similar to `StateFlow` and that behaves as in the absence of a subscriber, any posted event will be immediately dropped.

* **Effect** - plain object that signals one-time side-effect actions that should impact the UI e.g. triggering a navigation action, showing a Toast, SnackBar etc. Effects are exposed as `ChannelFlow` which behave as in each event is delivered to a single subscriber. An attempt to post an event without subscribers will suspend as soon as the channel buffer becomes full, waiting for a subscriber to appear.


## Branching

[Git Flow](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow)
is used for this project.

Branch name should contain proper prefix and ticket name - ```branch_prefix/ticket_name```. Example, ```feature/readme_file```.
In case changes was made without ticket name branch with self explainable name. Example, ```feature/readme_file```


Please, fill free to edit this README ;)





