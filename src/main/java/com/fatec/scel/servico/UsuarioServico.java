package com.fatec.scel.servico;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import com.fatec.scel.model.Endereco;
import com.fatec.scel.model.Livro;
import com.fatec.scel.model.Usuario;
import com.fatec.scel.model.UsuarioRepository;

@Service
public class UsuarioServico {
	Logger logger = LogManager.getLogger(UsuarioServico.class);
	@Autowired
	private UsuarioRepository repository;

	public Iterable<Usuario> findAll() {
		return repository.findAll();
	}

	public Usuario findByRa(String ra) {
		return repository.findByRa(ra);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public Usuario findById(Long id) {
		return repository.findById(id).get();
	}

	public void save(Usuario usuario) {
		repository.save(usuario);
	}

	public String obtemEndereco(String cep) {
		RestTemplate template = new RestTemplate();
		String url = "https://viacep.com.br/ws/{cep}/json/";
		Endereco endereco = template.getForObject(url, Endereco.class, cep);
		return endereco.getLogradouro();
	}

	public ModelAndView verificaRaExiste(Usuario usuario) {
		ModelAndView modelAndView = new ModelAndView("consultarUsuario");
		try {
			Usuario jaExiste = null;
			jaExiste = repository.findByRa(usuario.getRa());
			logger.info("=======> Verifica se usuario ja existe = " + usuario.getRa());
			if (jaExiste == null) {
				logger.info("=======> Usuario nao cadastrado");
				usuario.setEndereco(obtemEndereco(usuario.getCep()));
				repository.save(usuario);
				modelAndView.addObject("usuarios", repository.findAll());
			} else {
				logger.info("=======> Usuario ja cadastrado");
				modelAndView.setViewName("cadastrarUsuario");
				modelAndView.addObject("message", "Usuario ja cadastrado");
			}
		} catch (Exception e) {
			logger.error("========> Exceçao nao prevista - save() cadastra usuario");
			modelAndView.setViewName("cadastrarUsuario");
			modelAndView.addObject("message", "Exceçao nao prevista");
		}
		return modelAndView;
	}
}