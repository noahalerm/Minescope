package com.itb.minescope.data.models

data class OpaqueMineralSample(var id: Int, var mineralId: Int, val imageLPA: String, val imageLPNA: String,  var name: String, var coloration: String, var pleochroism: String, var abundance: Float, var otherMinerals: String, var shape: String, var cleavage: String, var reflectivity: String, var hardness: String, var anisotropy: String, var interferenceColors: String, var internalReflections: String)
