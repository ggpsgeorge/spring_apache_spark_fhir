package com.ggpsgeorge.fullstack_hospital_system_fhir.Models.ResourceType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ggpsgeorge.fullstack_hospital_system_fhir.Models.DataType.Address;
import com.ggpsgeorge.fullstack_hospital_system_fhir.Models.DataType.Name;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Patient {

    @Column
    private String resourceType;
    @Id
    private String id;
    @Embedded
    @ElementCollection
    private List<Name> name = new ArrayList<>();
    @Column
    private String gender;
    @Column
    private LocalDate birthDate;
    @Embedded
    @ElementCollection
    private List<Address> address = new ArrayList<>();
    @ManyToMany
    @JoinTable(
        name = "doctor_aiding",
        joinColumns=@JoinColumn(name="patient_id"),
        inverseJoinColumns=@JoinColumn(name="doctor_id")
    )
    // Is good practice to use Set instead of list in a ManyToMany, because when deleting the jpa uses a lot of resources
    private List<Practioner> doctors = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name="hospital_id")
    @JsonIgnore
    private Organization hospital;


    public Patient() {
    }

    public Patient(String resourceType, String id, List<Name> name, String gender, LocalDate birthDate, List<Address> address, List<Practioner> doctors, Organization hospital) {
        this.resourceType = resourceType;
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.address = address;
        this.doctors = doctors;
        this.hospital = hospital;
    }

    public String getResourceType() {
        return this.resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Name> getName() {
        return this.name;
    }

    public void setName(List<Name> name) {
        this.name = name;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Address> getAddress() {
        return this.address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public List<Practioner> getDoctors() {
        return this.doctors;
    }

    public void setDoctors(List<Practioner> doctors) {
        this.doctors = doctors;
    }

    public Organization getHospital() {
        return this.hospital;
    }

    public void setHospital(Organization hospital) {
        this.hospital = hospital;
    }

    public Patient resourceType(String resourceType) {
        setResourceType(resourceType);
        return this;
    }

    public Patient id(String id) {
        setId(id);
        return this;
    }

    public Patient name(List<Name> name) {
        setName(name);
        return this;
    }

    public Patient gender(String gender) {
        setGender(gender);
        return this;
    }

    public Patient birthDate(LocalDate birthDate) {
        setBirthDate(birthDate);
        return this;
    }

    public Patient address(List<Address> address) {
        setAddress(address);
        return this;
    }

    public Patient doctors(List<Practioner> doctors) {
        setDoctors(doctors);
        return this;
    }

    public Patient hospital(Organization hospital) {
        setHospital(hospital);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Patient)) {
            return false;
        }
        Patient patient = (Patient) o;
        return Objects.equals(resourceType, patient.resourceType) && Objects.equals(id, patient.id) && Objects.equals(name, patient.name) && Objects.equals(gender, patient.gender) && Objects.equals(birthDate, patient.birthDate) && Objects.equals(address, patient.address) && Objects.equals(doctors, patient.doctors) && Objects.equals(hospital, patient.hospital);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourceType, id, name, gender, birthDate, address, doctors, hospital);
    }

    @Override
    public String toString() {
        return "{" +
            " resourceType='" + getResourceType() + "'" +
            ", id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", gender='" + getGender() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", address='" + getAddress() + "'" +
            ", doctors='" + getDoctors() + "'" +
            ", hospital='" + getHospital() + "'" +
            "}";
    }    

}
