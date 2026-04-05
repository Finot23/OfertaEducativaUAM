package com.example.ofertaeducativauam.data

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.ofertaeducativauam.R

data class Licenciatura(
    val id: String,
    val nombre: String,
    val division: Division,
    val duracion: String = "12 trimestres",
    val modalidad: String = "Presencial",
    val infoGeneral: Int,    
    val perfilIngreso: Int,  
    val planEstudios: Int,   
    val campoLaboral: Int,
    val icon: Int,        
    val imagenes: List<Int> = emptyList(),
    val imagenPlanEstudios: Int? = null,
    val imagenCampoLaboral: Int? = null
)

enum class Division(
    val siglas: String, 
    val nombreCompleto: String,
    val descripcion: Int, 
    val imagenes: List<Int>
) {
    CCD(
        "CCD", 
        "Ciencias de la Comunicación y Diseño", 
        R.string.ccd_desc, 
        imagenes = listOf(R.drawable.dccd_1,R.drawable.cc_3, R.drawable.tsi_programando)
    ),
    CNI(
        "CNI", 
        "Ciencias Naturales e Ingeniería", 
        R.string.cni_desc, 
        imagenes = listOf(R.drawable.cni_2, R.drawable.cni_1)
    ),
    CSH(
        "CSH", 
        "Ciencias Sociales y Humanidades", 
        R.string.csh_desc, 
        imagenes = listOf(R.drawable.csh_1)
    )
}
