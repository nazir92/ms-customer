package com.alten.customer.mapper;

import com.alten.customer.dto.CustomerDto;
import com.alten.customer.dto.CustomerResponseDto;
import com.alten.customer.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class CustomerMapper {

    public static final CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    public abstract Customer fromCustomerDto(CustomerDto customerDto);

    public abstract CustomerDto toCustomerDto(Customer customer);


    @Mapping(source = "customerId",target = "id")
    public abstract CustomerResponseDto toCustomerResponseDto(Customer customer);


    public abstract List<CustomerResponseDto> toCustomerDtoList(List<Customer> customers);


}
