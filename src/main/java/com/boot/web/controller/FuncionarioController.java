package com.boot.web.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.JodaTimeConverters.DateTimeToDateConverter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boot.web.domain.Cargo;
import com.boot.web.domain.Funcionario;
import com.boot.web.domain.UF;
import com.boot.web.service.CargoService;
import com.boot.web.service.FuncionarioService;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;

	@Autowired
	private CargoService cargoService;

	@GetMapping("/cadastrar")
	public String cadastrar(Funcionario funcionario) {
		return "/funcionario/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("funcionarios", funcionarioService.buscarTodos());
		return "/funcionario/lista";
	}

	@PostMapping("/salvar")
	public String salvar(Funcionario funcionario, RedirectAttributes attr) {
		funcionarioService.salvar(funcionario);
		attr.addFlashAttribute("success", "Funcionário cadastrado com sucesso.");
		return "redirect:/funcionarios/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("funcionario", funcionarioService.buscarPorId(id));
		return "funcionario/cadastro";
	}

	@GetMapping("/buscar/nome")
	public String getPorNome(@RequestParam("nome") String nome, ModelMap model) {
		model.addAttribute("funcionarios", funcionarioService.buscarPorNome(nome));
		return "/funcionario/lista";
	}

	@GetMapping("/buscar/cargo")
	public String getPorCargo(@RequestParam("id") Long id, ModelMap model) {
		model.addAttribute("funcionarios", funcionarioService.buscarPorCargo(id));
		return "funcionario/lista";
	}

	@GetMapping("/buscar/data")
	public String getPorDatas(@RequestParam("entrada") @DateTimeFormat(iso = ISO.DATE)  LocalDate entrada, 
			@RequestParam("saida") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate saida,
			ModelMap model) {
		model.addAttribute("funcionarios", funcionarioService.buscarPorDatas(entrada,saida));
		return "/funcionario/lista";
	}

	@PostMapping("/editar")
	public String editar(Funcionario funcionario, RedirectAttributes attr) {
		funcionarioService.editar(funcionario);
		attr.addFlashAttribute("success", "Funcionário atualizado com sucesso.");
		return "redirect:/funcionarios/listar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		funcionarioService.excluir(id);
		attr.addFlashAttribute("success, Funcionário removido com sucesso.");
		return "redirect:/funcionarios/listar";
	}

	@ModelAttribute("cargos")
	public List<Cargo> getCargos() {
		return cargoService.buscarTodos();
	}

	@ModelAttribute("ufs")
	public UF[] getUfs() {
		return UF.values();
	}

}
