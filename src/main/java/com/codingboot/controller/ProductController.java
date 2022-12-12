package com.codingboot.controller;
/*
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codingboot.entity.Product;
import com.codingboot.exception.ProductNotFoundException;
import com.codingboot.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		return ResponseEntity.ok(productRepository.save(product));
	}

	@GetMapping
	public ResponseEntity<List<Product>> getProductList() {
		return ResponseEntity.ok(productRepository.findAll());
	}

	@GetMapping("/{id}")

	public ResponseEntity<Product> findProduct(@PathVariable Integer id) {
		return ResponseEntity.ok(
				productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product Not found")));
	}

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<String> handleProductNotFoundException() {
		return ResponseEntity.ok("Product Not Found");
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		Product p = productRepository.findById(product.getId()).get();
		p.setName(product.getName());
		p.setPrice(p.getPrice());

		p = productRepository.save(p);
		return ResponseEntity.ok(p);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
		productRepository.deleteById(id);
		return ResponseEntity.ok("Deleted");
	}

	@GetMapping("/pagination")
	public ResponseEntity<List<Product>> getProductPagination(@RequestParam int page, @RequestParam int size) {
		Pageable pageable = PageRequest.of(page, size);
		List<Product> list = productRepository.findAll(pageable).getContent();
		return ResponseEntity.ok(list);

	}

	@GetMapping("/filter")
	public ResponseEntity<List<Product>> getProductPagination(@RequestParam String name) {
		return ResponseEntity.ok(productRepository.findByName(name));
	}

}
*/