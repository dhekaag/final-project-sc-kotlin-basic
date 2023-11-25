package app

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import models.ObatModels
import constant.*
import java.text.DecimalFormat

fun homeScreen(username: String){
    ObatModels.loadMedicineFromJson() // parsing obat.json kedalam list dataObat

    topHomeScreenTitle(username = username)
    val userInput = readln()
    when(userInput){
        "1" -> resep()
        "2" -> nonResep()
    }
}

fun resep() = runBlocking{
    val dataObat = ObatModels
    val resepDokter: MutableList<String> = mutableListOf() // chart resep dokter

    println(resepTitle)
    while (true){
        print(resepBody)
        val userInput = readln()
        if (userInput.equals("selesai", ignoreCase = true)){
            print(ensureUser)
            val input = readln()
            when(input){
                "y" -> {
                    payment(resepDokter = resepDokter, nonResep = null)
                    break
                }
                "n" -> continue
            }
        }
        val obat = dataObat.findMedicine(namaObat = userInput, keluhan = null)
        if (obat != null){
            println(isLoading)
            delay(1_000)
            dataObat.showMedicine(namaObat = userInput, keluhan = null)
            resepDokter.add(userInput)
        } else{
            println(isLoading)
            delay(1_000)
            println("obat tidak tersedia")
        }
    }
}


fun nonResep() = runBlocking{
    val dataObat = ObatModels
    val dataKeluhan: MutableList<String> = mutableListOf() // chart keluhan user

    println(nonResepTitle)
    while (true){
        print(nonResepBody)
        val userInput = readln()
        if (userInput.equals("selesai", ignoreCase = true)){
            print(ensureUser)
            val input = readln()
            when(input){
                "y" -> {
                    payment(resepDokter = null, nonResep = dataKeluhan)
                    break
                }
                "n" -> continue
            }
        }
        val keluhan = dataObat.findMedicine(namaObat = null, keluhan = userInput)
        if (keluhan != null){
            println(isLoading)
            delay(1_000)
            dataObat.showMedicine(namaObat = null, keluhan = userInput)
            dataKeluhan.add(userInput)
        } else{
            println(isLoading)
            delay(1_000)
            println("obat untuk keluhan tersebut tidak tersedia")
        }
    }
}

fun payment(resepDokter: MutableList<String>?, nonResep: MutableList<String>? )= runBlocking{
    var totalHarga = 0
    val dataObat = ObatModels.dataObat

    println(isLoading)
    delay(1_500)
    println("\n======= Faktur Obat =======")
    if (resepDokter != null){
        for (obat in resepDokter){
            val hargaObat: String? = dataObat.find { it.namaObat == obat }?.harga
            val hargaInt: Int? = hargaObat!!.replace(",","").toIntOrNull()
            totalHarga += hargaInt!!// Menambahkan harga obat ke totalHarga

            println("")
            paymentBody(
                obat = obat,
                keterangan = dataObat.find { it.namaObat == obat }?.keterangan,
                harga = hargaObat
            )
        }
    }else if (nonResep != null){
        for (keluhan in nonResep){
            val hargaObat: String? = dataObat.find { it.keterangan == keluhan }?.harga
            val hargaInt: Int? = hargaObat!!.replace(",","").toIntOrNull()
            totalHarga += hargaInt!!// Menambahkan harga obat ke totalHarga

            println("")
            paymentBody(
               obat = dataObat.find { it.keterangan == keluhan }?.namaObat,
               keterangan = keluhan,
               harga = hargaObat
           )
        }
    }
    val df = DecimalFormat("#,###")
    val totalHargaStr:String = df.format(totalHarga)
    println("\nTotal Harga Rp $totalHargaStr")
    println("\n==========================")
}
