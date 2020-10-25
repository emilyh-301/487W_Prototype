package com.hotel.model.item;

import javax.persistence.*;

@Entity
@Table(name = "Allergens")
public class Allergen {

    /**
     * The name of the allergen
     */
    @Id
    @Column(name = "allergen")
    private String allergen;

    /**
     * The item this allergen belongs to
     */
    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private MenuItem item;

    public Allergen() {
    }

    public Allergen(String allergen, MenuItem item) {
        this.allergen = allergen;
        this.item = item;
    }

    //<editor-fold desc="Getters and Setters">

    public String getAllergen() {
        return allergen;
    }

    public void setAllergen(String allergen) {
        this.allergen = allergen;
    }

    public MenuItem getItem() {
        return item;
    }

    public void setItem(MenuItem item) {
        this.item = item;
    }
    //</editor-fold>
}
