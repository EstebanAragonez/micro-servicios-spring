package com.micro.companies_crud.services;

import com.micro.companies_crud.entities.Category;
import com.micro.companies_crud.entities.Company;
import com.micro.companies_crud.repositories.CompanyRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    @Override
    public Company create(Company company) {
        company.getWebSites().forEach(webSite -> {
            if (Objects.isNull(webSite.getCategory())){
               webSite.setCategory(Category.NONE);
            }
        });
        return this.companyRepository.save(company);
    }

    @Override
    public Company readByName(String name) {
        return this.companyRepository.findByName(name)
                .orElseThrow(()-> new NoSuchElementException("Company not found"));
    }

    @Override
    public Company update(Company company, String name) {
        var companyToUpdate = this.companyRepository.findByName(name)
                .orElseThrow(()-> new NoSuchElementException("Company not found"));
        companyToUpdate.setLogo(company.getLogo());
        companyToUpdate.setFundationDate(company.getFundationDate());
        companyToUpdate.setFounder(company.getFounder());
        return this.companyRepository.save(companyToUpdate);
    }

    @Override
    public void delete(String name) {
        var companyToDelete = this.companyRepository.findByName(name)
                .orElseThrow(()-> new NoSuchElementException("Company not found"));
        this.companyRepository.delete(companyToDelete);

    }
}