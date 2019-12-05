package com.example.crudkontak;

import android.os.Parcel;
import android.os.Parcelable;

public class Kontak implements Parcelable {

    private String idkontak, namakontak, nomorkontak;

    public Kontak(String idkontak, String namakontak, String nomorkontak) {
        this.idkontak = idkontak;
        this.namakontak = namakontak;
        this.nomorkontak = nomorkontak;
    }

    public String getIdkontak() {
        return idkontak;
    }

    public void setIdkontak(String idkontak) {
        this.idkontak = idkontak;
    }

    public String getNamakontak() {
        return namakontak;
    }

    public void setNamakontak(String namakontak) {
        this.namakontak = namakontak;
    }

    public String getNomorkontak() {
        return nomorkontak;
    }

    public void setNomorkontak(String nomorkontak) {
        this.nomorkontak = nomorkontak;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.idkontak);
        dest.writeString(this.namakontak);
        dest.writeString(this.nomorkontak);
    }

    protected Kontak(Parcel in) {
        this.idkontak = in.readString();
        this.namakontak = in.readString();
        this.nomorkontak = in.readString();
    }

    public static final Creator<Kontak> CREATOR = new Creator<Kontak>() {
        @Override
        public Kontak createFromParcel(Parcel source) {
            return new Kontak(source);
        }

        @Override
        public Kontak[] newArray(int size) {
            return new Kontak[size];
        }
    };
}
