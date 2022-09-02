package pe.rodcar.shelterpet.adoption.controller;

import java.util.HashSet;
//import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import pe.rodcar.shelterpet.adoption.entities.Role;
import pe.rodcar.shelterpet.adoption.entities.RoleName;
import pe.rodcar.shelterpet.adoption.entities.User;
import pe.rodcar.shelterpet.adoption.repository.RoleRepository;
import pe.rodcar.shelterpet.adoption.repository.UserRepository;
import pe.rodcar.shelterpet.adoption.request.LoginForm;
import pe.rodcar.shelterpet.adoption.request.SignUpForm;
import pe.rodcar.shelterpet.adoption.response.JwtResponse;
import pe.rodcar.shelterpet.adoption.response.ResponseMessage;
import pe.rodcar.shelterpet.adoption.security.jwt.JwtProvider;
import pe.rodcar.shelterpet.adoption.security.service.UserPrinciple;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;

	/*@Autowired
	private EmailService emailService;*/

	@ApiOperation("User authentication")
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
		String email = loginRequest.getEmail();
		String password = loginRequest.getPassword();

		// Verifica si el correo se encuentra registrado
		if (!userRepository.existsByEmail(email)) {
			return new ResponseEntity<>(new ResponseMessage("Error -> The email is not registered"),
					HttpStatus.UNAUTHORIZED);
		}

		// Verifica si la contraseña es la correcta para el correo asociado
		User user = userRepository.findByEmail(email).get();

		if (!BCrypt.checkpw(password, user.getPassword())) {
			return new ResponseEntity<>(new ResponseMessage("Error -> The password is not correct"),
					HttpStatus.UNAUTHORIZED);
		}

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		UserPrinciple userDetails = (UserPrinciple) authentication.getPrincipal();

		return ResponseEntity
				.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getEmail(), userDetails.getAuthorities()));
	}

	@ApiOperation("User registration")
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("Error -> The email is already in use"),
					HttpStatus.BAD_REQUEST);
		}

		User user = new User(signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()),
				signUpRequest.getName(), signUpRequest.getPhone());
		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("¡Fallo! -> Causa: El rol del usuario no se encuentra."));
		roles.add(userRole);
		user.setRoles(roles);
		userRepository.save(user);
		return new ResponseEntity<>(new ResponseMessage("Usuario registrado correctamente!"), HttpStatus.OK);
	}

	/*@ApiOperation("Restablece la contraseña y envía correo electrónico con nueva contraseña")
	@PostMapping(value = "/resetpassword")
	@ResponseBody
	public ResponseEntity<Object> resetPassword(@RequestParam("email") String email) {
		try {
			Optional<User> userFinded = userRepository.findByEmail(email);

			if (userFinded.isPresent()) {
				User user = userFinded.get();

				// Genera nueva contraseña
				String newPassword = PasswordGenerator.generate();
				user.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
				userRepository.save(user);

				// Genera el correo
				EmailBody eb = new EmailBuilder().resetPassword(newPassword).to(email).build();

				if (emailService.sendEmail(eb)) {
					return ResponseEntity.ok().build();
				} else {
					return new ResponseEntity<Object>(HttpStatus.SERVICE_UNAVAILABLE);
				}

			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/

	@ApiOperation("Verify if an email is already used")
	@GetMapping(value = "/exists")
	@ResponseBody
	public ResponseEntity<Object> exitsByEmail(@RequestParam("email") String email) {
		if (userRepository.existsByEmail(email)) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}