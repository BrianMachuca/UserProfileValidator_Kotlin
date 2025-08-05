/**
 * La estructura de datos para el perfil de un usuario.
 * Los campos son opcionales para reflejar los datos del endpoint.
 * Se corrigió 'age' para que sea nulable (Int?) y así manejar los casos de prueba.
 */
data class UserProfile(
    val name: String?,
    val email: String?,
    val age: Int?
)

/**
 * Procesa y valida un objeto UserProfile.
 *
 * param profile El perfil de usuario a procesar.
 * return `true` si el perfil tiene los campos requeridos y el email es válido.
 * `false` si falta un campo crítico o el email es inválido.
 */
fun processUserProfile(profile: UserProfile): Boolean {
    // 1. Cláusula de Guarda: Salir temprano si faltan datos críticos.
    if (profile.name.isNullOrEmpty() || profile.email.isNullOrEmpty()) {
        println("Error: Perfil incompleto. El nombre y el email son obligatorios.")
        return false
    }

    // 2. Validar la estructura del email.
    val email = profile.email
    var isEmailValid = true

    if (email != email.lowercase()) {
        println("Error de validación de email: El email '${email}' debe estar en minúsculas.")
        isEmailValid = false
    }
    if ('@' !in email) {
        println("Error de validación de email: El email '${email}' debe contener un carácter '@'.")
        isEmailValid = false
    }
    if ('.' !in email) {
        println("Error de validación de email: El email '${email}' debe contener un carácter '.'.")
        isEmailValid = false
    }
    if (email.length <= 10 || email.length >= 90) {
        println("Error de validación de email: La longitud de '${email}' debe ser mayor a 10 y menor a 90 caracteres.")
        isEmailValid = false
    }

    if (!isEmailValid) {
        return false
    }

    // 3. Validar la edad del usuario (no crítico).
    var isAgeValid = false
    if (profile.age != null) {
        if (profile.age > 18) {
            isAgeValid = true
        } else {
            println("Advertencia: La edad del usuario (${profile.age}) no es válida (debe ser mayor a 18).")
        }
    } else {
        println("Advertencia: La edad no fue proporcionada.")
    }


    // 4. Si todo es válido (incluida la edad), imprimir el mensaje de éxito.
    if (isAgeValid) {
        println(" Perfil procesado exitosamente: Nombre: ${profile.name}, Email: ${profile.email}, Edad: ${profile.age}")
    }

    // 5. Retornar true porque los datos requeridos estaban presentes y el email era válido.
    return true
}

fun main() {
    val profile1 = UserProfile(name = "Alice", email = "alice@example.com", age = 30)
    val profile2 = UserProfile(name = "Bob", email = "bobexample.com", age = 25)
    val profile3 = UserProfile(name = "Charlie", email = null, age = 40)
    val profile4 = UserProfile(name = "David", email = "david@example.com", age = -5)
    val profile5 = UserProfile(name = "Eve", email = "someprettylongandlongandlongemailweusedasanexapletoknowifithasmorethen90chars@example.com", age = null)

    println("--- Procesando Perfil 1 (Válido) ---")
    println("Resultado: ${processUserProfile(profile1)}\n")

    println("--- Procesando Perfil 2 (Email inválido) ---")
    println("Resultado: ${processUserProfile(profile2)}\n")

    println("--- Procesando Perfil 3 (Email nulo) ---")
    println("Resultado: ${processUserProfile(profile3)}\n")

    println("--- Procesando Perfil 4 (Edad inválida) ---")
    println("Resultado: ${processUserProfile(profile4)}\n")

    println("--- Procesando Perfil 5 (Email largo, edad nula) ---")
    println("Resultado: ${processUserProfile(profile5)}\n")
}
