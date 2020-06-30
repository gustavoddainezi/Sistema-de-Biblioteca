package com.fatec.scel.controller;

import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.fatec.scel.model.Livro;
import com.fatec.scel.model.Usuario;
import com.fatec.scel.servico.UsuarioServico;

//imports omitidos
@Controller
@RequestMapping(path = "/usuarios")
public class UsuarioController {
	Logger logger = LogManager.getLogger(UsuarioController.class);
	@Autowired
	UsuarioServico servico;

	@GetMapping("/consulta")
	public ModelAndView retornaFormDeConsultaTodosUsuarios() {
		ModelAndView modelAndView = new ModelAndView("consultarUsuario");
		modelAndView.addObject("usuarios", servico.findAll());
		return modelAndView;
	}

	@GetMapping("/cadastrar")
	public ModelAndView retornaFormCadastraDe(Usuario usuario) {
		ModelAndView mv = new ModelAndView("cadastrarUsuario");
		mv.addObject("usuario", usuario);
		return mv;
	}

	@GetMapping("/edit/{ra}") // diz ao metodo que ira responder a uma requisicao do tipo get
	public ModelAndView retornaFormParaEditarUsuario(@PathVariable("ra") String ra) {
		ModelAndView modelAndView = new ModelAndView("atualizarUsuario");
		modelAndView.addObject("usuario", servico.findByRa(ra)); // o repositorio e injetado no controller
		return modelAndView; // addObject adiciona objetos para view
	}

	@GetMapping("/delete/{id}")
	public ModelAndView excluirNoFormDeConsultaTodosUsuarios(@PathVariable("id") Long id) {
		servico.deleteById(id);
		ModelAndView modelAndView = new ModelAndView("consultarUsuario");
		modelAndView.addObject("usuarios", servico.findAll());
		return modelAndView;
	}

	@PostMapping("/save")
	public ModelAndView save(@Valid Usuario usuario, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("consultarUsuario");
		if (result.hasErrors()) {
			modelAndView.setViewName("cadastrarUsuario");
		} else {
			modelAndView = servico.verificaRaExiste(usuario);
		}
		return modelAndView;
	}

	@PostMapping("/update/{id}")
	public ModelAndView atualizaUsuario(@PathVariable("id") Long id, @Valid Usuario usuario, BindingResult result) {

		if (result.hasErrors()) {
			usuario.setId(id);
			return new ModelAndView("atualizarUsuario");
		}
		Usuario umUsuario = servico.findById(id);
		umUsuario.setRa(usuario.getRa());
		umUsuario.setNome(usuario.getNome());
		umUsuario.setEmail(usuario.getEmail());
		servico.save(umUsuario);
		ModelAndView modelAndView = new ModelAndView("consultarUsuario");
		modelAndView.addObject("usuarios", servico.findAll());
		return modelAndView;
	}
}