package ar.com.midinero.MIDinero.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ar.com.midinero.MIDinero.jwt.MainUser;
import ar.com.midinero.MIDinero.models.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User usuario = userService.getUserFromUserName(username);
		return MainUser.build(usuario);	
	}

}
