package com.be.controller.admin;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.be.dto.ManufacturerDTO;
import com.be.entities.Manufacturer;
import com.be.service.ManufacturerService;
import com.be.utils.RamdomID;
import com.be.utils.RedirectHelper;


@Controller
@RequestMapping("web/admin/manufacturer/")
public class ManagementManufacturerController {

	@Autowired
	private ManufacturerService manufacturerService;

	public void fillToTable(ModelMap model) {
		List<Manufacturer> list = manufacturerService.findAll();
		model.addAttribute("manus", list);
	}

	@GetMapping("view")
	public String viewForm(ModelMap model) {
		fillToTable(model);

		ManufacturerDTO dto=new ManufacturerDTO();
		dto.setManuId("M-"+RamdomID.generateRandomId(10));
		model.addAttribute("manu", dto);
		return "adminUI/managementManufacturer";
	}

	@PostMapping("saveOrUpdate")
	public ModelAndView save(ModelMap model, @Valid @ModelAttribute("manu") ManufacturerDTO dto,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ModelAndView("restaurantUI/managementCategories");
		}
		Manufacturer entity = new Manufacturer();
		BeanUtils.copyProperties(dto, entity);
		manufacturerService.save(entity);
		if (dto.getIsEdit()) {
			model.addAttribute("mess", "Manufacturer is updated");
		} else {
			model.addAttribute("mess", "Manufacturer is saved");
		}

		return RedirectHelper.redirectTo("/web/admin/manufacturer/view");
	}

	@GetMapping("delete/{manuId}")
	public ModelAndView delete(ModelMap model, @PathVariable("manuId") String manuID) {
		manufacturerService.deleteById(manuID);
		model.addAttribute("mess", "Manufacturer id deleted");
		return RedirectHelper.redirectTo("/web/admin/manufacturer/view");
	}

	@GetMapping("edit/{manuId}")
	public ModelAndView edit(ModelMap model, @PathVariable("manuId") String manuID) {
		fillToTable(model);
		Optional<Manufacturer> opt = manufacturerService.findById(manuID);
		ManufacturerDTO dto = new ManufacturerDTO();

		if (opt.isPresent()) {
			Manufacturer entity = opt.get();
			BeanUtils.copyProperties(entity, dto);
			dto.setIsEdit(true);

			model.addAttribute("manu", dto);
			return new ModelAndView("adminUI/managementManufacturer", model);
		}
		model.addAttribute("mess", "Manufacturer is not existed");

		return new ModelAndView("adminUI/managementManufacturer", model);
	}

}
