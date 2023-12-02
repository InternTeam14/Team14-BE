package com.be.controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.be.dto.CategoryDTO;
import com.be.dto.ManufacturerDTO;
import com.be.dto.ProductDTO;
import com.be.service.ManufacturerService;
import com.be.service.ProductService;
import com.be.service.SessionService;
import com.be.service.StorageService;
import com.be.service.impl.CategoryServiceImpl;
import com.be.utils.RedirectHelper;

import com.be.entities.Category;
import com.be.entities.Manufacturer;
import com.be.entities.Product;

@Controller
@RequestMapping("web/admin/product/")
public class ManagementProductController {

	@Autowired
	ProductService productService;
	@Autowired
	CategoryServiceImpl categotyService;
	@Autowired
	ManufacturerService manufacturerService;
	@Autowired
	SessionService session;

	@Autowired
	StorageService storageService;

	@Autowired
	ServletContext app;

	public void fillToTable(ModelMap model) {
		List<Product> list = productService.findAll();
		model.addAttribute("produtss", list);
	}

	@ModelAttribute("manus") // lựa chọn danh mục
	public List<ManufacturerDTO> getDtos() {
		return manufacturerService.findAll().stream().map(item -> {
			ManufacturerDTO dto = new ManufacturerDTO();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).collect(Collectors.toList());
	}

	@ModelAttribute("categories") // lựa chọn danh mục
	public List<CategoryDTO> getCategories() {
		return categotyService.findAll().stream().map(item -> {
			CategoryDTO dto = new CategoryDTO();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).collect(Collectors.toList());
	}

	@GetMapping("add")
	public String add(ModelMap modelMap) {

		fillToTable(modelMap);

		ProductDTO dto = new ProductDTO();
		List<Category> list = categotyService.findAll();
		modelMap.addAttribute("categories", list);

		List<Manufacturer> listq = manufacturerService.findAll();
		modelMap.addAttribute("manus", listq);
		dto.setIsEdit(false);
		List<Product> listss = productService.findAll();
		modelMap.addAttribute("product", listss);
		modelMap.addAttribute("product", new ProductDTO());
		return "adminUI/managementProduct";
	}

	@GetMapping("edit/{productID}")
	public ModelAndView edit(ModelMap model, @PathVariable("productID") String productID) {
		fillToTable(model);
		Optional<Product> opt = productService.findById(productID);
		ProductDTO dto = new ProductDTO();

		if (opt.isPresent()) {
			Product entity = opt.get();

			BeanUtils.copyProperties(entity, dto);
			dto.setIsEdit(true);
			dto.setCateId(entity.getCategory().getCateId());
			dto.setManuId(entity.getManufacturer().getManuId());
			dto.setImages(entity.getImages());
			model.addAttribute("product", dto);

			return new ModelAndView("adminUI/managementProduct", model);

		}
		model.addAttribute("mess", "Dish is not existed");

		return RedirectHelper.redirectTo("/web/admin/product/add");
	}

	@PostMapping("/saveOrUpdate")
	public ModelAndView save(ModelMap modelMap, @ModelAttribute("order") ProductDTO dto) {
		List<Category> list = categotyService.findAll();
		modelMap.addAttribute("categories", list);

		Product entity = new Product();
		Category customer = new Category();
		Manufacturer manufacturer = new Manufacturer();
		BeanUtils.copyProperties(dto, entity);
		customer.setCateId(dto.getCateId());
		manufacturer.setManuId(dto.getManuId());
//
//		if (!dto.getImgFile().isEmpty()) {
//			UUID uuid = UUID.randomUUID();
//			String uuString = uuid.toString();
//			entity.setImages(storageService.getStoredFileName(dto.getImgFile(), uuString));
//			storageService.storeResizedImage(dto.getImgFile(), entity.getImages(), 209, 171);
//		}

		if (!dto.getImgFile().isEmpty()) {
			UUID uuid = UUID.randomUUID();

			String uuString = uuid.toString();

			entity.setImages(storageService.getStoredFileName(dto.getImgFile(), uuString));
			storageService.store(dto.getImgFile(), entity.getImages());
		}
		dto.setIsEdit(true);
		entity.setCategory(customer);
		entity.setManufacturer(manufacturer);

		productService.save(entity);
		modelMap.addAttribute("message", "Thành công");

		return RedirectHelper.redirectTo("/web/admin/product/add");

	}

	@GetMapping("delete/{productID}")
	public ModelAndView delete(@PathVariable("productID") String productID, ModelMap modelMap) {
		productService.deleteById(productID);

		modelMap.addAttribute("message", "Xóa thành công");
		return RedirectHelper.redirectTo("/web/admin/product/add");
	}

	@GetMapping("/uploads/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
}
