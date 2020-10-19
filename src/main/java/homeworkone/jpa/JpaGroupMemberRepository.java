package homeworkone.jpa;

import homeworkone.GroupMember;
import org.springframework.data.repository.CrudRepository;

public interface JpaGroupMemberRepository extends CrudRepository<GroupMember, String> {
}
