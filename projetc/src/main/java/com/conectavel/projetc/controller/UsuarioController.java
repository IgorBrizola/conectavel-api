package com.conectavel.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.conectavel.api.model.Usuario;
import com.conectavel.api.service.UsuarioService;


@RestController
@RequestMapping("/api/v1")
public class UsuarioController {
	private final UsuarioService usuarioService;
	
	@Autowired
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	
	@PostMapping("/usuario")
	public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario)
	{
		Usuario novoUsuario = usuarioService.salvarUsuario(usuario);
		return ResponseEntity.ok(novoUsuario);
	}
	
	@GetMapping("/usuarios")
	public List<Usuario> getAllUsuarios() {
		return usuarioService.getAllUsuarios();
	}

	@GetMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id){
		Optional<Usuario> usuario = usuarioService.getUsuarioById(id);
		return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PutMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> usuarioServices(@PathVariable Long id, @RequestBody Usuario usuario){
		Usuario usuarioAtualizado = usuarioService.atualizarUsuario(id, usuario);
		return ResponseEntity.ok(usuarioAtualizado);
	}
	
	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<String> deletarUsuario(@PathVariable Long id)
	{
		usuarioService.deletarUsuario(id);
		return ResponseEntity.ok("Usuario deletado com sucesso!");
	}
	
}
