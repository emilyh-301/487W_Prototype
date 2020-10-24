package com.hotel.service;

import com.hotel.model.GroupMember;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

public interface MemberServiceInterface {

    ArrayList<GroupMember> getMembers();

    void add(@NotNull String email, String first, String last, String avatar_url, String language);

    void remove(GroupMember member);

    void remove(String email);

    void edit(@NotNull String email, String first, String last, String avatar_url, String language);

    GroupMember findByEmail(String email);
}
