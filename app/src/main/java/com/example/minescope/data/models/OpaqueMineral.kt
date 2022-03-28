package com.example.minescope.data.models

data class OpaqueMineral(var id: Int, var name: String, var colors: String, var pleochroism: String, var chemicalFormula: String?, var reflectivity: String, var hardness: String, var anisotropy: String, var interferenceColors: String, var internalReflections: String, var cleavage: String?, var associatedMinerals: String?, var samples: MutableList<OpaqueMineralSample>?)
