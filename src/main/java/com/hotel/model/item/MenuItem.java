package com.hotel.model.item;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;

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
    //@OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    @ManyToMany
    @JoinTable(name = "itemAllergens", joinColumns =
        @JoinColumn(name = "item_id", referencedColumnName = "item_id"), inverseJoinColumns =
        @JoinColumn(name = "allergen", referencedColumnName = "allergen"))
    private Collection<Allergen> allergens;

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
     * URL for the item image
     */
    @Column(name = "image")
    private String image;

    public static final String DEFAULT_NAME = "Menu Item";
    public static final double MINIMUM_PRICE = 0.25;
    public static final String DEFAULT_DESCRIPTION = "Item Description.";

    public MenuItem() {
    }

    public MenuItem(int id, String name, Collection<Allergen> allergens, BigDecimal price, String description, String image) {
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

    public Collection<Allergen> getAllergens() {
        return allergens;
    }

    public void setAllergens(Collection<Allergen> allergens) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    //</editor-fold>
}
