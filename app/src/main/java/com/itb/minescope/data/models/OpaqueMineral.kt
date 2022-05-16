package com.itb.minescope.data.models

data class OpaqueMineral(var idMineral: Int, var name: String, var color: String, var pleochroism: String, var chemicalFormula: String?, var reflectance: String, var polishingHardness: String, var anisotropism: String, var interference_colors: String, var internalReflections: String, var polishingCleavage: String?, var associatedMinerals: String?, var samples: MutableList<OpaqueMineralSample>?)
