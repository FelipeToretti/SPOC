package com.example.felipe.spoc;


import android.os.Bundle;


import android.os.CountDownTimer;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsFragment extends SupportMapFragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private int contadorWP=0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(this);


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        float zoomInicial = 14;

        mMap.getUiSettings().setZoomControlsEnabled(true);



        // Add a marker in Sydney and move the camera
        LatLng crici = new LatLng(-28.680006, -49.369911);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(crici, zoomInicial));



    }

    public void startCountDownTimer(){

        LatLng latlng0 = new LatLng(-28.679778, -49.370135);
        LatLng latlng1 = new LatLng(-28.679509, -49.371631);
        LatLng latlng2 = new LatLng(-28.679354, -49.372784);
        LatLng latlng3 = new LatLng(-28.679533, -49.373980);
        LatLng latlng4 = new LatLng(-28.679909, -49.375043);
        LatLng latlng5 = new LatLng(-28.680304, -49.376094);
        LatLng latlng6 = new LatLng(-28.680869, -49.377607);
        LatLng latlng7 = new LatLng(-28.681293, -49.379324);
        LatLng latlng8 = new LatLng(-28.681500, -49.380708);
        LatLng latlng9 = new LatLng(-28.681556, -49.382907);

        final LatLng[] arrayLatLng = new LatLng[10];
        arrayLatLng[0] = latlng0;
        arrayLatLng[1] = latlng1;
        arrayLatLng[2] = latlng2;
        arrayLatLng[3] = latlng3;
        arrayLatLng[4] = latlng4;
        arrayLatLng[5] = latlng5;
        arrayLatLng[6] = latlng6;
        arrayLatLng[7] = latlng7;
        arrayLatLng[8] = latlng8;
        arrayLatLng[9] = latlng9;

        int intervalo = 3000;

        CountDownTimer cdt = new CountDownTimer((intervalo * 10)+500, intervalo) {
            int markerNo = 0;
            @Override
            public void onTick(long millisUntilFinished) {

                if(markerNo <= 9){
                    mMap.clear();
                    mMap.addMarker(new MarkerOptions().position(arrayLatLng[markerNo]).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_busandando)));
                    markerNo++;
                }
            }

            @Override
            public void onFinish(){

            }
        }.start();

    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    public void mostrarToast(String msg){
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
