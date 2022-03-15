package eu.commean.backend.controller;

import eu.commean.backend.dto.security.JwtResponseDto;
import eu.commean.backend.dto.security.LoginDto;
import eu.commean.backend.dto.security.SignUpDto;
import eu.commean.backend.entity.Role;
import eu.commean.backend.entity.User;
import eu.commean.backend.enums.RoleName;
import eu.commean.backend.repo.RoleRepository;
import eu.commean.backend.repo.UserRepository;
import eu.commean.backend.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

	JwtProvider tokenProvider;
	private AuthenticationManager authenticationManager;
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public AuthController(AuthenticationManager authenticationManager, JwtProvider tokenProvider, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.authenticationManager = authenticationManager;
		this.tokenProvider = tokenProvider;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping(value = "/signin", consumes = "application/json", produces = "application/json")
	public ResponseEntity<JwtResponseDto> authenticateUser(@RequestBody LoginDto loginDto) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = tokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtResponseDto(jwt));

	}

	@PostMapping(value = "/signup", consumes = "application/json", produces = "application/text")
	public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {

		if (userRepository.existsByUsername(signUpDto.getUsername())) {
			return new ResponseEntity<>("Username already taken!", HttpStatus.BAD_REQUEST);
		}
		User user = new User();
		user.setUsername(signUpDto.getUsername());
		user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
		user.setActive(true);

		Role roles = roleRepository.findByName(RoleName.NODE);
		user.setRoles(Arrays.asList(roles));

		userRepository.save(user);
		return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
	}
}
