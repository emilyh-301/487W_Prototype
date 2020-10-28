package com.hotel.model.item;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Allergen)) return false;
        Allergen allergen1 = (Allergen) o;
        return Objects.equals(allergen, allergen1.allergen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(allergen);
    }

    //<editor-fold desc="Getters and Setters">

    public String getAllergen() {
        return allergen;
    }

    public void setAllergen(String allergen) {
        this.allergen = allergen;
    }

    //</editor-fold>


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < allergen.length(); i++) {
            sb.append(i == 0? Character.toUpperCase(allergen.charAt(i)) : Character.toLowerCase(allergen.charAt(i)));
        }

        return sb.toString();
    }
}
