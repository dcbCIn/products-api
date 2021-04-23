package br.com.dcruzb.product.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.dcruzb.product.model.Product;
import br.com.dcruzb.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;

@Component
public class Seeder {
	@Autowired
	private ProductService productService;
	
	public Seeder(ProductService productService) {
		this.productService = productService;
		seed();		
	}
	
	public void seed() {
		Product product = new Product();
		product.setName("The Fellowship of the Ring");
		product.setDescription("The first novel in the Lord of the Rings series");
		product.setValue(12.34);
		productService.save(product);
		
		product = new Product();
		product.setName("The Two Towers");
		product.setDescription("The second novel in the Lord of the Rings series");
		product.setValue(21.45);
		productService.save(product);
		
		product = new Product();
		product.setName("The Return of the King");
		product.setDescription("The third novel in the Lord of the Rings series");
		product.setValue(34.85);
		productService.save(product);		
		
		product = new Product();
		product.setName("The Cuckoo's Calling");
		product.setDescription("The first novel in the Cormoran Strike series");
		product.setValue(41.15);
		productService.save(product);
		
		product = new Product();
		product.setName("The Silkworm");
		product.setDescription("The second novel in the Cormoran Strike series");
		product.setValue(59.56);
		productService.save(product);
		
		product = new Product();
		product.setName("Career of Evil");
		product.setDescription("The thrid novel in the Cormoran Strike series");
		product.setValue(62.30);
		productService.save(product);
		
		product = new Product();
		product.setName("Lethal White");
		product.setDescription("The fourth novel in the Cormoran Strike series");
		product.setValue(71.90);
		productService.save(product);
		
		product = new Product();
		product.setName("Troubled Blood");
		product.setDescription("The fifth novel in the Cormoran Strike series");
		product.setValue(87.13);
		productService.save(product);
	}
}
