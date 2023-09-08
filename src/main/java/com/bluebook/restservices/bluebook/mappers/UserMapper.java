package com.bluebook.restservices.bluebook.mappers;

import com.bluebook.restservices.bluebook.dto.UserMsDTO;
import com.bluebook.restservices.bluebook.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface UserMapper {
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	// User to user Dto
	@Mappings(
			@Mapping(source="email",target ="email")
	)
//	    // Use when variables have diff name
	UserMsDTO usertoUserMsDTO(User user);
	
	// List<User> to List<UsersDTO>
	
	List<UserMsDTO> usersToUserDtos(List<User> users);
}
