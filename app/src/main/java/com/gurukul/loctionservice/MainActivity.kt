package com.gurukul.loctionservice

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var status = ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)

        if(status == PackageManager.PERMISSION_GRANTED){
            accessLoction()
        }else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),123)
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
            accessLoction()
        }else{
            System.exit(0)
        }
    }

    @SuppressLint("MissingPermission")
    fun accessLoction(){
        var lManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        lManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000L,200F, object : LocationListener {
            @SuppressLint("SetTextI18n")
            override fun onLocationChanged(location: Location?) {
                if(location != null){
                    longi.setText("Longitude is ${location.longitude}")
                    lati.setText("Latitude is ${location.latitude}")
                }
            }

            override fun onProviderEnabled(provider: String?) {

            }

            override fun onProviderDisabled(provider: String?) {

            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

            }
        })
    }
}
