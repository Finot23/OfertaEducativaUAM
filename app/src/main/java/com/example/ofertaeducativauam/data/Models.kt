package com.example.ofertaeducativauam.data

data class Licenciatura(
    val id: String,
    val nombre: String,
    val division: Division,
    val duracion: String = "12 trimestres (4 años)",
    val modalidad: String = "Presencial",
    val perfilIngreso: String,
    val planEstudios: String,
    val campoLaboral: String
)

enum class Division(val siglas: String, val nombreCompleto: String) {
    CCD("CCD", "Ciencias de la Comunicación y Diseño"),
    CNI("CNI", "Ciencias Naturales e Ingeniería"),
    CSH("CSH", "Ciencias Sociales y Humanidades")
}

// Repositorio Offline
object UamRepository {
    val licenciaturas = listOf(
        Licenciatura(
            id = "LCC", nombre = "Ciencias de la Comunicación", division = Division.CCD,
            perfilIngreso = "Interés en periodismo, comunicación social y medios.",
            planEstudios = "Tronco general, producción audiovisual, teoría de la comunicación...",
            campoLaboral = "Medios de comunicación, agencias de publicidad, empresas productoras."
        ),
        Licenciatura(
            id = "LTSI", nombre = "Tecnologías y Sistemas de Información", division = Division.CCD,
            perfilIngreso = "Gusto por la programación, bases de datos y desarrollo de software.",
            planEstudios = "Programación, matemáticas, interacción humano-computadora...",
            campoLaboral = "Desarrollo de software, análisis de datos, gestión de TI."
        ),
        Licenciatura(
            id = "LMD", nombre = "Diseño", division = Division.CCD,
            perfilIngreso = "Creatividad, interés en diseño gráfico y de productos.",
            planEstudios = "Teoría del diseño, ergonomía, herramientas digitales...",
            campoLaboral = "Estudios de diseño, agencias de marketing, freelance."
        )
        // Nota: Aquí puedes agregar el resto de las licenciaturas de CNI y CSH siguiendo este formato.
    )
}
