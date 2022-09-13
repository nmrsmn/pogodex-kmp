import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.registering
import org.gradle.kotlin.dsl.withType

class KotlinDetektConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("io.gitlab.arturbosch.detekt")
            }

            extensions.configure<DetektExtension> {
                source = files(
                    DetektExtension.DEFAULT_SRC_DIR_KOTLIN,
                    DetektExtension.DEFAULT_TEST_SRC_DIR_KOTLIN
                )

                buildUponDefaultConfig = true
            }
        }

        with(target.rootProject) {
            tasks.withType<Detekt>().configureEach {
                reports {
                    html.required.set(true)
                    md.required.set(true)
                    txt.required.set(true)

                    sarif.required.set(false)
                    xml.required.set(false)
                }
            }
        }
    }
}
