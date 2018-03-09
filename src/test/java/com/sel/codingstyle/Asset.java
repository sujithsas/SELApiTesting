package com.sel.codingstyle;

/**
 * Created by sasisu on 3/7/2018.
 */
public class Asset {

    public Asset() {
        super();
    }

    public Asset(long id, String assetName, String assetType, String details) {
        this.id = id;
        this.assetName = assetName;
        this.assetType = assetType;
        this.details = details;
    }


    private long id;
    private String assetName;
    private String assetType;
    private String details;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "id=" + id +
                ", assetName='" + assetName + '\'' +
                ", assetType='" + assetType + '\'' +
                ", details='" + details + '\'' +
                '}';
    }


}
