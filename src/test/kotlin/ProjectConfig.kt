import io.kotest.core.config.AbstractProjectConfig
import io.kotest.extensions.junitxml.JunitXmlReporter
import io.kotest.extensions.spring.SpringExtension

object ProjectConfig : AbstractProjectConfig() {
    override val parallelism = 4

    override fun extensions() = listOf(
        SpringExtension,
        JunitXmlReporter(
            useTestPathAsName = true
        )
    )
}