package com.taskmanager.service;

import com.taskmanager.dto.MyUserDto;
import com.taskmanager.dto.MyUserResponse;

public interface MyUserService {

	public MyUserDto createUser(MyUserDto myUserDto);

	public void deleteMyUserById(int id);

	public MyUserResponse getAllMyUsers(int pageNo, int pageSize);

	public MyUserDto getMyUserById(int id);

	public MyUserDto updateMyUserDetail(MyUserDto myUserDtoUpdate, int id);

	public MyUserDto currentUser();

}
