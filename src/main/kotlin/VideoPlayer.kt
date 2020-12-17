
import kotlinx.css.*
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.h3
import styled.css
import styled.styledButton
import styled.styledDiv

external interface VideoPlayerProps : RProps {
	var video: Video
	var onWatchedButtonPress: (Video) -> Unit
	var isWatched: Boolean
}

class VideoPlayer : RComponent<VideoPlayerProps, RState>() {
	override fun RBuilder.render() {
		styledDiv {
			css {
				position = Position.absolute
				top = 10.px
				right = 10.px
				fontFamily = "arial"
			}

			h3 {
				+"${props.video.speaker}: ${props.video.title}"
			}

			styledButton {
				css {
					display = Display.block
					backgroundColor = if (props.isWatched) Color.lightGreen else Color.red
					marginBottom = 10.px
					fontFamily = "arial"
				}
				attrs {
					onClickFunction = {
						props.onWatchedButtonPress(props.video)
					}
				}
				if (props.isWatched) {
					+"Mark as watched"
				} else {
					+"Mark as unwatched"
				}
			}

			styledDiv {
				css {
					display = Display.flex
					marginBottom = 10.px
				}

				emailShareButton {
					attrs.url = props.video.videoUrl
					emailIcon {
						attrs.size = 32
						attrs.round = true
					}
				}

				whatsappShareButton {
					attrs.url = props.video.videoUrl
					whatsappIcon {
						attrs.size = 32
						attrs.round = true
					}
				}

				twitterShareButton {
					attrs.url = props.video.videoUrl
					twitterIcon {
						attrs.size = 32
						attrs.round = true
					}
				}
			}

			reactPlayer {
				attrs.url = props.video.videoUrl
			}
		}
	}
}

fun RBuilder.videoPlayer(handler: VideoPlayerProps.() -> Unit): ReactElement =
	child(VideoPlayer::class) { this.attrs(handler) }