package com.example.myhome.model;

public class Properties {
    private Long propertyId;
    private Long agencyId;
    private String propertyType;
    private String propertyStatus;
    private Integer propertyPrice;
    private Integer propertyExpenses;
    private Integer propertyRoomQuantity;
    private Integer propertyBedroomQuantity;
    private Integer propertyBathroomQuantity;
    private Integer propertyGarageQuantity;
    private Boolean propertyHasGarage;
    private Boolean propertyHasBalcony;
    private Boolean propertyHasStorage;
    private String propertyPosition;
    private String propertyOrientation;
    private String propertyAge;
    private String[] propertyAmenities;
    private String[] propertyImages;
    private String propertyDescription;
    private Integer propertyCoveredM2;
    private Integer propertySemiCoveredM2;
    private Integer propertyUncoveredM2;
    private Address propertyAddress;
    private boolean propertyHasTerrace;
    private boolean propertyIsFavorite;
    private String agencyImage;

    public String getAgencyImage() {
        return agencyImage;
    }

    public void setAgencyImage(String agencyImage) {
        this.agencyImage = agencyImage;
    }

    public boolean getPropertyIsFavorite() {
        return propertyIsFavorite;
    }

    public void setPropertyIsFavorite(boolean propertyIsFavorite) {
        this.propertyIsFavorite = propertyIsFavorite;
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

    public Integer getPropertyPrice() {
        return propertyPrice;
    }

    public void setPropertyPrice(Integer propertyPrice) {
        this.propertyPrice = propertyPrice;
    }

    public Integer getPropertyExpenses() {
        return propertyExpenses;
    }

    public void setPropertyExpenses(Integer propertyExpenses) {
        this.propertyExpenses = propertyExpenses;
    }

    public Integer getPropertyRoomQuantity() {
        return propertyRoomQuantity;
    }

    public void setPropertyRoomQuantity(Integer propertyRoomQuantity) {
        this.propertyRoomQuantity = propertyRoomQuantity;
    }

    public Integer getPropertyBedroomQuantity() {
        return propertyBedroomQuantity;
    }

    public void setPropertyBedroomQuantity(Integer propertyBedroomQuantity) {
        this.propertyBedroomQuantity = propertyBedroomQuantity;
    }

    public Integer getPropertyBathroomQuantity() {
        return propertyBathroomQuantity;
    }

    public void setPropertyBathroomQuantity(Integer propertyBathroomQuantity) {
        this.propertyBathroomQuantity = propertyBathroomQuantity;
    }

    public Integer getPropertyGarageQuantity() {
        return propertyGarageQuantity;
    }

    public void setPropertyGarageQuantity(Integer propertyGarageQuantity) {
        this.propertyGarageQuantity = propertyGarageQuantity;
    }

    public Boolean getPropertyHasGarage() {
        return propertyHasGarage;
    }

    public void setPropertyHasGarage(Boolean propertyHasGarage) {
        this.propertyHasGarage = propertyHasGarage;
    }

    public Boolean getPropertyHasBalcony() {
        return propertyHasBalcony;
    }

    public void setPropertyHasBalcony(Boolean propertyHasBalcony) {
        this.propertyHasBalcony = propertyHasBalcony;
    }

    public Boolean getPropertyHasStorage() {
        return propertyHasStorage;
    }

    public void setPropertyHasStorage(Boolean propertyHasStorage) {
        this.propertyHasStorage = propertyHasStorage;
    }

    public String getPropertyPosition() {
        return propertyPosition;
    }

    public void setPropertyPosition(String propertyPosition) {
        this.propertyPosition = propertyPosition;
    }

    public String getPropertyOrientation() {
        return propertyOrientation;
    }

    public void setPropertyOrientation(String propertyOrientation) {
        this.propertyOrientation = propertyOrientation;
    }

    public String getPropertyAge() {
        return propertyAge;
    }

    public void setPropertyAge(String propertyAge) {
        this.propertyAge = propertyAge;
    }

    public String[] getPropertyAmenities() {
        return propertyAmenities;
    }

    public void setPropertyAmenities(String[] propertyAmenities) {
        this.propertyAmenities = propertyAmenities;
    }

    public String getPropertyDescription() {
        return propertyDescription;
    }

    public void setPropertyDescription(String propertyDescription) {
        this.propertyDescription = propertyDescription;
    }

    public Integer getPropertyCoveredM2() {
        return propertyCoveredM2;
    }

    public void setPropertyCoveredM2(Integer propertyCoveredM2) {
        this.propertyCoveredM2 = propertyCoveredM2;
    }

    public Integer getPropertySemiCoveredM2() {
        return propertySemiCoveredM2;
    }

    public void setPropertySemiCoveredM2(Integer propertySemiCoveredM2) {
        this.propertySemiCoveredM2 = propertySemiCoveredM2;
    }

    public Integer getPropertyUncoveredM2() {
        return propertyUncoveredM2;
    }

    public void setPropertyUncoveredM2(Integer propertyUncoveredM2) {
        this.propertyUncoveredM2 = propertyUncoveredM2;
    }

    public Address getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(Address propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public void setPropertyHasTerrace(boolean propertyHasTerrace) {
        this.propertyHasTerrace = propertyHasTerrace;
    }

    public boolean getPropertyHasTerrace() {
       return  this.propertyHasTerrace;
    }

    public void setPropertyImages(String[] propertyImages){
        this.propertyImages = propertyImages;
    }

    public String[] getPropertyImages() { return propertyImages; }


}

