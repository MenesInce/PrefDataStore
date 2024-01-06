package com.menesince.datastorekullanimi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.menesince.datastorekullanimi.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ap = AppPref(this)
        // suspend olarak tanımlanan fonksiyonlar CoroutineScope içinde çağrılır
        CoroutineScope(Dispatchers.Main).launch {
            // Kayıt İşlemleri
            ap.kayitAd("Ahmet")
            ap.kayitYas(22)
            ap.kayitBoy(1.88)
            ap.kayitBekar(true)

            // Set türünde string ==> HashSet
            val liste = HashSet<String>()
            liste.add("Ali")
            liste.add("Ece")
            ap.kayitListeArkadas(liste)

            // Silme İşlemleri
            //ap.silAd()

            // Okuma İşlemleri
            val gelen_ad = ap.okuAd()
            val gelen_yas = ap.okuYas()
            val gelen_boy = ap.okuBoy()
            val gelen_bekar = ap.okuBekar()

            Log.e("Gelen Ad",gelen_ad)
            Log.e("Gelen Yas",gelen_yas.toString())
            Log.e("Gelen Boy",gelen_boy.toString())
            Log.e("Gelen Bekar",gelen_bekar.toString())

            val gelen_liste = ap.okuListeArkadas()

            if (gelen_liste != null) {
                for (a in gelen_liste) {
                    Log.e("Gelen Arkadas",a)
                }
            }

            // Uygulamanın kaç kere açıldığını görmek için önce kayıt edilen okunmalı
            // Okuduktan sonra üzerine 1 arttırarak kaçıncı olduğunu buluruz

            var gelen_sayac = ap.okuSayac() // başta kayıt işlemi yapılmadığı için 0 okuyacak
            ap.kayitSayac(++gelen_sayac)    // kayit işlemi yaparken 1 arttıracak
            binding.textViewSayac.text = "Açılış sayısı : ${gelen_sayac}"



        }
    }
}