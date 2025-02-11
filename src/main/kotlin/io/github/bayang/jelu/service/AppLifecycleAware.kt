package io.github.bayang.jelu.service

import io.github.bayang.jelu.config.JeluProperties
import mu.KotlinLogging
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.io.File

private val logger = KotlinLogging.logger {}

@Component
class AppLifecycleAware(
    private val properties: JeluProperties
) {

    @EventListener
    fun onApplicationEvent(event: ContextRefreshedEvent?) {
        val assetsDir = File(properties.files.images)
        if (!assetsDir.exists()) {
            val created = assetsDir.mkdirs()
            logger.debug { "Attempt to create non existing assets dir succeeded : $created" }
        }
        val importsDir = File(properties.files.imports)
        if (!importsDir.exists()) {
            val created = importsDir.mkdirs()
            logger.debug { "Attempt to create non existing imports dir succeeded : $created" }
        }
    }
}
