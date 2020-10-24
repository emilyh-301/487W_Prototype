package com.hotel.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "menuItems")
public class MenuItem {

    /**
     * A unique identifier for the item
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_ID_SEQ")
    @SequenceGenerator(name = "ITEM_ID_SEQ", sequenceName = "ITEM_ID_SEQ", allocationSize = 250)
    @Column(name = "item_id")
    private int id;

    /**
     * The item's name
     */
    @Column(name = "name")
    private String name;

    /**
     * The item's allergens
     */
    @Column(name = "allergens")
    private String allergens;

    /**
     * The item's price
     */
    @Column(name = "price")
    private BigDecimal price;

    /**
     * A description of the item
     */
    @Column(name = "description")
    private String description;

    /**
     * A BLOB image for the item
     */
    @Lob
    @Column(name = "image")
    private byte[] image;

    public static final String DEFAULT_NAME = "Menu Item";
    public static final double MINIMUM_PRICE = 0.25;
    public static final String DEFAULT_DESCRIPTION = "Item Description.";

    public MenuItem() {
    }

    public MenuItem(int id, String name, String allergens, BigDecimal price, String description, byte[] image) {
        this.id = id;
        this.name = name;
        this.allergens = allergens;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    //<editor-fold desc="Getters and Setters">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAllergens() {
        return allergens;
    }

    public void setAllergens(String allergens) {
        this.allergens = allergens;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    //</editor-fold>
}
