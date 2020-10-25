package com.hotel.database.jpa;

import com.hotel.model.GroupMember;
import org.springframework.data.repository.CrudRepository;

public interface JpaGroupMemberRepository extends CrudRepository<GroupMember, String> {
}
