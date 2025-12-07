package br.com.infnet.PetFriends.almoxarifado.domain;


import jakarta.persistence.Embeddable;

import java.util.Objects;


public class Localizacao {

    private double longitude;
    private double latitude;

    protected Localizacao() {
    }

    public Localizacao(double longitude, double latitude) {
        if (latitude < -90 || latitude > 90) throw new IllegalArgumentException("Latitude inválida");
        if (longitude < -180 || longitude > 180) throw new IllegalArgumentException("Longitude inválida");
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Localizacao outro = (Localizacao) obj;
        return Double.compare(outro.latitude, latitude) == 0 &&
                Double.compare(outro.longitude, longitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(longitude, latitude);
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
