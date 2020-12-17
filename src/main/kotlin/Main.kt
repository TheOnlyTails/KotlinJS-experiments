
import kotlinx.browser.document
import react.dom.render

data class Video(val id: Int, val title: String, val speaker: String, val videoUrl: String)

fun main() {
	render(document.getElementById("root")) {
		child(App::class) {}
	}
}