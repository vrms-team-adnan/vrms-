package com.vrms.domain;

/**
 * Represents a customer who rents vehicles.
 */
public class Customer {

    private final String id;
    private final String name;
    private final int age;
    private final boolean hasSpecialLicense;

    /**
     * Creates a new customer.
     *
     * @param   id                   the customer's unique id
     * @param   name                 the customer's name
     * @param   age                  the customer's age
     * @param   hasSpecialLicense    whether the customer holds a special
     *                               license (e.g. for trucks)
     */
    public Customer(String id, String name, int age, boolean hasSpecialLicense) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.hasSpecialLicense = hasSpecialLicense;
    }

    /**
     * Returns the customer's id.
     *
     * @return  the id
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the customer's name.
     *
     * @return  the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the customer's age.
     *
     * @return  the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Checks whether the customer holds a special license.
     *
     * @return  true if the customer has a special license
     */
    public boolean hasSpecialLicense() {
        return hasSpecialLicense;
    }
}