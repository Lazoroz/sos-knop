package com.example.sosknop;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Contacten {
        private StringProperty naam = new SimpleStringProperty();
        private IntegerProperty telefoonnummer = new SimpleIntegerProperty();

        public Contacten(String naam, int telefoonnummer) {
                this.naam.set(naam);
                this.telefoonnummer.set(telefoonnummer);
        }

        public StringProperty naamProperty() {
                return  naam;
        }

        public IntegerProperty telefoonProperty() {
                return telefoonnummer;
        }

        public String getNaam() {
                return naam.get();
        }

        public int getTelefoonnummer() {
                return telefoonnummer.get();
        }



}
