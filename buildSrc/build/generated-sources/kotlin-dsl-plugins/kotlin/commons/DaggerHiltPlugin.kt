package commons


/**
 * Precompiled [dagger-hilt.gradle.kts][commons.Dagger_hilt_gradle] script plugin.
 *
 * @see commons.Dagger_hilt_gradle
 */
class DaggerHiltPlugin : org.gradle.api.Plugin<org.gradle.api.Project> {
    override fun apply(target: org.gradle.api.Project) {
        try {
            Class
                .forName("commons.Dagger_hilt_gradle")
                .getDeclaredConstructor(org.gradle.api.Project::class.java, org.gradle.api.Project::class.java)
                .newInstance(target, target)
        } catch (e: java.lang.reflect.InvocationTargetException) {
            throw e.targetException
        }
    }
}
