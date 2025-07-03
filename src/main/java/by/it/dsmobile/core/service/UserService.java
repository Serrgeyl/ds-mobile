package by.it.dsmobile.core.service;

import by.it.dsmobile.api.dto.response.*;
import by.it.dsmobile.api.mapper.GroupMapper;
import by.it.dsmobile.api.mapper.OrganizationMapper;
import by.it.dsmobile.api.mapper.RelatedUserMapper;
import by.it.dsmobile.api.mapper.UserMapper;
import by.it.dsmobile.core.exception.ValueNotFoundException;
import by.it.dsmobile.core.model.Group;
import by.it.dsmobile.core.model.GroupType;
import by.it.dsmobile.core.model.Organization;
import by.it.dsmobile.core.model.User;
import by.it.dsmobile.core.repository.GroupRepository;
import by.it.dsmobile.core.repository.ServiceToUserRepository;
import by.it.dsmobile.core.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final ServiceToUserRepository serviceToUserRepository;
    private final UserMapper userMapper;
    private final GroupMapper groupMapper;
    private final RelatedUserMapper relatedUserMapper;
    private final OrganizationMapper organizationMapper;


    public User findByPhoneNumber(final String phoneNumber) {
        return userRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ValueNotFoundException("User not found [phoneNumber = %s]".formatted(phoneNumber)));
    }

    public User findById(final Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ValueNotFoundException("No user found [id = %s]".formatted(id)));
    }

    public void saveAndFlush(final User user) {
        userRepository.saveAndFlush(user);
    }

    public List<RelatedUser> getRelatedUsers(final Integer id) {
        return serviceToUserRepository
                .findByOwnerId(id)
                .stream()
                .filter(s -> !s.getService().getDisposable())
                .map(relatedUserMapper::toRelatedUser)
                .sorted(Comparator.comparingInt(RelatedUser::getId))
                .toList();
    }

    public Double getBalanceById(final Integer id) {
        return userRepository.getBalanceById(id)
                .orElseThrow(() -> new ValueNotFoundException("User not found [id = %s]".formatted(id)));
    }

    public TeacherAdditionalData getTeacherAdditionalData(final Integer id) {
        final var organization = groupRepository
                .findByUsers_Id(id)
                .stream()
                .filter(group -> group.getGroupType() == GroupType.TEACHERS)
                .findFirst()
                .map(Group::getOrganization)
                .map(organizationMapper::toOrganizationResponse)
                .orElseThrow(() -> new ValueNotFoundException("Group [type = teachers] not found for user [id = %s]".formatted(id)));

        final var relatedGroups = groupRepository
                .findAllByClassTeacherId(id)
                .stream()
                .map(groupMapper::toGroupBriefResponse)
                .sorted(Comparator.comparing(GroupBriefResponse::getMapping))
                .toList();

        final var teacherAdditionalData = new TeacherAdditionalData();
        teacherAdditionalData.setOrganization(organization);
        teacherAdditionalData.setRelatedGroups(relatedGroups);

        return teacherAdditionalData;
    }

    public AdministrationAdditionalData getAdministrationAdditionalData(final Integer id) {
        final var organizationId = groupRepository
                .findByUsers_Id(id)
                .stream()
                .filter(group -> group.getGroupType() == GroupType.ADMINISTRATION)
                .findFirst()
                .map(Group::getOrganization)
                .map(Organization::getId)
                .orElseThrow(() -> new ValueNotFoundException("Group [type = administration] not found for user [id = %s]".formatted(id)));

        final var teachers = groupRepository
                .findAllByOrganization_Id(organizationId)
                .stream()
                .filter(group -> group.getGroupType() == GroupType.TEACHERS)
                .map(Group::getUsers)
                .flatMap(Collection::stream)
                .map(userMapper::toUserBriefResponse)
                .toList();

        final var groups = groupRepository
                .findAllByOrganization_Id(organizationId)
                .stream()
                .filter(group -> group.getGroupType() == GroupType.CLASS)
                .map(groupMapper::toGroupBriefResponse)
                .toList();

        final var administrationAdditionalData = new AdministrationAdditionalData();
        administrationAdditionalData.setTeachers(teachers);
        administrationAdditionalData.setGroups(groups);

        return administrationAdditionalData;
    }
}
