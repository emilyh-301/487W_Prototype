package com.hotel.model.repo;

import com.hotel.model.GroupMember;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

public interface MemberDatabaseInterface {

    ArrayList<GroupMember> getDatabase();

    boolean containsKey(String key);

    void add(GroupMember member);

    void remove(GroupMember member);

    void remove(String email);

    GroupMember findByEmail(String email);

    void edit(@NotNull String email, String first, String last, String avatar_url, String language);
}
