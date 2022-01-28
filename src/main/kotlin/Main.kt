import java.util.logging.Level
import java.util.logging.LogManager

internal val l = LogManager.getLogManager().getLogger("").apply { level = Level.ALL }
internal fun i(tag: String, msg: String) {
    l.info("[$tag] - $msg")
}

interface Interfaz {
    fun existeLibro(idLibro: String): Boolean
    fun infoLibro(idLibro: String): Map<String, Any>
}

class gestionLibrosIUEspaniol() : InterfazGestionLibros {
    override fun introducirID(): String {
        println("Introduzca un ID: ")
        val idLibro = readln()
        return idLibro
    }

    override fun libroExiste(idLibro: String) {
        return println("El libro $idLibro existe!")
    }

    override fun libroNoExiste(idLibro: String) {
        return println("El libro $idLibro NO existe!")
    }

    override fun informacionLibro(infoLibro: Map<String, Any>) {
        return println("La información sobre es la siguiente\n$infoLibro")
    }

    override fun noInformacionLibro() {
        return println("No se encontró información sobre el libro")
    }
}

class gestionLibrosIUIngles() : InterfazGestionLibros {
    override fun introducirID(): String {
        println("Enter an ID:")
        val idLibro = readln()
        return idLibro
    }

    override fun libroExiste(idLibro: String) {
        return println("The book $idLibro exists!")
    }

    override fun libroNoExiste(idLibro: String) {
        return println("The book $idLibro does NOT exist!")
    }

    override fun informacionLibro(infoLibro: Map<String, Any>) {
        return println("Information about is the following\n$infoLibro")
    }

    override fun noInformacionLibro() {
        return println("No information found about the book")
    }
}

interface InterfazGestionLibros {
    fun introducirID(): String
    fun libroExiste(idLibro: String)
    fun libroNoExiste(idLibro: String)
    fun informacionLibro(infoLibro: Map<String, Any>)
    fun noInformacionLibro()
}

class gestionLibros(val cat: Interfaz, val libro: InterfazGestionLibros) {

    fun preguntarPorUnLibro() {
        val idLibro = libro.introducirID()
        if (cat.existeLibro(idLibro))
            libro.libroExiste(idLibro)
        else
            libro.libroNoExiste(idLibro)
    }

    fun mostrarInfoDeUnLibro() {
        val idLibro = libro.introducirID()
        val infoLibro = cat.infoLibro(idLibro)
        if (infoLibro.isNotEmpty())
            libro.informacionLibro(infoLibro)
        else
            libro.noInformacionLibro()
    }

}

fun main() {
    val portatil = "src/main/kotlin/Catalog.xml"
    //var casa = "/home/usuario/Documentos/workspace/IdeaProjects/IESRA-DAM/ejercicios/src/main/kotlin/un5/eje5_4/Catalog.xml"

    val gestorDeLibros = gestionLibros(CatalogoLibrosXML(portatil), gestionLibrosIUEspaniol())
    val gestorDeLibrosIngles = gestionLibros(CatalogoLibrosXML(portatil), gestionLibrosIUIngles())
    gestorDeLibros.preguntarPorUnLibro()
    gestorDeLibros.mostrarInfoDeUnLibro()

    gestorDeLibrosIngles.preguntarPorUnLibro()
    gestorDeLibrosIngles.mostrarInfoDeUnLibro()

}



