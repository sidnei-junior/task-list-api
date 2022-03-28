package com.tasklist.tasklistapi.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.tasklist.tasklistapi.data.UserDetailData;
import com.tasklist.tasklistapi.models.User;
import com.tasklist.tasklistapi.repository.UserRepository;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;
	
	public UserDetailServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isEmpty()) {
			throw new UsernameNotFoundException("Usuário [" + username + "] não encontrado");
		}
		return new UserDetailData(user);
	}

}
