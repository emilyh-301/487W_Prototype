package com.hotel.database;

import com.hotel.model.GroupMember;
import com.hotel.database.jpa.JpaGroupMemberRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public class MemberDatabase {

    private final JpaGroupMemberRepository repo;

    public MemberDatabase(JpaGroupMemberRepository repo) {
        this.repo = repo;
    }

    public ArrayList<GroupMember> getDatabase() {
        return (ArrayList<GroupMember>) repo.findAll();
    }

    public boolean containsKey(String key) {
        return repo.existsById(key);
    }

    public void add(GroupMember member) {
        if(!containsKey(member.getEmail())) repo.save(member);
    }

    public void remove(GroupMember member) {
        if(containsKey(member.getEmail())) repo.delete(member);
    }

    public void remove(String email) {
        if(containsKey(email))
            remove(repo.findById(email).get());
    }

    public GroupMember findByEmail(String email) {
        Optional<GroupMember> m = repo.findById(email);
        return m.orElse(null);
    }

    public void edit(@NotNull String email, String first, String last, String avatar_url, String language) {
        GroupMember toEdit = findByEmail(email);
        if(toEdit == null) return;

        /*
         * Update the member's values
         */
        if(first != null) toEdit.setFirstName(first);
        if(last != null) toEdit.setLastName(last);
        if(avatar_url != null) toEdit.setAvatar(avatar_url);
        if(language != null) toEdit.setLanguage(language);

        /*
         * Update the database
         */
        repo.save(toEdit);
    }


}
