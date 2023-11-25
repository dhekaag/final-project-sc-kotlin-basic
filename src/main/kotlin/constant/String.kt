package constant

const val isLoading: String = "Loading..."

fun topHomeScreenTitle(username: String?) {
    print("""
                  ========================== Selamat datang di MyApotek =========================
                    Hallo, $username
                    
                    Beli obat dengan mudah dan cepat di MyApotek!!!                             
                     > Jika anda sudah mempunyai resep dokter, silahkan masuk ke menu resep      
                     > Jika anda belum mempunyai resep dokter, silahkan masuk ke menu nonResep  
                  
               Menu : 
                  1. Resep
                  2. Non-resep
               
               Masukan pilihan anda : """)
}

const val resepTitle: String = "\nMasukkan nama obat dari resep dokter dan ketik 'selesai' jika sudah"

const val resepBody: String = "\nNama obat: "

const val ensureUser: String = "\nApakah anda ingin melakukan pembayaran (y/n) : "

const val nonResepTitle: String = "\nMasukkan keluhan anda dan ketik 'selesai' jika sudah"

const val nonResepBody: String = "\nKeluhan: "

fun paymentBody(obat: String?, keterangan: String?, harga: String?) {
    println("""
                Obat : $obat
                Keterangan : $keterangan
                Harga : Rp $harga
            """.trimIndent())
}