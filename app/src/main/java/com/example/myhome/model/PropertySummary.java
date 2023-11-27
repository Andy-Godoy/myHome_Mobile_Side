package com.example.myhome.model;

public class PropertySummary {
    private Long propertyId;
    private Long agencyId;
    private Integer propertyPrice;
    private Integer propertyDimension;
    private Integer propertyBedroomQuantity;
    private String propertyDescription;
    private String propertyAddress;
    private String propertyNeighbourhood;
    private String propertyCity;

    public PropertySummary(Long propertyId, Long agencyId, Integer propertyPrice, Integer propertyDimension, Integer propertyBedroomQuantity, String propertyDescription, String propertyAddress, String propertyNeighbourhood, String propertyCity) {
        this.propertyId = propertyId;
        this.agencyId = agencyId;
        this.propertyPrice = propertyPrice;
        this.propertyDimension = propertyDimension;
        this.propertyBedroomQuantity = propertyBedroomQuantity;
        this.propertyDescription = propertyDescription;
        this.propertyAddress = propertyAddress;
        this.propertyNeighbourhood = propertyNeighbourhood;
        this.propertyCity = propertyCity;
    }

    public PropertySummary(PropertySummary ps) {
        this.propertyId = ps.getPropertyId();
        this.agencyId = ps.getAgencyId();
        this.propertyPrice = ps.getPropertyPrice();
        this.propertyDimension = ps.getPropertyDimension();
        this.propertyBedroomQuantity = ps.getPropertyBedroomQuantity();
        this.propertyDescription = ps.getPropertyDescription();
        this.propertyAddress = ps.getPropertyAddress();
        this.propertyNeighbourhood = ps.getPropertyNeighbourhood();
        this.propertyCity = ps.getPropertyCity();
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public Long getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Long agencyId) {
        this.agencyId = agencyId;
    }

    public Integer getPropertyPrice() {
        return propertyPrice;
    }

    public void setPropertyPrice(Integer propertyPrice) {
        this.propertyPrice = propertyPrice;
    }

    public Integer getPropertyDimension() {
        return propertyDimension;
    }

    public void setPropertyDimension(Integer propertyDimension) {
        this.propertyDimension = propertyDimension;
    }

    public Integer getPropertyBedroomQuantity() {
        return propertyBedroomQuantity;
    }

    public void setPropertyBedroomQuantity(Integer propertyBedroomQuantity) {
        this.propertyBedroomQuantity = propertyBedroomQuantity;
    }

    public String getPropertyDescription() {
        return propertyDescription;
    }

    public void setPropertyDescription(String propertyDescription) {
        this.propertyDescription = propertyDescription;
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public String getPropertyNeighbourhood() {
        return propertyNeighbourhood;
    }

    public void setPropertyNeighbourhood(String propertyNeighbourhood) {
        this.propertyNeighbourhood = propertyNeighbourhood;
    }

    public String getPropertyCity() {
        return propertyCity;
    }

    public void setPropertyCity(String propertyCity) {
        this.propertyCity = propertyCity;
    }

    private String[] propertyImages;

    public String[] getPropertyImages() {
        return propertyImages;
    }

    public void setPropertyImages(String[] propertyImages) {
        this.propertyImages = propertyImages;
}
}