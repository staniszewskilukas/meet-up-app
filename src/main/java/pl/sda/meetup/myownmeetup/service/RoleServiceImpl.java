package pl.sda.meetup.myownmeetup.service;

import org.springframework.stereotype.Service;
import pl.sda.meetup.myownmeetup.dao.RoleModel;
import pl.sda.meetup.myownmeetup.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleModel findDefaultRole() {
        return roleRepository.findByRoleName("zwykły użytkownik");
    }

}
