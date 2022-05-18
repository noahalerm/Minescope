package com.itb.minescope.data.models

data class OpaqueMineral(var id: Int, var nom: String, var colors: String, var pleocroisme: String, var formula: String?, var reflectivitat: String, var resistencia_polit: String, var anisotropia: String, var colors_interferencia: String, var reflexions_internes: String, var exfoliacio_polit: String?, var minerals_associats: String?, var opaqueSamplesList: MutableList<OpaqueMineralSample>?)
