package com.datagi.teamcalendar.rolelist;

import com.datagi.teamcalendar.domain.role.Role;
import com.datagi.teamcalendar.domain.role.repository.RoleRepository;
import com.datagi.teamcalendar.domain.rolelist.RoleList;
import com.datagi.teamcalendar.domain.rolelist.repository.RoleListRepository;
import com.datagi.teamcalendar.domain.user.User;
import com.datagi.teamcalendar.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@SpringBootTest
class RoleListTest {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final RoleListRepository roleListRepository;

    @Autowired
    public RoleListTest(RoleRepository roleRepository,
                        UserRepository userRepository,
                        RoleListRepository roleListRepository
    ) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.roleListRepository = roleListRepository;
    }

    @Test
    @DisplayName("롤 리스트 추가 성공 테스트")
    void saveRoleListSuccessTest(){

        Role role = roleRepository.getById(1L);
        User user = userRepository.getById(1L);

        RoleList roleList = RoleList.builder()
                .role(role)
                .user(user)
                .build();

        roleListRepository.save(roleList);

        assertThat(roleList.getId()).isNotNull();
    }

    @Test
    @DisplayName("롤 리스트 추가 실패 테스트 (유저 정보 누락)")
    void saveRoleListFailureTest1(){

        Role role = roleRepository.getById(1L);

        RoleList roleList = RoleList.builder()
                .role(role)
                .build();

        assertThatThrownBy(() -> roleListRepository.save(roleList))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    @DisplayName("롤 리스트 추가 실패 테스트 (역할 정보 누락)")
    void ssaveRoleListFailureTest2(){

        User user = userRepository.getById(1L);

        RoleList roleList = RoleList.builder()
                .user(user)
                .build();

        assertThatThrownBy(() -> roleListRepository.save(roleList))
                .isInstanceOf(DataIntegrityViolationException.class);
    }
}
