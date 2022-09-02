package pe.rodcar.shelterpet.adoption.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.rodcar.shelterpet.adoption.entities.User;
import pe.rodcar.shelterpet.adoption.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(email).orElseThrow(
				() -> new UsernameNotFoundException("Usuario no encontrado con -> nombre de usuario o correo electrónico  : " + email));

		return UserPrinciple.build(user);
	}
}