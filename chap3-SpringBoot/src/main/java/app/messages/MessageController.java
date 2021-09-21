package app.messages;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/messages")
public class MessageController {
		
		private final static Log log = LogFactory.getLog( MessageController.class );
		
		private MessageService messageService;
		
		public MessageController( MessageService messageService) {
				this.messageService = messageService;
		}
		
		@GetMapping("/welcome")
		public String welcome( Model model ) {
				log.info( "Hello World!..............." );
				model.addAttribute( "message", "HelloWorld!");
				return "welcome";
		}
		
		@PostMapping("")
		@ResponseBody
		public ResponseEntity<Message> saveMessage(@RequestBody MessageData data ) {
				
				checkSecurity();
				
				Message saved = messageService.save( data.getText() );
				if ( saved == null ) {
						return ResponseEntity.status( 500 ).build();
				}
				return ResponseEntity.ok( saved );
				
		}
		
		private void checkSecurity() throws NotAuthorizedException{
		
		}

}
