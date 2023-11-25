package models

import com.beust.klaxon.Klaxon
import data.Obat
import java.io.File

object ObatModels {
    private val klaxon = Klaxon()
    private val jsonFile = File("obat.json")
    val dataObat = mutableListOf<Obat>()
    fun loadMedicineFromJson() {
        val jsonString = jsonFile.readText()
        val obatList = klaxon.parseArray<Obat>(jsonString)
        if (obatList != null) {
            dataObat.clear()
            dataObat.addAll(obatList)
        }
    }
    fun findMedicine(namaObat: String?, keluhan: String?): Obat? {
        return dataObat.find { it.namaObat == namaObat || it.keterangan == keluhan}
    }
    fun showMedicine(namaObat: String?, keluhan: String?){
        for (obat in dataObat) {
            if (obat.namaObat == namaObat) {
                println("")
                println("""
                    Obat tersedia.
                    Nama obat: ${obat.namaObat}, 
                    keterangan: ${obat.keterangan}, 
                    harga: Rp ${obat.harga}
                """.trimIndent())
            }
            else if (obat.keterangan == keluhan){
                println("")
                println("""
                    Obat tersedia.
                    Nama obat: ${obat.namaObat}, 
                    keterangan: ${obat.keterangan}, 
                    harga: Rp ${obat.harga}
                """.trimIndent())
            }
        }
    }
}