package br.com.dcruzb.product.controller;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dcruzb.product.exception.EntityNotFoundException;
import br.com.dcruzb.product.exception.ErrorMessage;
import br.com.dcruzb.product.model.Product;
import br.com.dcruzb.product.repository.ProductRepository;
import br.com.dcruzb.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping()
	public Page<Product> listAll(@PageableDefault(size = 5) Pageable page) {
		return productRepository.findAll(page);
	}
	
	@PostMapping()
	public ResponseEntity<?> create(@RequestBody @Valid Product product) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), "It was not possible to save the notice!", LocalDateTime.now());
			return ResponseEntity.unprocessableEntity().body(errorMessage);
		}
	}
	
	@PutMapping()
	public ResponseEntity<?> update(@RequestBody @Valid Product product) {
		try {
			Product storedProduct = productService.findOrFail(product.getId()); 
			
			storedProduct.setName(product.getName());
			storedProduct.setDescription(product.getDescription());
			storedProduct.setValue(product.getValue());
			
			return ResponseEntity.ok(productService.save(storedProduct));
		} catch (EntityNotFoundException e) {
			ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), e.getMessage(), LocalDateTime.now());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), "It was not possible to update the product!", LocalDateTime.now());
			return ResponseEntity.unprocessableEntity().body(errorMessage);
		}
	}
	
	@DeleteMapping("/{productId}")
	public ResponseEntity<?> delete(@PathVariable Long productId) {
		try {
			productService.delete(productId);
			return ResponseEntity.noContent().build();
		} catch (EntityNotFoundException e) {
			ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), e.getMessage(), LocalDateTime.now());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), "It was not possible to delete the product!", LocalDateTime.now());
			return ResponseEntity.unprocessableEntity().body(errorMessage);
		}
	}
}
