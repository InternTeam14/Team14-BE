package com.be.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.be.dto.ManufacturerDTO;
import com.be.dto.ProductDTO;
import com.be.entities.Cart;
import com.be.entities.Category;
import com.be.entities.Manufacturer;
import com.be.entities.Product;
import com.be.service.CartService;
import com.be.service.CategoryService;
import com.be.service.ManufacturerService;
import com.be.service.ProductService;
import com.be.service.StorageService;
import com.be.utils.RamdomID;



@Controller
@RequestMapping("web/admin/product/")
public class ManagementProductController {
	@Autowired
	private ProductService productService;



	@Autowired
	private ManufacturerService manufacturerService;
	
	
	
	
	@Autowired
	private StorageService storageService;

	@GetMapping("add")
	public String add(Model model) {
		ProductDTO dto = new ProductDTO();
		List<Category> list = null;
		model.addAttribute("categories", list);
		List<Manufacturer> list1 = manufacturerService.findAll();
		model.addAttribute("manus", list1);
		
		List<Cart> list2 = null;
		model.addAttribute("carts", list2);
		dto.setIsEdit(false);
		model.addAttribute("product", dto);
		return "adminUI/managementProduct";
	}
	
	
	@GetMapping("edit/{id}")
	public ModelAndView edit(ModelMap model, @PathVariable("id") String productID) {
		Optional<Product> opt = productService.findById(Long.valueOf(productID));
		
		ProductDTO dto = new ProductDTO();
		if (opt.isPresent()) {
			Product enCategory = opt.get();
			
			List<Category> list = null;
			model.addAttribute("categories", list);
			List<Manufacturer> list1 = manufacturerService.findAll();
			model.addAttribute("manus", list1);
			
			List<Cart> list2 = null;
			model.addAttribute("carts", list2);
			
			BeanUtils.copyProperties(enCategory, dto);
			dto.setCateId(enCategory.getCategory().getCateId());
			dto.setCartId(enCategory.getCart().getCartId());
			dto.setManuId(enCategory.getManufacturer().getManuId());
			dto.setIsEdit(true);

			model.addAttribute("product", dto);
			return new ModelAndView("adminUI/managementProduct", model);
		}
		
		
		model.addAttribute("message", "Product is not existed");
		return new ModelAndView("forward:/adminUI/managementProduct", model);

	}

	@GetMapping("/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@GetMapping("delete/{id}")
	public ModelAndView delete(@PathVariable("productID") String id, ModelMap model) throws IOException {
		Optional<Product> otp = productService.findById(Long.valueOf(id));
		if (otp.isPresent()) {
			if(!StringUtils.isEmpty(otp.get().getImages())){
				storageService.delete(otp.get().getImages());
			}
			productService.delete(otp.get());
			model.addAttribute("message", "Product is delete");
		}else {
			model.addAttribute("message","Product not delete");
		}
		
		return new ModelAndView("forward:/adminUI/managementProduct", model);
	}

	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @Validated @ModelAttribute("product") ProductDTO productModel,
			BindingResult result) {
		
		
		Product entity = new Product();
		
		BeanUtils.copyProperties(productModel, entity);
		
		Category category = new Category();
		
		category.setCateId(productModel.getCateId());
		
		entity.setCategory(category);
	
		Manufacturer manufacturer = new Manufacturer();
		manufacturer.setManuId(productModel.getManuId());
		entity.setManufacturer(manufacturer);
		
		Cart cart = new Cart();
		cart.setCartId(productModel.getCartId());
		entity.setCart(cart);
		
		if (!productModel.getImageFile().isEmpty()) {
			UUID uuid = UUID.randomUUID();
			String uuString = uuid.toString();

			entity.setImages(storageService.getStoredFileName(productModel.getImageFile(), uuString));
			storageService.store(productModel.getImageFile(), entity.getImages());
		}

		productService.save(entity);
		model.addAttribute("message", "Product is save");

		return new ModelAndView("forward:/adminUI/managementProduct", model);
	}

	@GetMapping("view")
	public String list(ModelMap modelMap) {
		List<Product> list = productService.findAll();
		modelMap.addAttribute("products", list);
		return "adminUI/managementProduct";
	}
	
	
	
	

}
