package com.boot.web.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boot.web.domain.Departamento;
import com.boot.web.service.DepartamentoService;
import com.boot.web.util.PaginacaoUtil;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {

	@Autowired
	private DepartamentoService departamentoService;

	@GetMapping("/cadastrar")
	public String cadastrar(Departamento departamento) {
		return "departamento/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model, @RequestParam("page") Optional<Integer> page) {
		 
		int paginaAtual = page.orElse(1);
		
		PaginacaoUtil<Departamento> pageDepartamento = departamentoService.buscaPorPagina(paginaAtual);
		
		model.addAttribute("pageDepartamento", pageDepartamento);
		return "departamento/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Departamento departamento, BindingResult bindingResult, RedirectAttributes attr) {
		if (bindingResult.hasErrors()) {
			return "departamento/cadastro";
		}
		
		departamentoService.salvar(departamento);
		attr.addFlashAttribute("success", "Departamento salvo com sucesso.");
		return "redirect:/departamentos/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("departamento", departamentoService.buscarPorId(id));
		return "departamento/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Departamento departamento, BindingResult bindingResult, RedirectAttributes attr) {
		if (bindingResult.hasErrors()) {
			return "departamento/cadastro";
		}
		
		departamentoService.editar(departamento);
		attr.addFlashAttribute("success", "Departamento atualizado com sucesso.");
		return "redirect:/departamentos/listar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		if (departamentoService.departamentoTemCargos(id)) {
			model.addAttribute("fail", "Departamento não removido. Possui cargo(s) cadastrado(s).");
		} else {
			departamentoService.excluir(id);
			model.addAttribute("success", "Departamento excluído com sucesso.");
		}
		return "redirect:/departamentos/listar";
	}
}
