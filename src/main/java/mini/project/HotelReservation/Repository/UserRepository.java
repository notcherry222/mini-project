package mini.project.HotelReservation.Repository;


import mini.project.HotelReservation.Data.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Long : pk value
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findStatusByEmail(String email);
}
