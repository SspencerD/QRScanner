package com.recneps.scannerqr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import com.recneps.scannerqr.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnScanner.setOnClickListener{ initScanner()}
    }

    private fun initScanner(){
       val integrator =  IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("Coloque el codigo QR en el centro del recuadro para poder realizar su pago")
        integrator.setBeepEnabled(false)
        integrator.initiateScan()
        integrator.setBarcodeImageEnabled(true);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)
        if( result != null){
            if(result.contents == null){
                Toast.makeText(this,"Cancel",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Scanner Code! : ${result.contents}",Toast.LENGTH_LONG).show()
                println("Resultado : ${result.contents}")

            }

        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}