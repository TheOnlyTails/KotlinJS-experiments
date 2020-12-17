@file:JsModule("react-share")
@file:JsNonModule

import react.RClass
import react.RProps

@JsName("EmailIcon")
external val emailIcon: RClass<IconProps>

@JsName("EmailShareButton")
external val emailShareButton: RClass<ShareButtonProps>

@JsName("TelegramIcon")
external val telegramIcon: RClass<IconProps>

@JsName("TelegramShareButton")
external val telegramShareButton: RClass<ShareButtonProps>

@JsName("TwitterIcon")
external val twitterIcon: RClass<IconProps>

@JsName("TwitterShareButton")
external val twitterShareButton: RClass<ShareButtonProps>

@JsName("WhatsappIcon")
external val whatsappIcon: RClass<IconProps>

@JsName("WhatsappShareButton")
external val whatsappShareButton: RClass<ShareButtonProps>

external interface ShareButtonProps : RProps {
	var url: String
}

external interface IconProps : RProps {
	var size: Int
	var round: Boolean
}