package br.com.dcruzb.product.service;

import javax.transaction.Transactional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.dcruzb.product.exception.EntityNotFoundException;
import br.com.dcruzb.product.model.Product;
import br.com.dcruzb.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	public Product findOrFail(Long productId) throws EntityNotFoundException {
		return productRepository.findById(productId).orElseThrow(
					() -> new EntityNotFoundException("Product " + productId + " not found!")
				);
	}
	
	@Transactional
	public Product save(Product product) {
		return productRepository.save(product);
	}
	
	@Transactional
	public void delete(Long productId) throws EntityNotFoundException {
		try {
			productRepository.deleteById(productId);
			productRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new EntityNotFoundException("Product " + productId + " not found!");
		}
	}
}
