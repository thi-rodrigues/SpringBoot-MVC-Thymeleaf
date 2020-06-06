package com.boot.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boot.web.domain.Cargo;
import com.boot.web.domain.Departamento;
import com.boot.web.service.CargoService;
import com.boot.web.service.DepartamentoService;

@Controller
@RequestMapping("/cargos")
public class CargoController {

	@Autowired
	private CargoService cargoService;

	@Autowired
	private DepartamentoService departamentoService;

	@GetMapping("/cadastrar")
	public String cadastrar(Cargo cargo) {
		return "cargo/cadastro";
	}

	@GetMapping("/listar")
	public String cargos(ModelMap model) {
		model.addAttribute("cargos", cargoService.buscarTodos());
		return "cargo/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Cargo cargo, BindingResult bindingResult, RedirectAttributes attr) {
		if (bindingResult.hasErrors()) {
			return "cargo/cadastro";
		}

		cargoService.salvar(cargo);
		attr.addFlashAttribute("success", "Cargo inserido com sucesso.");
		return "redirect:/cargos/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("cargo", cargoService.buscarPorId(id));
		return "cargo/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Cargo cargo, BindingResult bindingResult, RedirectAttributes attr) {
		if (bindingResult.hasErrors()) {
			return "cargo/cadastro";
		}
		
		cargoService.editar(cargo);
		attr.addFlashAttribute("success", "Registro atualizado com sucesso.");
		return "redirect:/cargos/listar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		if (cargoService.cargoTemFuncionarios(id)) {
			attr.addFlashAttribute("fail", "Cargo não excluido. Tem funcionário(s) vinculado(s).");
		} else {
			cargoService.excluir(id);
			attr.addFlashAttribute("success", "Cargo excluido com sucesso.");
		}
		return "redirect:/cargos/listar";
	}

	@ModelAttribute("departamentos")
	public List<Departamento> listaDeDepartamentos() {
		return departamentoService.buscarTodos();
	}

}
