package com.hotel.model.item;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
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
    private Long id;

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
    private Set<Allergen> allergens;

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

    public MenuItem(long id, String name, Set<Allergen> allergens, BigDecimal price, String description, String image) {
        this.id = id;
        this.name = name;
        this.allergens = allergens;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public MenuItem(long id, String name, Set<Allergen> allergens, double price, String description, String image) {
        this.id = id;
        this.name = name;
        this.allergens = allergens;
        this.price = new BigDecimal(price);
        this.description = description;
        this.image = image;
    }

    public MenuItem(String name, Set<Allergen> allergens, BigDecimal price, String description, String image) {
        this.name = name;
        this.allergens = allergens;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public MenuItem(String name, Set<Allergen> allergens, double price, String description, String image) {
        this.name = name;
        this.allergens = allergens;
        this.price = new BigDecimal(price);
        this.description = description;
        this.image = image;
    }

    public void addAllergen(Allergen a) {
        allergens.add(a);
    }

    public void removeAllergen(Allergen a) {
        allergens.remove(a);
    }

    public void clearAllergens() {
        allergens.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItem)) return false;
        MenuItem menuItem = (MenuItem) o;
        return id.equals(menuItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    //<editor-fold desc="Getters and Setters">
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Allergen> getAllergens() {
        return allergens;
    }

    public void setAllergens(Set<Allergen> allergens) {
        this.allergens = allergens;
    }

    public void setAllergens(Collection<Allergen> allergens) {
        this.allergens = new HashSet<>(allergens);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPrice(double price) {
        this.price = new BigDecimal(price);
    }

    public String getDescription() {
        return description;
    }

    public String getDescriptionExcerpt() { return description.substring(0, description.length() < 40 ? description.length() : 40);}

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

    public String listAllergens() {
        StringBuilder s = new StringBuilder();
        for(Allergen a : allergens) {
            s.append(a.toString()).append(", ");
        }
        s.deleteCharAt(s.length() - 1).deleteCharAt(s.length() - 1);

        return s.toString();
    }
}
