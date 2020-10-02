package com.rabo.demoron.repos.poa;

import java.util.List;

public interface PowerOfAttorneyRepo {

    List<PowerOfAttorney> findAll();

    PowerOfAttorney findById(String id);

}
