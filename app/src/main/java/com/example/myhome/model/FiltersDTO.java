package com.example.myhome.model;


import java.io.Serializable;

public class FiltersDTO implements Serializable {

    private Long userId;
    private Long agencyId;
    private String localidad;
    private String provincia;
    private String pais;
    private int cantidadBanios;
    private int cantidadAmbientes;
    private int cantidadCuartos;
    private int precioMax;
    private int precioMin;
    private String[] propertyAmenities;
    private String propertyType;
    private String propertyStatus;
    private String propertyAge;
    private boolean isFavorite;
    private double userLatitude;
    private double userLongitude;


    public Long getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Long agencyId) {
        this.agencyId = agencyId;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getCantidadBanios() {
        return cantidadBanios;
    }

    public void setCantidadBanios(int cantidadBanios) {
        this.cantidadBanios = cantidadBanios;
    }

    public int getCantidadAmbientes() {
        return cantidadAmbientes;
    }

    public void setCantidadAmbientes(int cantidadAmbientes) {
        this.cantidadAmbientes = cantidadAmbientes;
    }

    public int getCantidadCuartos() {
        return cantidadCuartos;
    }

    public void setCantidadCuartos(int cantidadCuartos) {
        this.cantidadCuartos = cantidadCuartos;
    }

    public int getPrecioMax() {
        return precioMax;
    }

    public void setPrecioMax(int precioMax) {
        this.precioMax = precioMax;
    }

    public int getPrecioMin() {
        return precioMin;
    }

    public void setPrecioMin(int precioMin) {
        this.precioMin = precioMin;
    }

    public String[] getPropertyAmenities() {
        return propertyAmenities;
    }

    public void setPropertyAmenities(String[] propertyAmenities) {
        this.propertyAmenities = propertyAmenities;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getPropertyStatus() {
        return propertyStatus;
    }

    public void setPropertyStatus(String propertyStatus) {
        this.propertyStatus = propertyStatus;
    }

    public String getPropertyAge() {
        return propertyAge;
    }

    public void setPropertyAge(String propertyAge) {
        this.propertyAge = propertyAge;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public boolean getIsFavorite() {
        return isFavorite;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double getUserLatitude() {
        return userLatitude;
    }

    public void setUserLatitude(double userLatitude) {
        this.userLatitude = userLatitude;
    }

    public double getUserLongitude() {
        return userLongitude;
    }

    public void setUserLongitude(double userLongitude) {
        this.userLongitude = userLongitude;
    }


}
