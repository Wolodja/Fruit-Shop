package com.github.fruitshop.service;

import com.github.fruitshop.domain.dto.VendorDto;
import com.github.fruitshop.domain.dto.VendorListDto;

public interface VendorService {

    VendorDto getVendorById(Long id);

    VendorListDto getAllVendors();

    VendorDto createNewVendor(VendorDto vendorDto);

    VendorDto saveVendorByDTO(Long id, VendorDto vendorDto);

    VendorDto patchVendor(Long id, VendorDto vendorDto);

    void deleteVendorById(Long id);
}
