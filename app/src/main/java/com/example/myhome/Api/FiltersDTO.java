package com.example.myhome.Api;

public class FiltersDTO {

    private Long agencyId;
    private String localidad;

    private String provincia;

    private String pais;

    private int cantidadBanios;

    private int cantidadAmbientes;

    private int cantidadCuatros;

    private int precioMax;

    private int precioMin;

    private String[] propertyAmenities;

    private String propertyType;

    private String propertyStatus;

    private String propertyAge;




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

    public int getCantidadCuatros() {
        return cantidadCuatros;
    }

    public void setCantidadCuatros(int cantidadCuatros) {
        this.cantidadCuatros = cantidadCuatros;
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
}
