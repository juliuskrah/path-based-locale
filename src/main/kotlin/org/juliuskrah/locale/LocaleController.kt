package org.juliuskrah.locale

import org.springframework.context.MessageSource
import org.springframework.context.MessageSourceAware
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.Locale

/**
 * @author Julius Krah
 */
@RestController
class LocaleController: MessageSourceAware {
    private lateinit var messageSource: MessageSource

    override fun setMessageSource(messageSource: MessageSource) {
        this.messageSource = messageSource
    }

    @GetMapping("/")
    fun home(locale: Locale, @RequestParam(defaultValue = "John Doe") name: String): String {
        return messageSource.getMessage("hello", arrayOf(name), locale)
    }
}