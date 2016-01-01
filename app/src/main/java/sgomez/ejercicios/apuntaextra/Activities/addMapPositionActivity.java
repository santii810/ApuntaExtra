package sgomez.ejercicios.apuntaextra.Activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import sgomez.ejercicios.apuntaextra.R;


public class AddMapPositionActivity extends FragmentActivity
        implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker marker;
    private String addressName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_map_position);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


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

        // Tipo de mapa
        //mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        //mMap.setTrafficEnabled(true);


        // Zoom
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.zoomTo(8));

        // Add a marker in Sydney and move the camera
        LatLng pos = new LatLng(42.6026946, -8.6526407);
        marker = mMap.addMarker(new MarkerOptions().
                position(pos).title("Caldas de Reis"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(pos));
        //Activar localizacion
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }


        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {

            @Override
            public boolean onMyLocationButtonClick() {

                LocationManager locManager =
                        (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    mostrarAvisoGpsDeshabilitado();
                }


                return false;
            }
        });

        // Pinchar mapa
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                marker.remove();

                Geocoder geocoder = new Geocoder(getBaseContext(),
                        Locale.getDefault());

                addressName = "";
                try {
                    List<Address> addresses = geocoder.getFromLocation(
                            latLng.latitude,
                            latLng.longitude, 1);
                    if (addresses.size() > 0) {
                        Address address = addresses.get(0);
                        for (int i = 0;
                             i < address.getMaxAddressLineIndex(); i++) {
                            addressName += address.getAddressLine(i) + "\n";
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                marker = mMap.addMarker(new MarkerOptions().
                        position(latLng).title(addressName));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            }
        });
    }

    private void mostrarAvisoGpsDeshabilitado() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Para obtener tu obicacion GPS debes tener el gps activado." +
                " Esta característica se activa en \"Ajustes/Personal/Ubicacion\"")
                .setTitle("GPS desactivado")
                .setCancelable(false)
                .setNeutralButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void searchButtonClicked(View view) {
        EditText addressEditText =
                (EditText) findViewById(R.id.addressEditText);
        String addressName = addressEditText.getText().toString();

        Geocoder geoCoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geoCoder.getFromLocationName(
                    addressName, 1);
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                LatLng pos = new LatLng(address.getLatitude(),
                        address.getLongitude());

                marker.remove();
                marker = mMap.addMarker(new MarkerOptions().
                        position(pos).title(addressName));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(pos));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void buttonAddMapOnClick(View view) {
        Intent backData = new Intent();
        backData.putExtra("latitude", marker.getPosition().latitude);
        backData.putExtra("longitude", marker.getPosition().longitude);
        setResult(RESULT_OK, backData);
        finish();
    }
}
