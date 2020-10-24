package com.hotel.service;

import com.hotel.model.GroupMember;
import com.hotel.model.repo.MemberDatabase;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Service
public class MemberService implements MemberServiceInterface {

    private final MemberDatabase database;

    public MemberService(MemberDatabase database) {
        this.database = database;
    }

    public ArrayList<GroupMember> getMembers() {
        return database.getDatabase();
    }

    public void add(@NotNull String email, String first, String last, String avatar_url, String language) {
        database.add(new GroupMember(email, first == null? " " : first, last == null? " " : last,
                avatar_url == null? " " : avatar_url, language == null? " " : language));
    }

    public void remove(GroupMember member) {
        database.remove(member);
    }

    public void remove(String email) {
        database.remove(email);
    }

    @Override
    public void edit(@NotNull String email, String first, String last, String avatar_url, String language) {
        database.edit(email, first, last, avatar_url, language);
    }

    @Override
    public GroupMember findByEmail(String email) {
        return database.findByEmail(email);
    }
}
