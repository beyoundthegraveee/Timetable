package com.source.timetable.DTOs;

import com.source.timetable.enums.AccountStatus;
import com.source.timetable.enums.Role;

import java.time.LocalDate;

public class CreateAdminDTO extends CreateUserDTO{
    public LocalDate employmentDate;
    public AccountStatus accountStatus;

    public CreateAdminDTO(String firstName, String lastName, LocalDate birthDate, String email, String login, String password, Role role, LocalDate employmentDate, AccountStatus accountStatus) {
        super(firstName, lastName, birthDate, email, login, password, role);
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
}
