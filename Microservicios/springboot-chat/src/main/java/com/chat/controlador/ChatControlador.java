package com.chat.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chat.servicio.IChatServicio;
import com.chat.servicio.IMensajeServicio;
import com.commons.dto.ChatDTO;
import com.commons.dto.MensajeDTO;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ChatControlador {

	@Autowired
	private IChatServicio charServicio;

	@Autowired
	private IMensajeServicio mensajeServicio;
	
	@GetMapping("/ver-chat/{id}")
	public ResponseEntity<ChatDTO> getChatPorId(@PathVariable Long id) {
		ChatDTO chat = charServicio.verChatPorId(id);
		return chat != null ? ResponseEntity.ok(chat) : ResponseEntity.notFound().build();

	}
	
	@GetMapping("/ver-chat/{comprador}/{vendedor}")
	public ResponseEntity<ChatDTO> getChatPorCompradorYVendedor(@PathVariable String comprador,
			@PathVariable String vendedor) {
		ChatDTO chat = charServicio.obtenerChatMensajesYUsuarios(comprador, vendedor);
		return chat != null ? ResponseEntity.ok(chat) : ResponseEntity.notFound().build();

	}
	
	@GetMapping("/recuperar-chats-vendedor/{vendedor}")
	public ResponseEntity<List<ChatDTO>> getChatPorCompradorYVendedor(@PathVariable String vendedor) {
		List<ChatDTO> listaChats = charServicio.obtenerChatVendedorMensajesYUsuarios(vendedor);
		return listaChats != null ? ResponseEntity.ok(listaChats) : ResponseEntity.notFound().build();

	}

	@PostMapping("/crear-chat")
	public ResponseEntity<ChatDTO> crearChat(@RequestBody ChatDTO chatDTO) {
		ChatDTO chat = charServicio.crearChar(chatDTO);
		return chat != null ? ResponseEntity.ok(chat) : ResponseEntity.notFound().build();
	}

	@PostMapping("/crear-mensaje")
	public ResponseEntity<MensajeDTO> addMensajeChat(@RequestBody MensajeDTO mensajeDTO) {
		MensajeDTO mensajeCreado = mensajeServicio.crearMensaje(mensajeDTO);
		return mensajeCreado != null ? ResponseEntity.ok(mensajeCreado) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/mensajesPorChat/{chatId}")
	public ResponseEntity<List<MensajeDTO>> getMensajesPorChat(@PathVariable Long chatId){
		List<MensajeDTO> mensajesChat = mensajeServicio.getMensajesChat(chatId);
		return mensajesChat != null ? ResponseEntity.ok(mensajesChat) : ResponseEntity.notFound().build();
	}
	
	@MessageMapping("/mensaje")
	@SendTo("/chat/mensaje") 
	public MensajeDTO recibeMensaje(MensajeDTO mensaje) {
		MensajeDTO mensajeRecibido = new MensajeDTO();
		mensajeRecibido.setId(mensaje.getId());
		mensajeRecibido.setContenido(mensaje.getContenido());
		mensajeRecibido.setUsuarioAutor(mensaje.getUsuarioAutor());
		mensajeRecibido.setChat_id(mensaje.getChat_id());
		return mensajeRecibido;
	}
}
