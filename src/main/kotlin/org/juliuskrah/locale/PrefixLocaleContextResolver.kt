package org.juliuskrah.locale

import org.springframework.context.i18n.LocaleContext
import org.springframework.context.i18n.SimpleLocaleContext
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.adapter.WebHttpHandlerBuilder
import org.springframework.web.server.i18n.AcceptHeaderLocaleContextResolver
import org.springframework.web.util.pattern.PathPatternParser
import java.util.Locale

/**
 * @author Julius Krah
 */
@Component(WebHttpHandlerBuilder.LOCALE_CONTEXT_RESOLVER_BEAN_NAME)
class PrefixLocaleContextResolver : AcceptHeaderLocaleContextResolver() {

    override fun resolveLocaleContext(exchange: ServerWebExchange): LocaleContext {
        val container = exchange.request.path.pathWithinApplication()
        val pathPattern = PathPatternParser.defaultInstance.parse("/{locale}")
        val pathInfo = pathPattern.matchStartOfPath(container)
        val localString = pathInfo?.uriVariables?.get("locale")
        val locale = resolveLocale(localString)
        return if (locale != null)
            SimpleLocaleContext(locale)
        else
            super.resolveLocaleContext(exchange)
    }

    override fun getDefaultLocale(): Locale? = Locale.getDefault()

    private fun resolveLocale(localeString: String?): Locale? {
        if (localeString?.length == 2) {
            return Locale.forLanguageTag(localeString)
        }
        return null
    }
}