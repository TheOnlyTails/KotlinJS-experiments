
import kotlinx.browser.window
import kotlinx.coroutines.*
import kotlinx.css.fontFamily
import react.*
import react.dom.h3
import styled.css
import styled.styledDiv
import styled.styledH1

external interface AppState : RState {
	var currentVideo: Video?
	var unwatchedVideos: List<Video>
	var watchedVideos: List<Video>
}

class App : RComponent<RProps, AppState>() {
	override fun RBuilder.render() {
		// Title
		styledH1 {
			css {
				fontFamily = "arial"
			}
			+"KotlinConf Explorer"
		}

		// Videos
		styledDiv {
			css {
				fontFamily = "arial"
			}
			h3 { +"Videos to watch" }
			videoList {
				videos = state.unwatchedVideos
				selectedVideo = state.currentVideo
				onSelectVideo = { setState { currentVideo = it } }
			}

			h3 { +"Videos watched" }
			videoList {
				videos = state.watchedVideos
				selectedVideo = state.currentVideo
				onSelectVideo = { setState { currentVideo = it } }
			}
		}

		// Video player
		state.currentVideo?.let {
			videoPlayer {
				video = it
				isWatched = state.currentVideo in state.unwatchedVideos
				onWatchedButtonPress = {
					if (video in state.unwatchedVideos) {
						setState {
							unwatchedVideos -= video
							watchedVideos += video
							currentVideo = null
						}
					} else {
						setState {
							unwatchedVideos += video
							watchedVideos -= video
							currentVideo = null
						}
					}
				}
			}
		}
	}

	private suspend fun fetchVideos(): List<Video> = coroutineScope {
		(1..25).map { id -> async { fetchVideo(id) } }.awaitAll()
	}

	private suspend fun fetchVideo(id: Int) =
		window.fetch("https://my-json-server.typicode.com/kotlin-hands-on/kotlinconf-json/videos/$id")
			.await()
			.json()
			.await()
			.unsafeCast<Video>()

	override fun AppState.init() {
		unwatchedVideos = listOf()
		watchedVideos = listOf()

		MainScope().launch {
			val videos = fetchVideos()
			setState { unwatchedVideos = videos }
		}
	}
}