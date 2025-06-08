package com.source.timetable.models;

import com.source.timetable.enums.AccountStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "administrators")
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User{

    @Column(nullable = false)
    private LocalDate employmentDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus accountStatus;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKey(name = "id")
    private Map<Integer, AccessRight> accessRights = new HashMap<>();

    public Admin() {
        super();
    }

    public Admin(int id, String firstName, String lastName, LocalDate birthDate, String email, String login, String password, LocalDate employmentDate, AccountStatus accountStatus) {
        super(id, firstName, lastName, birthDate, email, login, password);
        this.employmentDate = employmentDate;
        this.accountStatus = accountStatus;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Map<Integer, AccessRight> getAccessRights() {
        return accessRights;
    }

    public void setAccessRights(Map<Integer, AccessRight> accessRights) {
        this.accessRights = accessRights;
    }
}
