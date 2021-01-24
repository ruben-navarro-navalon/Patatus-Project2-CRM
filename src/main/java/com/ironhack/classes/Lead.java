package com.ironhack.classes;

public class Lead {
    // Properties
    private int id;
    private String name;
    private String phoneNumber;
    private String email;
    private String companyName;

    // To generate autoincrementID:
    private static int idGenerator = 0;

    // Constructor HAY QUE PASARLE ID O NO?
    public Lead(int id, String name, String phoneNumber, String email, String companyName) {
        setId(id);
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setCompanyName(companyName);
    }

    // Methods
    public Opportunity convertToOpportunity(Lead lead) {
        Contact contact = new Contact(getName(), getPhoneNumber(),getEmail(),getCompanyName());
        // meto aqui el scanner para la info, o creamos una funcion para recoger la info y la invoco aqui
        Opportunity opportunity = new Opportunity();
        return opportunity;
    }

    /** Remove. duda */
//    public void remove() throws Throwable {
//        this = null;
//    }

    //Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = generateId();
    }

    public static int generateId()  {
        return idGenerator++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}

