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

    public Allergen() {
    }

    public Allergen(String allergen) {
        this.allergen = allergen;
    }

    //<editor-fold desc="Getters and Setters">

    public String getAllergen() {
        return allergen;
    }

    public void setAllergen(String allergen) {
        this.allergen = allergen;
    }

    //</editor-fold>
}
