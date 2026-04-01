package com.example.ofertaeducativauam.data

import com.example.ofertaeducativauam.R

object UamRepository {
    val licenciaturas = listOf(
        // --- CCD: CIENCIAS DE LA COMUNICACIÓN Y DISEÑO (3) ---
        Licenciatura(
            id = "LTSI", 
            nombre = "Tecnologías y Sistemas de Información", 
            division = Division.CCD,
            infoGeneral = R.string.ltsi_info_gral,
            perfilIngreso = R.string.ltsi_perfil,
            planEstudios = R.string.ltsi_plan,
            campoLaboral = R.string.ltsi_campo,
            imagenes = listOf(R.drawable.tsi_aula, R.drawable.tsi_salon, R.drawable.tsi_programando),
            imagenPlanEstudios = R.drawable.tsi_plan,
            imagenCampoLaboral = R.drawable.tsi_programando
        ),
        Licenciatura(
            id = "LMD", 
            nombre = "Diseño", 
            division = Division.CCD,
            infoGeneral = R.string.lmd_info_gral,
            perfilIngreso = R.string.lmd_perfil,
            planEstudios = R.string.lmd_plan,
            campoLaboral = R.string.lmd_campo,
            imagenes = listOf(R.drawable.dis_1, R.drawable.dis_2, R.drawable.dis_3),
            imagenPlanEstudios = R.drawable.dis_plan1,
            imagenCampoLaboral = R.drawable.dis_4
        ),
        Licenciatura(
            id = "LCC", 
            nombre = "Ciencias de la Comunicación", 
            division = Division.CCD,
            infoGeneral = R.string.lcc_info_gral,
            perfilIngreso = R.string.lcc_perfil,
            planEstudios = R.string.lcc_plan,
            campoLaboral = R.string.lcc_campo,
            imagenes = listOf(R.drawable.cc_1, R.drawable.cc_2, R.drawable.cc_3),
            imagenPlanEstudios = R.drawable.cc_plan,
            imagenCampoLaboral = R.drawable.cc_4
        ),

        // --- CSH: CIENCIAS SOCIALES Y HUMANIDADES (4) ---
        Licenciatura(
            id = "LADM", 
            nombre = "Administración", 
            division = Division.CSH,
            infoGeneral = R.string.ladm_info_gral,
            perfilIngreso = R.string.ladm_perfil,
            planEstudios = R.string.ladm_plan,
            campoLaboral = R.string.ladm_campo
        ),
        Licenciatura(
            id = "LDER", 
            nombre = "Derecho", 
            division = Division.CSH,
            infoGeneral = R.string.lder_info_gral,
            perfilIngreso = R.string.lder_perfil,
            planEstudios = R.string.lder_plan,
            campoLaboral = R.string.lder_campo
        ),
        Licenciatura(
            id = "LEST", 
            nombre = "Estudios Socioterritoriales", 
            division = Division.CSH,
            infoGeneral = R.string.lest_info_gral,
            perfilIngreso = R.string.lest_perfil,
            planEstudios = R.string.lest_plan,
            campoLaboral = R.string.lest_campo
        ),
        Licenciatura(
            id = "LHIS", 
            nombre = "Humanidades", 
            division = Division.CSH,
            infoGeneral = R.string.lhis_info_gral,
            perfilIngreso = R.string.lhis_perfil,
            planEstudios = R.string.lhis_plan,
            campoLaboral = R.string.lhis_campo
        ),

        // --- CNI: CIENCIAS NATURALES E INGENIERÍA (4) ---
        Licenciatura(
            id = "LIA", 
            nombre = "Ingeniería Biológica", 
            division = Division.CNI,
            infoGeneral = R.string.lia_info_gral,
            perfilIngreso = R.string.lia_perfil,
            planEstudios = R.string.lia_plan,
            campoLaboral = R.string.lia_campo
        ),
        Licenciatura(
            id = "LBM", 
            nombre = "Biología Molecular", 
            division = Division.CNI,
            infoGeneral = R.string.lbm_info_gral,
            perfilIngreso = R.string.lbm_perfil,
            planEstudios = R.string.lbm_plan,
            campoLaboral = R.string.lbm_campo
        ),
        Licenciatura(
            id = "LIR", 
            nombre = "Ingeniería en Computación", 
            division = Division.CNI,
            infoGeneral = R.string.lir_info_gral,
            perfilIngreso = R.string.lir_perfil,
            planEstudios = R.string.lir_plan,
            campoLaboral = R.string.lir_campo
        ),
        Licenciatura(
            id = "LMA", 
            nombre = "Matemáticas Aplicadas", 
            division = Division.CNI,
            infoGeneral = R.string.lma_info_gral,
            perfilIngreso = R.string.lma_perfil,
            planEstudios = R.string.lma_plan,
            campoLaboral = R.string.lma_campo
        )
    )
}
