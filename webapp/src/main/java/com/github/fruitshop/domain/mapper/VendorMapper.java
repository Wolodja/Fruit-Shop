package com.github.fruitshop.domain.mapper;

import com.github.fruitshop.domain.dto.VendorDto;
import com.github.fruitshop.domain.entity.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    VendorDto vendorToVendorDto(Vendor vendor);

    Vendor vendorDTOtoVendor(VendorDto vendorDto);
}
