package com.example.ofertaeducativauam.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import com.example.ofertaeducativauam.R

object UamRepository {
    val licenciaturas = listOf(
        //CCD
        Licenciatura(
            id = "LTSI", nombre = "Tecnologías y Sistemas de Información", division = Division.CCD,
            infoGeneral = R.string.ltsi_info_gral, perfilIngreso = R.string.ltsi_perfil,
            planEstudios = R.string.ltsi_plan, campoLaboral = R.string.ltsi_campo,
            icon = R.drawable.tsi,
            imagenes = listOf(R.drawable.tsi_aula, R.drawable.tsi_salon, R.drawable.tsi_programando),
            imagenPlanEstudios = R.drawable.tsi_plan, imagenCampoLaboral = R.drawable.tsi_programando
        ),
        Licenciatura(
            id = "LMD", nombre = "Diseño", division = Division.CCD,
            infoGeneral = R.string.lmd_info_gral, perfilIngreso = R.string.lmd_perfil,
            planEstudios = R.string.lmd_plan, campoLaboral = R.string.lmd_campo,
            icon = R.drawable.dis,
            imagenes = listOf(R.drawable.dis_1, R.drawable.dis_2, R.drawable.dis_3),
            imagenPlanEstudios = R.drawable.dis_plan1, imagenCampoLaboral = R.drawable.dis_4
        ),
        Licenciatura(
            id = "LCC", nombre = "Ciencias de la Comunicación", division = Division.CCD,
            infoGeneral = R.string.lcc_info_gral, perfilIngreso = R.string.lcc_perfil,
            planEstudios = R.string.lcc_plan, campoLaboral = R.string.lcc_campo,
            icon = R.drawable.cc,
            imagenes = listOf(R.drawable.cc_1, R.drawable.cc_2, R.drawable.cc_3),
            imagenPlanEstudios = R.drawable.cc_plan, imagenCampoLaboral = R.drawable.cc_4
        ),

        //  CSH
        Licenciatura(
            id = "LADM", nombre = "Administración", division = Division.CSH,
            infoGeneral = R.string.ladm_info_gral, perfilIngreso = R.string.ladm_perfil,
            planEstudios = R.string.ladm_plan, campoLaboral = R.string.ladm_campo,
            icon = R.drawable.ad,
            imagenes = listOf(R.drawable.ad_1, R.drawable.ad_2, R.drawable.ad_3),
            imagenPlanEstudios = R.drawable.ad_plan, imagenCampoLaboral = R.drawable.ad_4
        ),
        Licenciatura(
            id = "LDER", nombre = "Derecho", division = Division.CSH,
            infoGeneral = R.string.lder_info_gral, perfilIngreso = R.string.lder_perfil,
            planEstudios = R.string.lder_plan, campoLaboral = R.string.lder_campo,
            icon = R.drawable.der,
            imagenes = listOf(R.drawable.der_1, R.drawable.der_2, R.drawable.der_3),
            imagenPlanEstudios = R.drawable.der_plan, imagenCampoLaboral = R.drawable.der_4
        ),
        Licenciatura(
            id = "LEST", nombre = "Estudios Socioterritoriales", division = Division.CSH,
            infoGeneral = R.string.lest_info_gral, perfilIngreso = R.string.lest_perfil,
            planEstudios = R.string.lest_plan, campoLaboral = R.string.lest_campo,
            icon = R.drawable.st,
            imagenes = listOf(R.drawable.st_1, R.drawable.st_2, R.drawable.st_3),
            imagenPlanEstudios = R.drawable.st_plan, imagenCampoLaboral = R.drawable.st_4
        ),
        Licenciatura(
            id = "LHIS", nombre = "Humanidades", division = Division.CSH,
            infoGeneral = R.string.lhis_info_gral, perfilIngreso = R.string.lhis_perfil,
            planEstudios = R.string.lhis_plan, campoLaboral = R.string.lhis_campo,
            icon = R.drawable.hu,
            imagenes = listOf(R.drawable.hu_1, R.drawable.hu_2, R.drawable.hu_3, R.drawable.hu_5),
            imagenPlanEstudios = R.drawable.hu_plan, imagenCampoLaboral = R.drawable.hu_4
        ),

        //CNI
        Licenciatura(
            id = "LIA", nombre = "Ingeniería Biológica", division = Division.CNI,
            infoGeneral = R.string.lia_info_gral, perfilIngreso = R.string.lia_perfil,
            planEstudios = R.string.lia_plan, campoLaboral = R.string.lia_campo,
            icon = R.drawable.ib,
            imagenes = listOf(R.drawable.ib_1, R.drawable.ib_2, R.drawable.ib_4),
            imagenPlanEstudios = R.drawable.ib_plan, imagenCampoLaboral = R.drawable.ib_3
        ),
        Licenciatura(
            id = "LBM", nombre = "Biología Molecular", division = Division.CNI,
            infoGeneral = R.string.lbm_info_gral, perfilIngreso = R.string.lbm_perfil,
            planEstudios = R.string.lbm_plan, campoLaboral = R.string.lbm_campo,
            icon = R.drawable.im,
            imagenes = listOf(R.drawable.im_1, R.drawable.im_2, R.drawable.im_3),
            imagenPlanEstudios = R.drawable.im_plan, imagenCampoLaboral = R.drawable.im_4
        ),
        Licenciatura(
            id = "LIR", nombre = "Ingeniería en Computación", division = Division.CNI,
            infoGeneral = R.string.lir_info_gral, perfilIngreso = R.string.lir_perfil,
            planEstudios = R.string.lir_plan, campoLaboral = R.string.lir_campo,
            icon = R.drawable.ic,
            imagenes = listOf(R.drawable.ic_1, R.drawable.ic_2, R.drawable.ic_3),
            imagenPlanEstudios = R.drawable.ic_plan, imagenCampoLaboral = R.drawable.ic_4
        ),
        Licenciatura(
            id = "LMA", nombre = "Matemáticas Aplicadas", division = Division.CNI,
            infoGeneral = R.string.lma_info_gral, perfilIngreso = R.string.lma_perfil,
            planEstudios = R.string.lma_plan, campoLaboral = R.string.lma_campo,
            icon = R.drawable.ma,
            imagenes = listOf(R.drawable.ma_1, R.drawable.ma_2, R.drawable.ma_3),
            imagenPlanEstudios = R.drawable.ma_plan, imagenCampoLaboral = R.drawable.ma_4
        )
    )
}
