package org.juliuskrah.locale

import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.method.HandlerTypePredicate
import org.springframework.web.reactive.config.PathMatchConfigurer
import org.springframework.web.reactive.config.WebFluxConfigurer

/**
 * @author Julius Krah
 */
@Component
class LocaleWebFluxConfigurer : WebFluxConfigurer {

    override fun configurePathMatching(configurer: PathMatchConfigurer) {
        configurer
            .setUseCaseSensitiveMatch(false)
            .addPathPrefix("/{locale}", HandlerTypePredicate.forAnnotation(RestController::class.java))
    }

}