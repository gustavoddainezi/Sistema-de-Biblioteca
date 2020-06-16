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
import com.fatec.scel.servico.LivroServico;

@Controller
@RequestMapping(path = "/livros")
public class LivroController {
	Logger logger = LogManager.getLogger(LivroController.class);
	@Autowired
	LivroServico servico;

	@GetMapping("/consultar")
	public ModelAndView retornaFormDeConsultaTodosLivros() {
		ModelAndView modelAndView = new ModelAndView("consultarLivro");
		modelAndView.addObject("livros", servico.findAll());
		return modelAndView;
	}

	@GetMapping("/cadastrar")
	public ModelAndView retornaFormDeCadastroDe(Livro livro) {
		ModelAndView mv = new ModelAndView("cadastrarLivro");
		mv.addObject("livro", livro);
		return mv;
	}

	@GetMapping("/edit/{isbn}") // diz ao metodo que ira responder a uma requisicao do tipo get
	public ModelAndView retornaFormParaEditarLivro(@PathVariable("isbn") String isbn) {
		ModelAndView modelAndView = new ModelAndView("atualizarLivro");
		modelAndView.addObject("livro", servico.findByIsbn(isbn)); // o repositorio e injetado no controller
		return modelAndView; // addObject adiciona objetos para view
	}

	@GetMapping("/delete/{id}")
	public ModelAndView excluiNoFormDeConsultaLivro(@PathVariable("id") Long id) {
		servico.deleteById(id);
		ModelAndView modelAndView = new ModelAndView("consultarLivro");
		modelAndView.addObject("livros", servico.findAll());
		return modelAndView;
	}

	@PostMapping("/save")
	public ModelAndView save(@Valid Livro livro, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("consultarLivro");
		if (result.hasErrors()) {
			modelAndView.setViewName("cadastrarLivro");
		} else {
			modelAndView = servico.verificaISBNJaExiste(livro);
		}
		return modelAndView;
	}

	@PostMapping("/update/{id}")
	public ModelAndView atualizaLivro(@PathVariable("id") Long id, @Valid Livro livro, BindingResult result) {
		if (result.hasErrors()) {
			livro.setId(id);
			return new ModelAndView("atualizarLivro");
		}
		// programacao defensiva - melhorar deve-se verificar se o livro existe antes de
		// atualizar
		Livro umLivro = servico.findById(id);
		umLivro.setAutor(livro.getAutor());
		umLivro.setIsbn(livro.getIsbn());
		umLivro.setTitulo(livro.getTitulo());
		servico.save(umLivro);
		ModelAndView modelAndView = new ModelAndView("consultarLivro");
		modelAndView.addObject("livros", servico.findAll());
		return modelAndView;
	}
}