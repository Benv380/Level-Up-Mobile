package cl.daeriquelme.appduoc_profe.ui.app

sealed class Route(val path: String) {
    data object HomeRoot : Route("homeRoot")
    data object Login : Route("login")
    data object Principal : Route("principal")
    data object Register : Route("registrarse")
    data object RecoverPassword : Route("recuperarContrasena")
}
