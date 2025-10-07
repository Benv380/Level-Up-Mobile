package ui.repository.auth

import ui.model.User

class AuthRepository(
    private val ds: FirebaseAuthDataSource = FirebaseAuthDataSource()
) {
    suspend fun login(email: String, pass: String): User? {
        val fu = ds.signIn(email, pass) ?: return null
        return User(uid = fu.uid, email = fu.email)
    }

    fun logout() = ds.signOut()
    fun currentUser(): User? = ds.currentUser()?.let { User(it.uid, it.email) }
}