package com.github.fruitshop.bootstrap;

import com.github.fruitshop.domain.entity.Category;
import com.github.fruitshop.domain.entity.Customer;
import com.github.fruitshop.repository.CategoryRepository;
import com.github.fruitshop.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;

    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadCustomers();
        loadCategories();
    }

    private void loadCustomers() {
        Customer john = Customer.builder().firstName("John").lastName("Smith").build();
        Customer adam = Customer.builder().firstName("Adam").lastName("Cold").build();
        Customer lidia = Customer.builder().firstName("Lidia").lastName("Nowak").build();
        Customer danuta = Customer.builder().firstName("Danuta").lastName("Kowal").build();
        Customer wiktor = Customer.builder().firstName("Wiktor").lastName("Bąk").build();

        customerRepository.save(john);
        customerRepository.save(adam);
        customerRepository.save(lidia);
        customerRepository.save(danuta);
        customerRepository.save(wiktor);

        System.out.println("Data Loaded int customer table: "+ customerRepository.count());
    }

    private void loadCategories() {
        Category fruits = Category.builder().name("Fruits").build();
        Category dried = Category.builder().name("Dried").build();
        Category fresh = Category.builder().name("Fresh").build();
        Category exotic = Category.builder().name("Exotic").build();
        Category nuts = Category.builder().name("Nuts").build();

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fruits);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Data Loaded int category table: "+ categoryRepository.count());
    }
}
